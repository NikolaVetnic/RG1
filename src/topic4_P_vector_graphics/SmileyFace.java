package topic4_P_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class SmileyFace implements Drawing {
	
	@GadgetDouble(min = 0.0, max = 200.0)
	double r = 200.0;
	
	@GadgetDouble(min = 0.0, max = 200.0)
	double xEye = 70.0;
	
	@GadgetDouble(min = 0.0, max = 300.0)
	double yEye = 70.0;
	
	@GadgetDouble(min = 0.0, max = 100.0)
	double rEye = 30.0;
	
	@GadgetDouble(min = 0.0, max = 200.0)
	double rMouth = 130.0;
	
	@GadgetDouble(min = 0.0, max = 0.5)
	double phiMouth = 1.0 / 3;

	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setFill(Color.YELLOW);
		view.fillCircleCentered(Vector.ZERO, r);
		
		view.setFill(Color.BLACK);
		view.fillCircleCentered(new Vector(-xEye, yEye), rEye);
		view.fillCircleCentered(new Vector( xEye, yEye), rEye);
		
		view.setLineWidth(15);
		view.setStroke(Color.BLACK);
		view.strokeArcCentered(Vector.ZERO, new Vector(rMouth), 0.75 - phiMouth / 2, phiMouth);
	}
	
	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
