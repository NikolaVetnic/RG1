package excercises;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Touching implements Drawing {
	
	double r = 300;
	
	@GadgetInteger(min = 1, max = 10)
	int n = 3;

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		for (int i = 0; i < n; i++) {
			
			Color f;
			
			f = i % 2 == 0 ? Color.ROYALBLUE : Color.CORNFLOWERBLUE;
			
			view.setFill(f);		
			view.fillCircleCentered(new Vector(0 - i * (r / n), 0), r - r * i / n);
			
			f = i % 2 == 1 ? Color.ROYALBLUE : Color.CORNFLOWERBLUE;
			
			view.setFill(f);
			view.fillCircleCentered(new Vector(r - (2 * i + 1.0) * (r / n), 0), 0.5 * (r / n));
		}
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
