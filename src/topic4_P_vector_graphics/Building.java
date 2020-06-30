package topic4_P_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Building implements Drawing {
	
	@GadgetInteger(min = 1, max = 10)
	int n = 3;
	
	@GadgetInteger(min = 1, max = 10)
	int m = 4;
	
	
	Vector w = new Vector(30, 20);
	Vector g = new Vector(10, 10);
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		// žuti zid
		Vector r = w.mul(new Vector(m, n)).add(g.mul(new Vector(m + 1, n + 1))).div(2);
		
		view.setFill(Color.hsb(50, 0.6, 0.9));
		view.fillRectCentered(Vector.ZERO, r);
		
		// prozori
		view.setFill(Color.hsb(210, 0.5, 0.9));
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				view.fillRect((w.add(g).mul(new Vector(j, i))).add(g).sub(r), w);
			}
		}
		
		// krov
		view.setFill(Color.hsb(10, 0.6, 0.9));
		view.fillPolygon(
				r,
				r.mul(new Vector(-1, 1)),
				r.mul(new Vector(0, 1)).add(new Vector(0, 40))
				);

	}
	
	
	public static void main(String[] args) {
		
		DrawingApplication.launch(800, 800);
	}
}
