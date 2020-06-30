package topic2_P_image_processing.filters.misc;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.geometry.Vector;

public class Sobel extends Filter {
	
	private static final double[][] kernelX = {
			{-0.25,  0   ,  0.25},
			{-0.5 ,  0   ,  0.5 },
			{-0.25,  0   ,  0.25}
	};
	
	private static final double[][] kernelY = {
			{-0.25, -0.5 , -0.25},
			{ 0   ,  0   ,  0   },
			{ 0.25,  0.5 ,  0.25}
	};

	private static final double sqrt2 = Math.sqrt(2);

	

	public static double[][] brightness(Image input) {
		PixelReader pr = input.getPixelReader();
		
		int h = (int) input.getHeight();
		int w = (int) input.getWidth();
		
		double[][] b = new double[h][w];
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				b[y][x] = pr.getColor(x, y).getBrightness();
			}
		}
		
		return b;
	}
	
	
	public static Vector[][] gradient(double[][] brightness) {
		int h = brightness.length;
		int w = brightness[0].length;
		
		Vector[][] g = new Vector[h][w];
		
		// Za svaki piksel u input slici
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double gx = 0;
				double gy = 0;

				// Za svaki element kernela
				for (int dy = 0; dy < 3; dy++) {
					for (int dx = 0; dx < 3; dx++) {
						
						int py = (y - dy + 1 + h) % h;
						int px = (x - dx + 1 + w) % w;

						double b = brightness[py][px];

						gy += kernelY[dy][dx] * b;
						gx += kernelX[dy][dx] * b;
					}
				}
				
				g[y][x] = (new Vector(gy, gx)).div(sqrt2); // Delimo sa koren iz 2 da bi intenzitet bio u [0, 1].
			}
		}
		
		return g;
	}
	
	
	public static Vector[][] gradient(Image input) {
		return gradient(brightness(input));
	}
	

	public static Image imgMagnitude(Vector[][] gradient) {
		int h = gradient.length;
		int w = gradient[0].length;

		WritableImage output = new WritableImage(w, h);
		PixelWriter pw = output.getPixelWriter();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				pw.setColor(x, y, Color.gray(gradient[y][x].norm()));
			}
		}

		return output;
	}

	
	public static Image imgEmboss(Vector[][] gradient, double angle) {
		int h = gradient.length;
		int w = gradient[0].length;
		
		Vector p = Vector.polar(1, angle);
		
		WritableImage output = new WritableImage(w, h);
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double k = (gradient[y][x].dot(p) + 1) / 2;
				pw.setColor(x, y, Color.gray(k));
			}
		}
		
		return output;
	}
	
	
	public static Image imgAngleHue(Vector[][] gradient) {
		int h = gradient.length;
		int w = gradient[0].length;
		
		WritableImage output = new WritableImage(w, h);
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				pw.setColor(x, y, Color.hsb(360 * gradient[y][x].angle(), 1, gradient[y][x].norm()));
			}
		}
		
		return output;
	}	
	

	@Override
	public Image process(Image input) {
		return imgMagnitude(gradient(input));
	}
		
}
