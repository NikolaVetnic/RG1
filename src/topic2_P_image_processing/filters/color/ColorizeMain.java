package topic2_P_image_processing.filters.color;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class ColorizeMain implements Drawing {
	
	@GadgetBoolean
	boolean applyFilter = true;
	
	@GadgetDouble(min = 0.0, max = 360.0)
	double hue;
	
	@GadgetDouble(min = 0.0, max = 1.0)
	double sat;
	
	private Image image;
	
	@Override
	public void init(View view) {
		
		image = new Image("images/Mona Lisa.jpg");
	}

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		Image filteredImage = new Colorize(hue, sat).process(image);
		
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : image);
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}

}
