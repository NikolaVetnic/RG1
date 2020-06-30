package sandbox;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
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

public class Histogram implements Drawing {
	
	@GadgetInteger(min = 1, max = 4)
	int zoomHist = 1;
	
	@GadgetDouble(min = 0.0, max = 1.0)
	double opacityHist = 0.0;
	
	@GadgetInteger(min = 0, max = 13)
	Integer imageIndex = 0;
	
	String[] fileNames = {
			"Mona Lisa.jpg",
			"building.jpg",
			"catparty.jpg",
			"christmas.jpg",
			"couple.jpg",
			"dive.jpg",
			"doggo.jpg",
			"fall.jpg",
			"forecast.jpg",
			"kitchen.jpg",
			"meterologist.jpg",
			"office.jpg",
			"skirts.jpg",
			"waiting.jpg",
	};
	
	public Image histogram(Image image) {
		
		int[] brightnessArr = new int[256];
		int maxCount = 0;
		
		PixelReader pr = image.getPixelReader();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				Color inputColor = pr.getColor(x, y);
				
				int index = (int)(255 * inputColor.getBrightness());
				brightnessArr[index]++;
				
//				if (brightnessArr[index] > maxCount)
//					maxCount = brightnessArr[index];
				maxCount = brightnessArr[index] > maxCount ? brightnessArr[index] : maxCount;
			}	
		}
		
		// normalizacija na interval [0, 100)
		for (int i = 0; i < brightnessArr.length; i++) {
			brightnessArr[i] = (int) (brightnessArr[i] / (double) maxCount * 100.0);
		}
		
		int w = 256 * zoomHist;
		int h = 100 * zoomHist;
		
		Color black = new Color(1, 1, 1, opacityHist);
		Color white = new Color(0, 0, 0, opacityHist);
		
		WritableImage histogram = new WritableImage(w, h);
		PixelWriter pw = histogram.getPixelWriter();
		
		for (int x = 0; x < w; x++) {
			for (int y = h - 1; y >= 0; y--) {
				if (100 - brightnessArr[x / zoomHist] >= (y / zoomHist))
					pw.setColor(x, y, black);
				else
					pw.setColor(x, y, white);
			}
		}
		
		return histogram;
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		Image image = new Image("images/" + fileNames[imageIndex]);
		
		view.drawImageCentered(Vector.ZERO, image);
		view.drawImageCentered(Vector.ZERO, histogram(image));
		
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
}