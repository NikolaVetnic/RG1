package excercises;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.utils.Numeric;

public class RotatingPolygon implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetDouble(min = -1.0, max = 1.0)
	double speed = 0.2;
	
	@GadgetInteger(min = 3, max = 10)
	int n = 3;
	
	@GadgetDouble(min = 30.0, max = 300.0)
	double r = 100;
	
	
	void drawCircles(View view) {
		
		view.setFill(Color.DARKRED);
		
		for (int i = 0; i < n; i++) {
			
			double d = Numeric.mod(speed * time, 1.0);
			double e = Numeric.sinT(speed * time) * 0.5 + 0.5;
			
			Vector p = Vector.polar(r, d + (i + 0.0) / n);
			Vector q = Vector.polar(r, d + (i + 0.0) / n + 0.5);
			view.fillCircleCentered(Vector.lerp(p, q, e), 20);
		}
	}
	
	
	void drawPoly(View view) {
		
		view.setFill(Color.CORNFLOWERBLUE);
		
		Vector[] polygon = new Vector[n];
		
		for (int i = 0; i < n; i++) {
			
			double d = Numeric.mod(speed * time, 1.0);
			
			polygon[i] = Vector.polar(r, d + (i + 0.0) / n);
		}
		
		view.fillPolygon(polygon);
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		drawPoly(view);
		drawCircles(view);
	}

	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
}
