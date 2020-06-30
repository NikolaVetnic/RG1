package excercises;

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
import mars.geometry.Vector;
import mars.random.fixed.continuous.PerlinNoise;
import topic2_P_image_processing.filters.binary.BinaryFilter;

class PerlinMixerFilter extends BinaryFilter {

	final double value;
	final double band;
	final double zoom;
	
	
	public PerlinMixerFilter(double value, double band, double zoom) {
		this.value = value;
		this.band = band;
		this.zoom = zoom;
	}
	

	@Override
	public Image process(Image input1, Image input2) {
		
		PerlinNoise pn = new PerlinNoise(6238957209870546723l);

		PixelReader pr1 = input1.getPixelReader();
		PixelReader pr2 = input2.getPixelReader();

		WritableImage image = new WritableImage((int) input1.getWidth(), (int) input1.getHeight());
		PixelWriter pw = image.getPixelWriter();
		
		for (int y = 0; y < input1.getHeight(); y++) {
			for (int x = 0; x < input1.getWidth(); x++) {
				
				double p = (pn.getValue(zoom * x / input1.getWidth(), zoom * y / input1.getHeight(), 1.0) + 1.0) / 2.0;
				
				if (p < value - band) {
					
					pw.setColor(x, y, pr1.getColor(x, y));
				} else if (value - band <= p && p <= value + band) {
					
					double d = (p - (value - band)) / (2 * band);
					pw.setColor(x, y, pr1.getColor(x, y).interpolate(pr2.getColor(x, y), d));
				} else {
					
					pw.setColor(x, y, pr2.getColor(x, y));
				}
			}
		}
		
		return image;
	}
	
}

public class PerlinMixer implements Drawing {
	
	@GadgetDouble(min = 0.0, max = 1.0)
	double value = 0.5;
	
	@GadgetDouble(min = 0.0, max = 0.5)
	double band = 0.15;
	
	@GadgetDouble(min = 0.1, max = 10.0)
	double zoom = 5.0;
	
	
	Image input1, input2;
	
	
	@Override
	public void init(View view) {
		
		input1 = new Image("images/soil.jpg");
		input2 = new Image("images/grass.jpg");
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		Image filteredImage = new PerlinMixerFilter(value, band, zoom).process(input1, input2);
		
		view.drawImageCentered(Vector.ZERO, filteredImage);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
