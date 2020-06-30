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
import mars.geometry.Vector;

public class RosKosmos implements Drawing {

	@GadgetColorPicker
	Color cBackground = Color.ANTIQUEWHITE;
	
	public Image image() {
		
		int h = 400, w = 400;
		
		WritableImage image = new WritableImage(h, w);
		PixelWriter pw = image.getPixelWriter();
		
		/*
		// rotacija
		double angle = Math.toRadians(25);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x0 = 0.5 * (h - 1.0);
		double y0 = 0.5 * (w - 1.0);
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double a = 0.75 * (x - x0);
				double b = 0.75 * (y - y0);
				
				int xx = (int) (+a * cos - b * sin + x0);
				int yy = (int) (+a * sin + b * cos + y0);
				
				if (xx >= 0 && xx < w && yy >= 0 && yy < h) {
					pw.setColor(xx, yy, Color.ANTIQUEWHITE);
				}
			}
		}
		*/
		
		// iscrtavanje okvira
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1.0;
				double dy = (2.0 * y / h) - 1.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				if (0.975 <= dd && dd <= 1.0)
					pw.setColor(x, y, Color.DIMGRAY);
				else if (dd < 0.975)
					pw.setColor(x, y, Color.WHITESMOKE);
				else
					pw.setColor(x, y, Color.TRANSPARENT);
				
				
			}
		}
		
		// iscrtavanje unutrasnjeg oblika
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = ((2.0 * x / (2 * w / 3)) - 1.5) * 0.90;
				double dy = ((2.0 * y / (2 * h / 3)) - 2.0) * 2.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				if (dd <= 1) {
					pw.setColor(x, y, Color.DIMGRAY);
				}
			}
		}
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				double dx = ((2.0 * x / (2 * w / 3)) - 1.50) * 0.90;
				double dy = ((2.0 * y / (2 * h / 3)) - 1.90) * 2.15;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				if (dd <= 0.85) {
					pw.setColor(x, y, Color.WHITE);
				}
			}
		}
		
		// iscrtavanje rakete
		h = 2 * h / 3;
		w = 2 * w / 3;
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (x >= w - y) {
					
					if (y < w - x / 2) 
						pw.setColor(x + w / 6, y + h / 6, Color.DARKRED);
					
					if (y >= h / 2 && x >= w / 2 && x > y)
						pw.setColor(x + h / 6, (y * 2) / 3 + (h / 3), Color.DARKRED);
				}
			}
		}

		return image;
	}
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, cBackground);
		view.drawImageCentered(Vector.ZERO, image());
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}