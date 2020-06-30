package sandbox;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class FourCircles implements Drawing {	
	
	public Image imgDrawing0() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (2.0 * x / image.getWidth()) - 1.0;
				double dy = ((2.0 * y / image.getHeight()) - 1.0) * 1.0;
				
				double limitUp = 0.25;
				double limitDn = 0.75;
				double radius  = (limitDn - limitUp) / 2.0;
				
				if ((-limitDn <= dx && dx <= -limitUp && -limitDn <= dy && dy <= -limitUp)) {
					double distance = Math.sqrt(Math.pow((dx + 2.0 * radius), 2) + Math.pow((dy + 2.0 * radius), 2));
					if (distance >= +radius) distance = +radius;
					
					pw.setColor(x, y, Color.hsb(360 * (distance * 1.0), 0.85, 1 - (distance * 4.0)));
				}
				
				if ((+limitUp <= dx && dx <= +limitDn && -limitDn <= dy && dy <= -limitUp)) {
					double distance = Math.sqrt(Math.pow((dx - 2.0 * radius), 2) + Math.pow((dy + 2.0 * radius), 2));
					if (distance >= +radius) distance = +radius;
					
					pw.setColor(x, y, Color.hsb(360 * (distance * 2.0), 0.85, 1 - (distance * 4.0)));
				}
				
				if ((-limitDn <= dx && dx <= -limitUp && +limitUp <= dy && dy <= +limitDn)) {
					double distance = Math.sqrt(Math.pow((dx + 2.0 * radius), 2) + Math.pow((dy - 2.0 * radius), 2));
					if (distance >= +radius) distance = +radius;
					
					pw.setColor(x, y, Color.hsb(360 * (distance * 4.0), 0.85, 1 - (distance * 4.0)));
				}
				
				if ((+limitUp <= dx && dx <= +limitDn && +limitUp <= dy && dy <= +limitDn)) {
					double distance = Math.sqrt(Math.pow((dx - 2.0 * radius), 2) + Math.pow((dy - 2.0 * radius), 2));
					if (distance >= +radius) distance = +radius;
					
					pw.setColor(x, y, Color.hsb(360 * (distance * 8.0), 0.85, 1 - (distance * 4.0)));
				}
				
				/* 
				double dd = 0.45;
				
				if ((dx >= -0.50 - dd && dx <= -0.50 + dd) && 
					(dy >= -0.50 - dd && dy <= -0.50 + dd)) {
					double distX = Math.abs(dx + 0.50);
					double distY = Math.abs(dy + 0.50);
					double dist = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
					
					if (dist >= dd) pw.setColor(x, y, Color.AQUA);
					else pw.setColor(x, y, Color.BLACK);
					
				} else {
					pw.setColor(x, y, Color.DARKRED);
				}
				*/
			}
		}
		
		return image;
	}
	/*
	public Image imgDrawing1() {
		
		int h = 
		
		WritableImage image = new WritableImage()
		
		return image;
	}
	*/
	
//	=-=-=-=-=
	
	Image[] images;
	
	public void init(View view) {
		images = new Image[] {
			imgDrawing0()	
		};
	}

	@GadgetColorPicker
	Color colorBackground = Color.GRAY;
	
	@GadgetInteger(min = 0, max = 5)
	int imageIndex = 0;
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, colorBackground);
		view.drawImageCentered(Vector.ZERO, images[imageIndex]);
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
	
}
