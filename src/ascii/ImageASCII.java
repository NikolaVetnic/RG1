package ascii;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.geometry.Vector;

public class ImageASCII {

	
	CharSheet cs;

	Color[][] colorMap;
	WritableImage[][] blocks;
	
	
	public ImageASCII(Image img, CharSheet cs) {
		
		int width = (int) img.getWidth() / (int) CharSheet.CHAR_SIZE.x;
		int height = (int) img.getHeight() / (int) CharSheet.CHAR_SIZE.y;
		
		colorMap = new Color[width][height];
		blocks = new WritableImage[width][height];
		
		PixelReader pr = img.getPixelReader();
		
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				
				double r = 0.0, g = 0.0, b = 0.0;
				int pxCount = 0;
				
				blocks[i][j] = new WritableImage(cs.charX(), cs.charY());
				PixelWriter pw = blocks[i][j].getPixelWriter();
				
				for (int y = 0; y < (int) CharSheet.CHAR_SIZE.y; y++) {
					for (int x = 0; x < (int) CharSheet.CHAR_SIZE.x; x++) {
						
						Color c = pr.getColor(
								(int) (i * CharSheet.CHAR_SIZE.x + x), 
								(int) (j * CharSheet.CHAR_SIZE.y + y));
						
						r += c.getRed();
						g += c.getGreen();
						b += c.getBlue();
						
						pw.setColor(x, y, c);
					}
				}
				
				colorMap[i][j] = Color.rgb(
						(int) (255 * r / CharSheet.CHAR_SIZE.area()), 
						(int) (255 * g / CharSheet.CHAR_SIZE.area()), 
						(int) (255 * b / CharSheet.CHAR_SIZE.area()));
			}
		}
		
		this.cs = cs;
	}
	
	
	public ImageASCII(String path, CharSheet cs) {
		this(new Image(path), cs);
	}

	
	public Image drawColorMap() {	// for testing purposes
		
		WritableImage wi = new WritableImage(		
				(int) (width() * CharSheet.CHAR_SIZE.x), 
				(int) (height() * CharSheet.CHAR_SIZE.y));
		PixelWriter pw = wi.getPixelWriter();
		
		for (int j = 0; j < height(); j++)
			for (int i = 0; i < width(); i++)
				for (int y = 0; y < CharSheet.CHAR_SIZE.y; y++)
					for (int x = 0; x < CharSheet.CHAR_SIZE.x; x++)
						pw.setColor(
								(int) (i * CharSheet.CHAR_SIZE.x + x), 
								(int) (j * CharSheet.CHAR_SIZE.y + y), 
								colorMap[i][j]);
		
		return wi;
	}
	
	
	public Image drawImage() {		// for testing purposes

		WritableImage wi = new WritableImage(		
				(int) (width() * CharSheet.CHAR_SIZE.x), 
				(int) (height() * CharSheet.CHAR_SIZE.y));
		PixelWriter pw = wi.getPixelWriter();
		
		for (int j = 0; j < height(); j++)
			for (int i = 0; i < width(); i++) {
				
				PixelReader pr = blocks[i][j].getPixelReader();
				
				for (int y = 0; y < CharSheet.CHAR_SIZE.y; y++)
					for (int x = 0; x < CharSheet.CHAR_SIZE.x; x++)
						pw.setColor(
								(int) (i * CharSheet.CHAR_SIZE.x + x), 
								(int) (j * CharSheet.CHAR_SIZE.y + y), 
								pr.getColor(x, y));
			}
		
		return wi;
	}

	
	public Image getImage(String s) {
		return toASCII(null, s, 0.0);
	}
	
	
	public Image getImage(Color c, String s) {
		return toASCII(c, s, 0.0);
	}
	
	
	public Image getImage(String s, double margin) {
		return toASCII(null, s, margin);
	}

	
	public Image getImage(Color c, String s, double margin) {
		return toASCII(c, s, margin);
	}
	
	private Image toASCII(Color c, String s, double margin) {

		WritableImage out = new WritableImage(width() * cs.charX(), height() * cs.charY());
		PixelWriter pw = out.getPixelWriter();
		
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				
				double val = value(i, j);
				
				Image currChar = null;
				
				if (s.equals("closest"))	currChar = getClosestToValue(val, true);
				if (s.equals("jitter"))		currChar = getRandomNearValue(val, new Random());
				if (s.equals("random"))		currChar = getRandomNearValue(val, new Random(12358715423L));
				if (s.equals("matching"))	currChar = getMatching(blocks[i][j], margin);

				PixelReader pr = currChar.getPixelReader();
				
				for (int y = 0; y < cs.charY(); y++) {
					for (int x = 0; x < cs.charX(); x++) {
						
						Color charColor = pr.getColor(x, y).equals(Color.TRANSPARENT) ?
										  Color.TRANSPARENT : c != null ? c : colorMap[i][j];
						
						pw.setColor(i * cs.charX() + x, j * cs.charY() + y, charColor);
					}
				}
			}
		}
		
		return out;
	}
	
	
	private Image getMatching(WritableImage block, double margin) {
		
		Vector p = Vector.ZERO;
		double match = Double.NEGATIVE_INFINITY;
		
		ArrayList<Vector> outputs = new ArrayList<>();
		
		for (int i = 0; i < cs.sheetX(); i++) {
			for (int j = 0; j < cs.sheetY(); j++) {
				
				if (cs.isBad(i, j)) continue;
				
				double curr = compareToChar(block, cs.character(new Vector(i, j)));
				
				if (curr > margin)
					outputs.add(new Vector(i, j));
			}
		}
		
		return cs.character(outputs.get(new Random().nextInt(outputs.size())));
	}


	private double compareToChar(WritableImage block, Image character) {
		
		PixelReader p0 = block.getPixelReader();
		PixelReader p1 = character.getPixelReader();
		
		double sum = 0.0;
		
		for (int y = 0; y < block.getHeight(); y++)
			for (int x = 0; x < block.getWidth(); x++) {
				
				double v = p0.getColor(x, y).getBrightness();
				double u = p1.getColor(x, y).getBrightness();
					   
				v = v > 0.5 ? 1.0 : 0.0;
				
				if (Math.abs(v - u) > 1e-8)
					sum += 1.0;
			}
		
		return sum / cs.charArea();
	}


	private Image getClosestToValue(double val, boolean fromBelow) {

		Vector pMin = Vector.ZERO, 
			   pMax = Vector.ZERO;
		
		double min = Double.NEGATIVE_INFINITY, 
			   max = Double.POSITIVE_INFINITY;
		
		for (int i = 0; i < cs.sheetX(); i++) {
			for (int j = 0; j < cs.sheetY(); j++) {
				
				// Are we taking the first char with value lower than argument...
				if (min < cs.values[i][j] && cs.values[i][j] <= val) {
					min = cs.values[i][j];
					pMin = new Vector(i, j);
				}
				
				// Are we taking the first char with value higher than argument...
				if (val < cs.values[i][j] && cs.values[i][j] <= max) {
					max = cs.values[i][j];
					pMax = new Vector(i, j);
				}
			}
		}
		
		if (fromBelow)	return cs.character(pMin);
		else			return cs.character(pMax);
	}
	
	
	private Image getRandomNearValue(double val, Random rng) {
		
		double deltaL = 0.025;
		double deltaH = 0.090;
		
		double border = 0.250;
		
		ArrayList<Vector> outputs = new ArrayList<>();
		
		for (int i = 0; i < cs.sheetX(); i++) {
			for (int j = 0; j < cs.sheetY(); j++) {
				
				double delta = cs.values[i][j] > border ? deltaH : deltaL;
				
				if (val - delta < cs.values[i][j] && cs.values[i][j] <= val + delta)
					outputs.add(new Vector(i, j));
			}
		}
		
		if (outputs.isEmpty())
			return cs.character(Vector.ZERO);
		else
			return cs.character(
					outputs.get(rng.nextInt(outputs.size())));
	}
	

	public void printValues() {
		
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				System.out.printf("%4.2f ", colorMap[i][j].getBrightness());
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public void printColorMap() {
		
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				System.out.print(colorMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}


	public int width() 					{ return colorMap.length; 					}
	public int height() 				{ return colorMap[0].length; 				}
	public double value(int x, int y) 	{ return colorMap[x][y].getBrightness();	}
}
