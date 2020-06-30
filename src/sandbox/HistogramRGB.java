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

public class HistogramRGB implements Drawing {
	
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
		
		int[] arrBright = new int[256];
		int[] arrR = new int[256];
		int[] arrG = new int[256];
		int[] arrB = new int[256];
		int maxCount = 0;
		
		PixelReader pr = image.getPixelReader();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				Color inputColor = pr.getColor(x, y);
				
				int indexBright = (int)(255 * inputColor.getBrightness());
				int indexR 		= (int)(255 * inputColor.getRed());
				int indexG 		= (int)(255 * inputColor.getGreen());
				int indexB 		= (int)(255 * inputColor.getBlue());
				
				arrBright[indexBright]++;
				arrR[indexR]++;
				arrG[indexG]++;
				arrB[indexB]++;
				
				maxCount = arrBright[indexBright] > maxCount ? arrBright[indexBright] : maxCount;
				maxCount = arrR[indexR] > maxCount ? arrR[indexR] : maxCount;
				maxCount = arrG[indexG] > maxCount ? arrG[indexG] : maxCount;
				maxCount = arrB[indexB] > maxCount ? arrB[indexB] : maxCount;
			}	
		}
		
		// normalizacija na interval [0, 100)
		for (int i = 0; i < arrBright.length; i++) {
			arrBright[i] 	= (int) (arrBright[i] / (double) maxCount * 100.0);
			arrR[i] 		= (int) (arrR[i] / (double) maxCount * 100.0);
			arrG[i] 		= (int) (arrG[i] / (double) maxCount * 100.0);
			arrB[i] 		= (int) (arrB[i] / (double) maxCount * 100.0);
		}
		
		int w = 256 * zoomHist;
		int h = 100 * zoomHist;
		
		WritableImage histogram = new WritableImage(w, h);
		PixelWriter pw = histogram.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				int r = 100 - arrR[x / zoomHist] < y / zoomHist ? 1 : 0;
				int g = 100 - arrG[x / zoomHist] < y / zoomHist ? 1 : 0;
				int b = 100 - arrB[x / zoomHist] < y / zoomHist ? 1 : 0;
				
				Color color = new Color(r, g, b, opacityHist);
				
				pw.setColor(x, y, color);
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