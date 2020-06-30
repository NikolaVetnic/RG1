package topic2_P_image_processing.filters.color;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class AccentMain implements Drawing {

	@GadgetInteger(min = 0, max = 360)
	int hue = 0;
	
	@GadgetBoolean
	boolean applyFilter = true;

	private Image image;
	
	@Override
	public void init(View view) {
		image = new Image("images/Mona Lisa.jpg");
	}
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		Image filteredImage = new Accent(hue).process(image);
		
		view.drawImageCentered(Vector.ZERO, applyFilter ? filteredImage : image);
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
}
