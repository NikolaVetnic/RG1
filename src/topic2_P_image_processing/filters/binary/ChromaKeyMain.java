package topic2_P_image_processing.filters.binary;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class ChromaKeyMain implements Drawing {
	
	@GadgetDouble(min = 0.0, max = 360.0)
	double hue;
	
	@GadgetBoolean
	boolean applyFilter = true;
	
	Image image1, image2;
	
	@Override
	public void init(View view) {
		image1 = new Image("images/meterologist.jpg");
		image2 = new Image("images/forecast.jpg");
	}

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		Image filteredImage = new ChromaKey(hue).process(image1, image2);
		
		view.drawImageCentered(Vector.ZERO, filteredImage);
	}

	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
}
