package excercises;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.random.RNG;

public class RainbowFieldRaster implements Drawing {
	
	final static Vector IMG_SIZE = new Vector(700, 600);
	
	final static int TILE = 70;
	
	
	@GadgetDouble(min = 0.0, max = 600)
	double groundLevel = 300;
	
	@GadgetDouble(min = 0.25, max = 0.95)
	double r = 0.75;
	
	@GadgetInteger(min = 1, max = 12)
	int w = 7;
	
	
	public Image drawSky() {
		
		WritableImage sky = new WritableImage((int) IMG_SIZE.x, (int) IMG_SIZE.y);
		PixelWriter pw = sky.getPixelWriter();
		
		for (int y = 0; y < sky.getHeight(); y++)
			for (int x = 0; x < sky.getWidth(); x++)
				pw.setColor(x, y, Color.hsb(210, 0.6, 1.0));
		
		return sky;
	}
	
	
	public Image drawRainbow() {
		
		WritableImage rainbow = new WritableImage((int) IMG_SIZE.x, (int) IMG_SIZE.y);
		PixelWriter pw = rainbow.getPixelWriter();
		
		double r0 = r - w * 0.05;
		
		int band = 360 / w;
		
		for (int y = 0; y < (int) IMG_SIZE.y / 2; y++) {
			for (int x = 0; x < (int) IMG_SIZE.x; x++) {
				
				double dx = (x / IMG_SIZE.x) * 2.0 - 1.0;
				double dy = (y / IMG_SIZE.y) * 2.0 - 1.0;
				
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				if (r0 <= dd && dd <= r) {
					
					double k = (dd - r0) / (r - r0);
					pw.setColor(x, y, Color.hsb((int) (360 * (1 - k)) / band * band, 0.75, 0.95));
				}
			}
		}
		
		return rainbow;
	}
	
	
	public Image drawField() {
		
		RNG rng = new RNG(6380977105788498275l);
		
		double[][] b = new double[(int) IMG_SIZE.x / TILE + 1][(int) IMG_SIZE.y / TILE + 1];
		
		for (int i = 0; i < b.length; i++)
			for (int j = 0; j < b[0].length; j++)
				b[i][j] = rng.nextDouble() * 0.5 + 0.25;
		
		WritableImage field = new WritableImage((int) IMG_SIZE.x, (int) IMG_SIZE.y);
		PixelWriter pw = field.getPixelWriter();
		
		for (int y = (int) groundLevel; y < (int) IMG_SIZE.y; y++)
			for (int x = 0; x < (int) IMG_SIZE.x; x++)
					pw.setColor(x, y, Color.hsb(120, 0.5, b[x / TILE][y / TILE]));
		
		return field;
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.drawImageCentered(Vector.ZERO, drawSky());
		view.drawImageCentered(new Vector(0, IMG_SIZE.y / 2 - groundLevel), drawRainbow());
		view.drawImageCentered(Vector.ZERO, drawField());
	}
	
	
	public static void main(String[] args) {
		
		DrawingApplication.launch((int) IMG_SIZE.x, (int) IMG_SIZE.y);
	}
}
