package topic5_P_procedural_generation;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetDoubleLogarithmic;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;
import mars.random.RNG;
import mars.random.fixed.continuous.PerlinNoise;

public class Terrain implements Drawing {
	
	int size = 500;
	
	@GadgetDoubleLogarithmic(min = 0.01, max = 100)
	double zoom = 3.0;
	
	@GadgetDouble(min = 0.0, max = 5.0)
	double z = 0.5;
	
	@GadgetInteger(min = 0, max = 10)
	int nLevels = 8;
	
	@GadgetBoolean
	boolean archipelago = false;
	
	
	PerlinNoise[] pn;
	
	
	double factor = 1.62;
	
	
	Color c0 = Color.hsb(210, 0.7, 0.8); // duboka voda
	double h0 = -0.5;
	Color c1 = Color.hsb(210, 0.7, 0.9); // voda
	double h1 =  0.0;
	Color c2 = Color.hsb( 50, 0.5, 1.0); // pesak
	double h2 =  0.1;
	Color c3 = Color.hsb(110, 0.6, 0.8); // trava
	double h3 =  0.4;
	Color c4 = Color.hsb( 30, 0.6, 0.5); // planina
	double h4 =  0.8;
	Color c5 = Color.hsb(  0, 0.0, 1.0); // sneg
	
	
	{
		setup();
	}
	
	
	void setup() {
		
		pn = new PerlinNoise[10];
		RNG rng = new RNG();
		
		for (int i = 0; i < pn.length; i++)
			pn[i] = new PerlinNoise(rng.nextLong());
	}
	
	
	public Image generateMap() {
		
		WritableImage image = new WritableImage(size, size);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (x / image.getWidth());
				double dy = (y / image.getHeight());
				double dd = Math.sqrt(Math.pow(dx * 2.0 - 1.0, 2) + Math.pow(dy * 2.0 - 1.0, 2));
				
				dd = dd > 1.0 ? 1.0 : dd;
				dd = dd * 2.0 - 1.0;
				
				dx *= zoom;
				dy *= zoom;
				
				double amplitude = 1.0;
				double frequency = 1.0;
				double h = 0.0;
				
				for (int l = 0; l < nLevels; l++) {
					
					h += amplitude * pn[l].getValue(dx * frequency, dy * frequency, z);
					amplitude /= factor;
					frequency *= factor;
				}
				
				h = archipelago ? h - dd : h;
				
				Color c;
				
					c = c5;
				if (h < h4)
					c = c4;
				if (h < h3)
					c = c3;
				if (h < h2)
					c = c2;
				if (h < h1)
					c = c1;
				if (h < h0)
					c = c0;
				
				pw.setColor(x, y, c);
			}
		}
		
		
		return image;
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		view.drawImageCentered(Vector.ZERO, generateMap());
	}

	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if (event.isKeyPress(KeyCode.ENTER)) {
			setup();
		}
	}
	
	
	public static void main(String[] args) {
		
		Options options = Options.redrawOnEvents();
		options.drawingSize = new Vector(666, 666);
		DrawingApplication.launch(options);
	}
}
