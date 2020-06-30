package kolokvijum_jun_2020;

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
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class Silhouette implements Drawing {

	@GadgetBoolean
	boolean applyFilter = true;
	
	@GadgetColorPicker
	Color colorBackground = new Color(0.2, 0.2, 0.2, 1.0);
	
	@GadgetDouble(min = 0.0, max = 1.0)
	double threshold = 0.5;
	
	
	Image image;
	
	
	@Override
	public void init(View view) {
		
		image = new Image("images/fall.jpg");
	}
	
	
	Image threshold() {
		
		PixelReader pr = image.getPixelReader();
		
		WritableImage output = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				Color cc = pr.getColor(x, y); 
				double b = (cc.getRed() + cc.getGreen() + cc.getBlue()) / 3.0;
				
				Color c = b < threshold && y % 5 != 0 ? Color.BLACK : Color.hsb(
																		colorBackground.getHue(), 
																		colorBackground.getSaturation(), 
																		1 - y / image.getHeight());
				
				pw.setColor(x, y, c);
			}
		}
		
		return output;
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.drawImageCentered(Vector.ZERO, applyFilter ? threshold() : image);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
