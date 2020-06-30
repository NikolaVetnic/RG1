package topic6_P_animation;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Waiting1 implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetInteger(min = 1, max = 10)
	int n = 7;
	
	@GadgetDouble(min = 50.0, max = 300.0)
	double r = 75.0;
	
	@GadgetDouble(min = -1.0, max = 1.0)
	double rotationalSpeed = 0.15;
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		for (int i = 0; i < n; i++) {
			
			view.setFill(Color.gray((time + (i + 0.0) / n) % 1.0 * 0.5 + 0.25));
			view.fillCircleCentered(Vector.polar(r, (i + 0.0) / n + time * rotationalSpeed), 20);
		}
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
