package topic4_P_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Chessboard implements Drawing {
	
	@GadgetInteger(min = 1, max = 20)
	int n = 8;
	
	@GadgetInteger(min = 1, max = 20)
	int m = 8;
	
	@GadgetDouble(min = 0.0, max = 100.0)
	double d = 50.0;
	
	@GadgetColorPicker
	Color c1 = Color.hsb(30, 0.4, 0.4);
	
	@GadgetColorPicker
	Color c2 = Color.hsb(30, 0.4, 0.6);
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				Color c = (i + j) % 2 == 0 ? c1 : c2;
				
				view.setFill(c);
				view.fillRect(new Vector(d).mul(new Vector(j - m/2.0, i - n/2.0)), new Vector(d));
			}
		}
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
