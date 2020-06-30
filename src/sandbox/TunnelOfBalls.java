package sandbox;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class TunnelOfBalls implements Drawing {
	
	@GadgetInteger(min = 1, max = 50)
	private int n = 7;

	private int m = 9;
	
	@GadgetDouble(min = 10.0, max = 25.0)
	private double r = 22.25;
	
	@GadgetDouble(min = 0.0, max = 2.0)
	private double fact = 1.125;
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		m = n+2;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				
				int 	red 	= 90;
				int 	green 	= (int) ((i *  85.0) / n);
				int 	blue 	= (int) ((i * 225.0) / n);
				double 	alpha 	= 1.0 - (0.95 * i) / n;				// dodavanje plave sa povecanjem rastojanja
				
				view.setFill(Color.rgb(red, green, blue, alpha));
				
				// (norm. na [-i/2, i/2] MINUS centriranje kruga) PUTA 
				// rastojanje PUTA pomeranje u dubinu MINUS offset;
				double 	xA 		= +(i    -n/2.0 - 1/2.0) * (2*r) * (fact/i    );
				double 	yA 		= +(j    -m/2.0 - 1/2.0) * (2*r) * (fact/i    );
				double 	xB 		= +((i+1)-n/2.0 - 1/2.0) * (2*r) * (fact/(i+1));
				double 	yB 		= +(j    -m/2.0 - 1/2.0) * (2*r) * (fact/(i+1));
				
				Vector[] points1 = {
					new Vector(xA - 50.0, yA),
					new Vector(50.0 - xA, yA),
					new Vector(50.0 - xB, yB),
					new Vector(xB - 50.0, yB)
				};
				
				Vector[] points2 = {
					new Vector(yA, xA - 50.0),
					new Vector(yA, 50.0 - xA),
					new Vector(yB, 50.0 - xB),
					new Vector(yB, xB - 50.0)
				};
				
				if (j == 1 || j == m) {
					view.fillPolygon(points1[0], points1[1], points1[2], points1[3]);
					view.fillPolygon(points2[0], points2[1], points2[2], points2[3]);
				}

				alpha = 1.0 - (0.75 * i) / n;
				
				view.setFill(Color.rgb(red, green, blue, alpha));

				view.fillCircleCentered(points1[0], r/i);
				view.fillCircleCentered(points1[1], r/i);
				
				view.fillCircleCentered(points2[0], r/i);
				view.fillCircleCentered(points2[1], r/i);
			}
		}
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
