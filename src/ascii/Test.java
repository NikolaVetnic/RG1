package ascii;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.geometry.Vector;

public class Test implements Drawing {
	
	@GadgetBoolean
	boolean avg = false;
	
	public Image gradient() {
		
		WritableImage im = new WritableImage(100, 100);
		PixelWriter pw = im.getPixelWriter();
		
		for (int j = 0; j < 100; j++)
			for (int i = 0; i < 100; i++)
				pw.setColor(i, j, Color.hsb((1.0 * j / 100) * 90, 0.85, 0.75));
		
		return im;
	}
	
	public Image avg(Image in) {
		
		PixelReader pr = in.getPixelReader();
		
		WritableImage im = new WritableImage(100, 100);
		PixelWriter pw = im.getPixelWriter();
		
		double r = 0, g = 0, b = 0;
		
		for (int j = 0; j < 100; j++)
			for (int i = 0; i < 100; i++) {
				r += pr.getColor(i, j).getRed();
				g += pr.getColor(i, j).getGreen();
				b += pr.getColor(i, j).getBlue();
			}
		
		int ri = (int) (255 * r / 10000), gi = (int) (255 * g / 10000), bi = (int) (255 * b / 10000);
		
		System.out.println((int) (255 * r / 10000));
		
		for (int j = 0; j < 100; j++)
			for (int i = 0; i < 100; i++)
				pw.setColor(i, j, Color.rgb(ri, gi, bi));
		
		return im;
	}

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.drawImageCentered(Vector.ZERO, gradient());
		
		if (avg) view.drawImageCentered(Vector.ZERO, avg(gradient()));
	}

	public static void main(String[] args) {
		
		DrawingApplication.launch(640, 640);
	}
}
