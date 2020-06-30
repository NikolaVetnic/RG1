package topic2_P_image_processing.filters.displacement;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class ZoomMain implements Drawing {
	
	@GadgetDouble(min = 0.0, max = 20.0)
	double k;
	
	@GadgetBoolean
	boolean applyFilter = true;
	
	Image image;
	
	@Override
	public void init(View view) {
		image = new Image("images/fall.jpg");
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		Image filteredImage = new Zoom(k).process(image);
		
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : image);
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
}
