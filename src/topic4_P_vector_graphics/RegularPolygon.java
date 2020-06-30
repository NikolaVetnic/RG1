package topic4_P_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class RegularPolygon implements Drawing {
	
	@GadgetInteger(min = 3, max = 10)
	int n = 6;
	
	@GadgetDouble(min = 50.0, max = 300.0)
	double r = 200.0;
	
	@GadgetDouble(min = 0.0, max = 1.0)
	double alpha = 0.0;
	
	
	Vector getVertex(int i) {
		return Vector.polar(r, alpha + 1.0 * i / n);
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setStroke(Color.WHITE);
		view.setLineWidth(2);
		
		for (int i = 0; i < n; i++) {
			view.strokeLine(getVertex(i), getVertex(i + 1));
		}
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
