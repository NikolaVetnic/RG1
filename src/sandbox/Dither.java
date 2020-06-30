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
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import topic2_P_image_processing.filters.color.ColorFilter;

class Filter extends ColorFilter {

	@Override
	public Color processColor(Color input) {

		return null;
	}

	class C3 {
		
		int r, g, b;
		
		public C3 (Color c) {
			this.r = (int) (c.getRed() * 255);
			this.g = (int) (c.getGreen() * 255);
			this.b = (int) (c.getBlue() * 255);
		}
		
		public C3 (int c) {
			this.r = c >> 16 & 0xFF;
	        this.g = c >> 8 & 0xFF;
	        this.b = c & 0xFF;
		}
		
		public C3 (int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		public int clamp(int c) {
			return Math.max(0, Math.min(255, c));
		}
		
		public C3 add(C3 o) {
			return new C3(clamp(r + o.r), clamp(g + o.g), clamp(b + o.b));
		}
		
		public C3 sub(C3 o) {
			return new C3(r - o.r, g - o.g, b - o.b);
		}
		
		public C3 mul(double d) {
			return new C3((int) (r * d), (int) (g * d), (int) (b * d));
		}
		
		public int diff(C3 o) {
			int Rdiff = o.r - r;
			int Gdiff = o.g - g;
			int Bdiff = o.b - b;
			
			int distanceSquared = Rdiff * Rdiff + Gdiff * Gdiff + Bdiff * Bdiff;
			
			return distanceSquared;
		}
	}
	
	private static C3 findClosestPaletteColor(C3 c, C3[] palette) {
		
		C3 closest = palette[0];
		
		for (C3 n : palette)
			if (n.diff(c) < closest.diff(c))
				closest = n;
		
		return closest;
	}
	
	public Image process(Image input, int paletteIndex, int pixelSize) {
		
		C3[] palette1 = {
                new C3(  0,   0,   0), // black
                new C3(  0,   0, 255), // green
                new C3(  0, 255,   0), // blue
                new C3(  0, 255, 255), // cyan
                new C3(255,   0,   0), // red
                new C3(255,   0, 255), // purple
                new C3(255, 255,   0), // yellow
                new C3(255, 255, 255)  // white
		};		// 3-bit RGB
		
		C3[] palette2 = {
				new C3( 15,  56,  15), // green
				new C3( 48,  98,  48), // green
				new C3(139, 172,  15), // green
				new C3(155, 188,  15)  // green
		};		// GameBoy
		
		C3[] palette3 = {
                new C3(  0,   0,   0), // black
                new C3(255, 255, 255), // white
                new C3(136,   0,   0), // red
                new C3(170, 255, 238), // cyan
                new C3(204,  68, 204), // violet
                new C3(  0, 204,  85), // green
                new C3(  0,   0, 170), // blue
                new C3(238, 238, 119), // yellow
                new C3(221, 136,  85), // orange
                new C3(102,  68,   0), // brown
                new C3(255, 119, 119), // light red
                new C3( 51,  51,  51), // grey 1
                new C3(119, 119, 119), // grey 2
                new C3(170, 255, 102), // light green
                new C3(  0, 136, 255), // light blue
                new C3(187, 187, 187)  // grey 3
		};		// C64
		
		C3[] palette4 = {
				new C3(  0,   0,   0), // black
				new C3(255, 255, 255), // white
				new C3(255,  85, 255), // magenta
				new C3( 85, 255, 255)  // cyan
		};		// CGA Mode 4 Palette 1
		
		C3[] palette5 = {
				new C3(  0,   0,   0), // black
				new C3(255,  85,  85), // red
				new C3( 85, 255,  85), // green
				new C3(255, 255,  85)  // yellow
		};		// CGA Mode 4 Palette 2
		
		C3[][] paletteArray = { palette1, palette2, palette3, palette4, palette5 };
		
		int w = (int) input.getWidth();
		int h = (int) input.getHeight();
		
		C3[][] d = new C3[w][h];
		C3[][] img = new C3[w][h];
		
		PixelReader pr = input.getPixelReader();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
            	
            	Color c = pr.getColor(x, y);
            	
            	int r = (int) (c.getRed() * 255);
            	int g = (int) (c.getGreen() * 255);
            	int b = (int) (c.getBlue() * 255);
            	
                d[x][y] = new C3(r, g, b);
            }
        }
        
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
            	
