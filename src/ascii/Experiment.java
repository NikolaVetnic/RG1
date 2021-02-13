package ascii;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.utils.Numeric;

public class Experiment implements Drawing {
	
	
	public static final int CHAR_WIDTH 	= 8  ;
	public static final int CHAR_HEIGHT = 16 ;
	public static final int CHAR_AREA	= 128;
	public static final int CHARS_X 	= 32 ;	// Number of chars per row in char sheet.
	public static final int CHARS_Y 	= 8  ;	// Number of chars per col in char sheet.
	
	
	@GadgetAnimation(speed = 0.25)
	double time = 0;
	
//	@GadgetInteger(min = 0, max = 255)
//	int n = 0;
	
//	@GadgetDouble(min = 0.0, max = 1.0)
//	double d = 0.0;
	
	@GadgetBoolean
	boolean displayImg = true;
	
	
	Color[][] colorsImg;
	Color[][] colorsVal;
	
	double[][] brightnessImg;
	double[][] brightnessVal;
	

	@GadgetInteger(min = 0, max = 14)
	Integer imageIndex = 0;
	
	
	String[] fileNames = {
			"Mona Lisa.jpg",
			"npv_fb.jpg",
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
	
	
	@Override
	public void init(View view) {
		
		// Load char sheet image.
		Image im = new Image("images/charsheet_transparent.png");
		PixelReader pr = im.getPixelReader();
		
		// Image brightness, as 2D array.
		brightnessImg = new double[(int) im.getWidth()][(int) im.getHeight()];
		
		// Average brightness of the image area corresponding to char size.
		brightnessVal = new double[CHARS_X][CHARS_Y];
		
		for (int y = 0; y < im.getHeight(); y++)
			for (int x = 0; x < im.getWidth(); x++)
				brightnessImg[x][y] = pr.getColor(x, y).getBrightness();
		
		for (int i = 0; i < CHARS_X; i++)
			for (int j = 0; j < CHARS_Y; j++)
				brightnessVal[i][j] = getValueAt(i, j);
	}
	
	
	public Image getCharAt(int x, int y) {
		
		WritableImage im = new WritableImage(CHAR_WIDTH, CHAR_HEIGHT);
		PixelWriter pw = im.getPixelWriter();
		
		for (int j = y * CHAR_HEIGHT; j < (y + 1) * CHAR_HEIGHT; j++)
			for (int i = x * CHAR_WIDTH; i < (x + 1) * CHAR_WIDTH; i++)
				pw.setColor(
						i - x * CHAR_WIDTH, 
						j - y * CHAR_HEIGHT, 
						brightnessImg[i][j] > 0.0 ? Color.gray(brightnessImg[i][j]) : Color.TRANSPARENT);
//						colorsVal[x][y] != null ? colorsVal[x][y] : Color.TRANSPARENT);
		
		return im;
	}
	
	
	public double getValueAt(int x, int y) {
		
		Image im = getCharAt(x, y);
		PixelReader pr = im.getPixelReader();
		
		int color = 0;
		
		for (int j = 0; j < im.getHeight(); j++)
			for (int i = 0; i < im.getWidth(); i++)
				color = pr.getColor(i, j).equals(Color.TRANSPARENT) ? color : color + 1;
				
		return 1.0 * color / CHAR_AREA;
	}
	
	
	public Color getColorAt(int x, int y) {
		
		Image im = getCharAt(x, y);
		PixelReader pr = im.getPixelReader();
		
		double r = 0, g = 0, b = 0;
		
		for (int j = 0; j < im.getHeight(); j++)
			for (int i = 0; i < im.getWidth(); i++) {
				Color c = pr.getColor(i, j);
				
				r += c.getRed();
				g += c.getGreen();
				b += c.getBlue();
			}
				
		Color out = Color.rgb((int) (255 * r / CHAR_AREA), (int) (255 * g / CHAR_AREA), (int) (255 * b / CHAR_AREA));
		
		return out.getBrightness() < 1e-8 ? Color.TRANSPARENT : out;
	}
	
	
	public Image getFirstWithValue(double v) {
		
		Vector pMin = Vector.ZERO, pMax = Vector.ZERO;
		
		double min = 0.0, max = 1.0;
		
		for (int i = 0; i < brightnessVal.length; i++) {
			for (int j = 0; j < brightnessVal[0].length; j++) {
				
				if (min < brightnessVal[i][j] && brightnessVal[i][j] <= v) {
					min = brightnessVal[i][j];
					pMin = new Vector(i, j);
				}
				
				if (v < brightnessVal[i][j] && brightnessVal[i][j] <= max) {
					max = brightnessVal[i][j];
					pMax = new Vector(i, j);
				}
			}
		}
		
		int x = (int) pMin.x, y = (int) pMin.y;
		
		return getCharAt(x, y);
	}
	
	
	public Image getRandomNearValue(double v) {
		
		double delta0 = 0.025;
		double delta1 = 0.10;
		
//		Vector pMin = Vector.ZERO, pMax = Vector.ZERO;
//		
//		double min = 0.0, max = 1.0;
		
		ArrayList<Vector> res = new ArrayList<>();
		
		for (int i = 0; i < brightnessVal.length; i++) {
			for (int j = 0; j < brightnessVal[0].length; j++) {
				
				double delta = brightnessVal[i][j] > 0.25 ? delta1 : delta0;
				
				if ((v - delta < brightnessVal[i][j] && brightnessVal[i][j] <= v) ||
					(v < brightnessVal[i][j] && brightnessVal[i][j] <= v + delta)) {
//					min = value[i][j];
//					pMin = new Vector(i, j);
					res.add(new Vector(i, j));
				}
			}
		}
		
		Vector p = null;
		
		if (res.isEmpty()) p = Vector.ZERO;
		else p = res.get(new Random().nextInt(res.size()));
		
		int x = (int) p.x, y = (int) p.y;
		
		return getCharAt(x, y);
	}
	
	
	public Image convert(Image in) {
		
		PixelReader pr = in.getPixelReader();
		
		int width = (int) in.getWidth() / CHAR_WIDTH - 1;
		int height = (int) in.getHeight() / CHAR_HEIGHT - 1;
		
		
		
		colorsVal = new Color[width][height];
		
		
		
		
		
		Image[][] charArr = new Image[width][height];
		
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				
				int x0 = w * CHAR_WIDTH, x1 = (w + 1) * CHAR_WIDTH;
				int y0 = h * CHAR_HEIGHT, y1 = (h + 1) * CHAR_HEIGHT;
				
				double sum = 0.0, r = 0.0, g = 0.0, b = 0.0;
				
				
				
				for (int i = x0; i < x1; i++)
					for (int j = y0; j < y1; j++) {
						sum += pr.getColor(i, j).getBrightness();
						
						r += pr.getColor(i, j).getRed();
						g += pr.getColor(i, j).getGreen();
						b += pr.getColor(i, j).getBlue();
					}
				
				Color c = Color.rgb(
						(int) (255 * r / (CHAR_WIDTH * CHAR_HEIGHT)), 
						(int) (255 * g / (CHAR_WIDTH * CHAR_HEIGHT)), 
						(int) (255 * b / (CHAR_WIDTH * CHAR_HEIGHT)));
				
//				valArr[w][h] = sum / CHAR_AREA;
//				charArr[w][h] = getFirstWithValue(sum / CHAR_AREA);
				charArr[w][h] = getRandomNearValue(sum / CHAR_AREA);
			}
		}
		
