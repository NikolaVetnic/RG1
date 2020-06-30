package topic4_P_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;

public class RectTest implements Drawing {

	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setFill(Color.YELLOW);
		view.fillRectCentered(Vector.ZERO, new Vector (120, 120));
		
		view.setFill(Color.BLUEVIOLET);
		view.fillRectCentered(new Vector(55, 55), new Vector(50, 50));
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
