package excercises;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import topic2_P_image_processing.filters.displacement.Jitter;

public class ColorPatch implements Drawing {
	
	Jitter jitter = new Jitter(40);
	
	
	Image drawPatch() {
		
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				if (x < image.getWidth() / 2) {
					
					if (y < image.getHeight() / 2) {
						
						pw.setColor(x, y, Color.CORNFLOWERBLUE);
					} else {
						
						double dx = x / (w / 2.0);
						double dy = (y - h / 2.0) / (h / 2.0);
						
						pw.setColor(x, y, new Color(dx, dy, 0.0, 1.0));
					}
					
				} else {
					
					if (y < image.getHeight() / 2) {
;
						pw.setColor(x, y, Color.hsb(300, y / (h / 2.0), 1.0));
					} else {
						
						pw.setColor(x, y, Color.hsb(360 * y / (h / 2.0), 0.35, 0.75));
					}
				}
			}
		}
		
		return image;
	}
	
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.drawImageCentered(Vector.ZERO, jitter.process(drawPatch()));
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
