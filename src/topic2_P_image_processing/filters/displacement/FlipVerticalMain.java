package topic2_P_image_processing.filters.displacement;

import javafx.scene.image.Image;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.geometry.Vector;

public class FlipVerticalMain implements Drawing {
	
	@GadgetBoolean
	boolean applyFilter = true;
	
	Image image;
	
	@Override
	public void init(View view) {
		image = new Image("images/fall.jpg");
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, javafx.scene.paint.Color.hsb(0, 0, 0.25));
		
		Image filteredImage = new FlipVertical().process(image);
		
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : image);
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
}
