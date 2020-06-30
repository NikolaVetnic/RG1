package excercises;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.geometry.Vector;
import topic2_P_image_processing.filters.displacement.DisplacementFilter;

class QuadrantFilter extends DisplacementFilter {

	@Override
	public Vector source(Vector dst, Vector dim) {
			
		double x = dst.x;
		double y = dst.y;
		
		if (x < dim.x / 2) {
			
			if (y < dim.y / 2)
				x += dim.x / 2;
			else
				y -= dim.y / 2;
		} else {
			
			if (y < dim.y / 2)
				y += dim.y / 2;
			else
				x -= dim.x / 2;
		}
		
		return new Vector(x, y);
	}
}

public class RotateQuadrants implements Drawing {
	
	@GadgetBoolean
	boolean applyFilter = true;
	
	
	Image image;
	
	
	@Override
	public void init(View view) {
		
		image = new Image("images/Mona Lisa.jpg");
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.drawImageCentered(Vector.ZERO, applyFilter ? new QuadrantFilter().process(image) : image);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(800, 800);
	}
}