		WritableImage out = new WritableImage(width * CHAR_WIDTH, height * CHAR_HEIGHT);
		PixelWriter pw = out.getPixelWriter();
		
		for (int i = 0; i < charArr.length; i++) {
			for (int j = 0; j < charArr[0].length; j++) {
				
				Image im = charArr[i][j];
				pr = im.getPixelReader();
				
				for (int y = 0; y < im.getHeight(); y++) {
					for (int x = 0; x < im.getWidth(); x++) {
						pw.setColor(i * CHAR_WIDTH + x, j * CHAR_HEIGHT + y, pr.getColor(x, y));
					}
				}
			}
		}
		
		return out;
	}
	
	
	
	public Image convert2(Image in) {
		
		PixelReader pr = in.getPixelReader();
		
		int width = (int) in.getWidth() / CHAR_WIDTH - 1;
		int height = (int) in.getHeight() / CHAR_HEIGHT - 1;
		
		Color[][] cc = new Color[width][height];
		Image[][] charArr = new Image[width][height];
		
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				
				int x0 = w * CHAR_WIDTH, x1 = (w + 1) * CHAR_WIDTH;
				int y0 = h * CHAR_HEIGHT, y1 = (h + 1) * CHAR_HEIGHT;
				
				double sum = 0.0, r = 0.0, g = 0.0, b = 0.0;
				
				for (int i = x0; i < x1; i++)
					for (int j = y0; j < y1; j++) {
						
						sum += pr.getColor(i, j).getBrightness();
						
						r += pr.getColor(i, j).getRed();
						g += pr.getColor(i, j).getGreen();
						b += pr.getColor(i, j).getBlue();
					}
				
				cc[w][h] = Color.rgb(
						(int) (255 * r / (CHAR_WIDTH * CHAR_HEIGHT)), 
						(int) (255 * g / (CHAR_WIDTH * CHAR_HEIGHT)), 
						(int) (255 * b / (CHAR_WIDTH * CHAR_HEIGHT)));
				
//				valArr[w][h] = sum / CHAR_AREA;
//				charArr[w][h] = getFirstWithValue(sum / CHAR_AREA);
				charArr[w][h] = getRandomNearValue(sum / CHAR_AREA);
			}
		}
		
		WritableImage out = new WritableImage(width * CHAR_WIDTH, height * CHAR_HEIGHT);
		PixelWriter pw = out.getPixelWriter();
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				Image im = charArr[i][j];
				pr = im.getPixelReader();
				
				for (int y = 0; y < CHAR_HEIGHT; y++) {
					for (int x = 0; x < CHAR_WIDTH; x++) {
						pw.setColor(i * CHAR_WIDTH + x, j * CHAR_HEIGHT + y, 
								pr.getColor(x, y).getBrightness() > 0.5 ? cc[i][j] : Color.TRANSPARENT);
					}
				}
			}
		}
		
		return out;
	}
	
	
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.0));
		
		WritableImage im = new WritableImage(480, 480);
		PixelWriter pw = im.getPixelWriter();
		
		for (int j = 0; j < im.getHeight(); j++)
			for (int i = 0; i < im.getWidth(); i++) {
				
				double xx = 1.0 * (i * 2.0 - im.getWidth()) / im.getWidth();
				double yy = 1.0 * (j * 2.0 - im.getHeight()) / im.getHeight();
				double dd = Math.sqrt(xx * xx + yy * yy);
				
				dd = dd > 1.0 ? 1.0 : dd;
				
				pw.setColor(i, j, Color.gray(1.0 * (1 - dd) * (0.5 * Numeric.cosT(time) + 0.5)));
			}
		
		Image photo = new Image("images/" + fileNames[imageIndex]);
		
//		view.drawImageCentered(Vector.ZERO, convert(im));
//		
//		if (displayImg) view.drawImageCentered(Vector.ZERO, im);
		
		view.drawImageCentered(Vector.ZERO, convert2(photo));
		if (displayImg) view.drawImageCentered(Vector.ZERO, photo);
		
//		view.drawImageCentered(Vector.ZERO, getCharAt(n % CHARS_X, n / CHARS_X), 4.0);
//		System.out.println(getValueAt(n % CHARS_X, n / CHARS_X));
		
//		view.drawImageCentered(Vector.ZERO, getFirstWithValue(d), 4.0);
	}

	public static void main(String[] args) {
		DrawingApplication.launch(640, 640);
	}
}
