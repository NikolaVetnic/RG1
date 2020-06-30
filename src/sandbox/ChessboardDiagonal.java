package sandbox;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class ChessboardDiagonal implements Drawing {
	
	private double d = 100;
	
	@GadgetInteger(min = 2, max = 6)
	private int n = 3;
	
	@GadgetInteger(min = 2, max = 6)
	private int m = 2;
	
	@GadgetDouble(min = 10.0, max = 30.0)
	private double b = 20.0;
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setFill(Color.DARKGREEN);
		view.fillRectCentered(Vector.ZERO, new Vector((m - 1) * d/2.0 + b, (n - 1) * d/2.0 + b));
		
		view.setFill(Color.GREEN);
		view.fillRectCentered(Vector.ZERO, new Vector((m - 1) * d/2.0, (n - 1) * d/2.0));
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				if ((i + j) % 2 == 0) view.setFill(Color.ANTIQUEWHITE);
				else view.setFill(Color.BLACK);
				
				double 	xA = (j - m/2.0 + 1/2.0) * d;
				double	yA = (i - n/2.0 + 1/2.0) * d;
				
				Vector[] points = {
					new Vector(xA + d/2.0, yA),
					new Vector(xA,		   yA + d/2.0),
					new Vector(xA - d/2.0, yA),
					new Vector(xA,		   yA - d/2.0),
					new Vector(xA, 		   yA),
				};
				
				if (j == 0) {
					if (i == n - 1) 	view.fillPolygon(points[0], points[4], points[3]);
					else if (i == 0) 	view.fillPolygon(points[0], points[1], points[4]);
					else 				view.fillPolygon(points[0], points[1], points[3]);
				} 
				else if (j == m - 1) {
					if (i == n - 1)		view.fillPolygon(points[4], points[2], points[3]);
					else if (i == 0)	view.fillPolygon(points[4], points[1], points[2]);
					else				view.fillPolygon(points[1], points[2], points[3]);
				}
				else if (i == n - 1)	view.fillPolygon(points[0], points[2], points[3]);
				else if (i == 0) 		view.fillPolygon(points[0], points[1], points[2]);
				else 					view.fillPolygon(points[0], points[1], points[2], points[3]);
			}
		}
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
