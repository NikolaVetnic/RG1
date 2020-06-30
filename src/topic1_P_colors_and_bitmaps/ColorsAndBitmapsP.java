package topic1_P_colors_and_bitmaps;

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

public class ColorsAndBitmapsP implements Drawing {
	
	// (0)
	public Image imgSolidColor() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				pw.setColor(x, y, new Color(1.0, 0.25, 0.25, 1.0));
			}
		}
		
		return image;
	}
	
	
	// (1)
	public Image imgLinearGradient1() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				pw.setColor(x, y, new Color(x / image.getWidth(), 0.25, 0.25, 1.0));
			}
		}
		
		return image;
	}

	
	// (2)
	public Image imgLinearGradient2() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				pw.setColor(x, y, new Color(x / image.getWidth(), 0.25, y / image.getHeight(), 1.0));
			}
		}
		
		return image;
	}

	
	// (3)
	public Image imgRadialGradient() {

		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (x / image.getWidth()) * 2.0 - 1.0;
				double dy = (y / image.getHeight()) * 2.0 - 1.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				Color c = dd > 1.0 ? Color.TRANSPARENT : new Color(1.0 - dd, 0.25, 0.25, 1.0);
				
				pw.setColor(x, y, c);
			}
		}
		
		return image;
	}

	
	// (4)
	public Image imgRadialGradientOpacity() {

		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (x / image.getWidth()) * 2.0 - 1.0;
				double dy = (y / image.getHeight()) * 2.0 - 1.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				dd = dd > 1.0 ? 1.0 : dd;
				
				pw.setColor(x, y, new Color(1.0, 0.25, 0.25, 1.0 - dd));
			}
		}
		
		return image;
	}

	
	// (5)
	public Image imgWave1() {

		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		/**
		 * intenzitet boje je talasna funkcija po x
		 * 
		 * "razvlacenje" intervala (perioda sin-a) 2 * Math.PI na interval [0,100]
		 * preko (x * 2 * Math.PI) / 100, pa kada je x = 100 dobije se 2 * Math.PI
		 * 
		 * skaliranje naravno preko mnozenja sa brojem
		 */
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double d = Math.cos(2 * Math.PI * (x + 50) / 100) / 2.0 + 0.5;
				
				pw.setColor(x, y, new Color(d, 0.25, 0.25, 1.0));
			}
		}
		
		return image;
	}
	
	
	// (6)
	public Image imgWave2() {
		
		/**
		 * crvena i zelena komponenta su talasne funkcije po x odnosno po y, plava
		 * je uvek 1
		 */
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = Math.cos(2 * Math.PI * (x + 50) / 100) / 2.0 + 0.5;
				double dy = Math.cos(2 * Math.PI * (y + 50) / 100) / 2.0 + 0.5;
				
				pw.setColor(x, y, new Color(dy, 0.25, dx, 1.0));
			}
		}
		
		return image;
	}
	
	
	// (7)
	public Image imgDiagonals() {
		
		/**
		 * Crvena i plava komponenta - funkcija dijagonalnih udaljenosti (x+y, od-
		 * nosno x-y osa). Kompononte boja se "ukljucuju" i "iskljucuju" periodic-
		 * no duz tih osa. Zelena je uvek 0.25.
		 */
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double r = (x + y) % 150 > 100 ? 1.0 : 0.0;
				double g = 0.25;
				double b = (x - y + image.getWidth()) % 150 > 100 ? 1.0 : 0.0;
				
				pw.setColor(x, y, new Color(r, g, b, 1.0));
			}
		}
		
		return image;
	}
	
	
	// (8)
	public Image imgFixedHue() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				pw.setColor(x, y, Color.hsb(0, x / image.getWidth(), y / image.getHeight()));
			}
		}
		
		return image;
	}
	
	
	// (9)
	public Image imgFixedSaturation() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				pw.setColor(x, y, Color.hsb(x / image.getWidth() * 360,	0.25, 1.0 - y / image.getHeight()));;
			}
		}
		
		return image;
	}
	
	
	// (10)
	public Image imgFixedBrightness() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				pw.setColor(x, y, Color.hsb(x / image.getWidth() * 360,	y / image.getHeight(), 0.75));;
			}
		}
		
		return image;
	}
	
	
	// (11)
	public Image imgDisk1() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (x / image.getWidth()) * 2.0 - 1.0;
				double dy = (y / image.getHeight()) * 2.0 - 1.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI);
				
				Color c = dd > 1.0 ? Color.TRANSPARENT : Color.hsb(phi, 0.75, 0.5);
				
				pw.setColor(x, y, c);
			}
		}
		
		return image;
	}
	
	
	// (12)
	public Image imgDisk2() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (x / image.getWidth()) * 2.0 - 1.0;
				double dy = (y / image.getHeight()) * 2.0 - 1.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI);
				
				Color c = dd > 1.0 ? Color.TRANSPARENT : Color.hsb(phi, 0.25, dd * 0.5 + 0.25);
				
				pw.setColor(x, y, c);
			}
		}
		
		return image;
	}
	
	
	// (13)
	public Image imgDisk3() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (x / image.getWidth()) * 2.0 - 1.0;
				double dy = (y / image.getHeight()) * 2.0 - 1.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				double phi = Math.atan2(dy, dx) * 360 / (2 * Math.PI);
				
				Color c = dd > 1.0 ? Color.TRANSPARENT : Color.hsb(phi, dd, 0.5);
				
				pw.setColor(x, y, c);
			}
		}
		
		return image;
	}
	
	
	// (14)
	public Image imgRainbow() {
		
		double r1 = 0.5, r2 = 0.75;
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (x / image.getWidth()) * 2.0 - 1.0;
				double dy = (y / image.getHeight()) * 2.0 - 1.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				Color c;
				
				if (dy >= 0.0) {
					
					c = Color.FORESTGREEN;
				} else {

					c = Color.BLUE;
				
					if (r1 <= dd && dd <= r2)
						c = Color.hsb(360 * (1 / (r2 - r1)) * dd, 0.85, 1.0);
					
					/**
					 * jos jedna varijanta racunanja duge: 
					 * 
					 * double k = (r0 - d) / (r1 - r0);
					 * c = Color.hsb(360 * k, 0.7, 1);
					 */
				}
				
				pw.setColor(x, y, c);
			}
		}
		
		return image;
	}

	
	// (15)
	public Image imgTablecloth() {
		
		WritableImage image = new WritableImage(400, 400);
		PixelWriter pw = image.getPixelWriter();
		
		int d = 20;
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				Color c;
				
				if ((x + y) % 2 == 0)
					c = x % (2 * d) < d ? Color.DARKRED : Color.ANTIQUEWHITE;
				else
					c = y % (2 * d) < d ? Color.INDIANRED : Color.WHITE;
				
				pw.setColor(x, y, c);
			}
		}
		
		return image;
	}

	
	// (16)
	public Image imgPlot() {
		
		WritableImage image = new WritableImage(400, 500);
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				
				double dx = (2.0 * x / image.getWidth()) - 1.0;
				double dy = Math.sin(dx * Math.PI);
				
				pw.setColor(x, (int) Math.round(image.getHeight() / 2.0 - dy * image.getWidth() / 2.0), Color.WHITE);
			}
		}
		
		return image;
	}

	
	// =-=-=
	
	
	Image[] images;
	
	
	@GadgetColorPicker
	Color colorBackground = new Color(0.2, 0.2, 0.2, 1.0);
	
	@GadgetInteger(min = 0, max = 16)
	int fileIndex = 10;
	
	
	@Override
	public void init(View view) {
		
		images = new Image[] {
			imgSolidColor(),
			imgLinearGradient1(),
			imgLinearGradient2(),
			imgRadialGradient(),
			imgRadialGradientOpacity(),
			imgWave1(),
			imgWave2(),
			imgDiagonals(),
			imgFixedHue(),
			imgFixedSaturation(),
			imgFixedBrightness(),
			imgDisk1(),
			imgDisk2(),
			imgDisk3(),
			imgRainbow(),
			imgTablecloth(),
			imgPlot()
		};
		
		view.setImageSmoothing(false);
	}
	
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, colorBackground);
		view.drawImageCentered(Vector.ZERO, images[fileIndex]);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
	
}