            	C3 oldColor = d[x][y];
            	C3 newColor = findClosestPaletteColor(oldColor, paletteArray[paletteIndex]);
            	
            	img[x][y] = new C3(newColor.r, newColor.g, newColor.b);
            	
            	C3 err = oldColor.sub(newColor);
            	
            	if (x + 1 < w) {
            		d[x + 1][y] = d[x + 1][y].add(err.mul(7. / 16.));
            	}
            	
            	if (x - 1 >= 0 && y + 1 < h) {
                    d[x - 1][y + 1] = d[x - 1][y + 1].add(err.mul(3. / 16));
                }
                
                if (y + 1 < h) {
                    d[x][y + 1] = d[x][y + 1].add(err.mul(5. / 16));
                }
                
                if (x + 1 < w && y + 1 < h) {
                    d[x + 1][y + 1] = d[x + 1][y + 1].add(err.mul(1. / 16));
                }
            }
        }
        
        WritableImage output = new WritableImage(w, h);
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				double r = img[x][y].r / 255.;
				double g = img[x][y].g / 255.;
				double b = img[x][y].b / 255.;
				
				pw.setColor(x, y, new Color(r, g, b, 1.0));
			}
		}	
		
		return output;
	}
}


public class Dither implements Drawing {
	
	@GadgetInteger(min = 0, max = 18)
	Integer imageIndex = 0;
	
	@GadgetInteger(min = 1, max = 5)
	Integer pixelSize = 1;	

	@GadgetInteger(min = 0, max = 4)
	Integer paletteIndex = 0;
	
	@GadgetBoolean
	boolean applyFilter = true;
	
	
	String[] fileNames = {
			"screenshot1.jpg",
			"screenshot2.jpg",
			"screenshot3.jpg",
			"screenshot4.jpg",
			"screenshot5.jpg",
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
			"waiting.jpg"
	};

	
	String[] palettes = {
			"3-bit RGB",
			"Game Boy 4-Color Monochrome",
			"Commodore 64",
			"CGA 4-Color Mode #1",
			"CGA 4-Color Mode #2"
	};
	
	
	public Image scaleDown(Image input, int pixelSize) {
		
		int w = (int) input.getWidth() / pixelSize;
		int h = (int) input.getHeight() / pixelSize;
		
		PixelReader pr = input.getPixelReader();
		
		WritableImage output = new WritableImage(w, h);
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				double rSum = 0.0, gSum = 0.0, bSum = 0.0, aSum = 0.0;
				
				for (int i = 0; i < pixelSize; i++) {
					for (int j = 0; j < pixelSize; j++) {
						
						Color c = pr.getColor(x * pixelSize + i, y * pixelSize + j);
						
						rSum += c.getRed();
						gSum += c.getGreen();
						bSum += c.getBlue();
						aSum += c.getOpacity();
					}
				}
				
				int pixelSizeSq = pixelSize * pixelSize;
				
				double r = rSum / pixelSizeSq;
				double g = gSum / pixelSizeSq;
				double b = bSum / pixelSizeSq;
				double a = aSum / pixelSizeSq;
				
				pw.setColor(x, y, new Color(r, g, b, a));
			}
		}
		return output;
	}
	
	
	public Image scaleUp(Image input, int pixelSize) {
		
		int w = (int) input.getWidth() * pixelSize;
		int h = (int) input.getHeight() * pixelSize;
		
		PixelReader pr = input.getPixelReader();
		
		WritableImage output = new WritableImage(w, h);
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < h; y++)
			for (int x = 0; x < w; x++)
				pw.setColor(x, y, pr.getColor(x / pixelSize, y / pixelSize));
		
		return output;
	}

	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		Image image = new Image("images/" + fileNames[imageIndex]);
		Image filteredImage = new Filter().process(scaleDown(image, pixelSize), paletteIndex, 1);
			  filteredImage = scaleUp(filteredImage, pixelSize);
		
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : image);
		
		String paletteCaption = palettes[paletteIndex];
		DrawingUtils.drawInfoText(view, "Image: " + fileNames[imageIndex] + "   Palette: " + paletteCaption);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(1280, 720);
	}
}