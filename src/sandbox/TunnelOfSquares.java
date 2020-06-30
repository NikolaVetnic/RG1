package sandbox;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;

public class TunnelOfSquares implements Drawing {
	
	@GadgetDouble(min = 20.0, max = 200.0)
	private double a = 50;
	
	@GadgetDouble(min = 0.0, max = 1.0)
	double opacity = 1.0;
	
	@GadgetInteger(min = 5, max = 50)
	int len = 10;
	
	@GadgetVector
	Vector p = Vector.ZERO;
	
	double cage = 250.0;
	
	private void drawTunnel(View view) {
		
		double[] scale = new double[len];
		scale[0] = 1.0;
		
		double[] shift = new double[len];
		shift[0] = 1.0;
		
		for (int i = 1; i < len; i++) {
			scale[i] = 1.0 / (i+1) + scale[i-1];
			shift[i] = 1.0 / (i+1);
		}
		
		for (int i = len - 1; i >= 0; i--) {
			
			Vector[] points = {
					new Vector(-a * scale[i], +a * scale[i]).add(p.mul(shift[i])),
					new Vector(+a * scale[i], +a * scale[i]).add(p.mul(shift[i])),
					new Vector(+a * scale[i], -a * scale[i]).add(p.mul(shift[i])),
					new Vector(-a * scale[i], -a * scale[i]).add(p.mul(shift[i]))
			};
			
			view.setStroke(Color.rgb(150, 150, 150, (0.25 * (len - i + 1)) / len));
			view.strokePolygon(points[0], points[1], points[2], points[3]);
			
			if (i != 0) {
				
				Vector[] morePoints = {
					new Vector(-a * scale[i-1], +a * scale[i-1]).add(p.mul(shift[i-1])),
					new Vector(+a * scale[i-1], +a * scale[i-1]).add(p.mul(shift[i-1])),
					new Vector(+a * scale[i-1], -a * scale[i-1]).add(p.mul(shift[i-1])),
					new Vector(-a * scale[i-1], -a * scale[i-1]).add(p.mul(shift[i-1]))
				};

				int 	red 	= 40;
				int 	green 	= (int) ((i *  85.0) / len);
				int 	blue1 	= (int) ((i * 225.0) / len);
				int		blue2   = (int) ((i * 175.0) / len);
				double 	alpha 	= opacity * (1.0 * (len - i + 1)) / len;
				
				Color c1 = Color.rgb(red, green, blue1, alpha);
				Color c2 = Color.rgb(red + 50, green, blue2, alpha);
				
				if (morePoints[0].x > points[0].x) view.setFill(c1);
				else view.setFill(c2);
				view.fillPolygon(points[0], morePoints[0], morePoints[3], points[3]);
				
				if (morePoints[2].x < points[2].x) view.setFill(c1);
				else view.setFill(c2);
				view.fillPolygon(morePoints[1], points[1], points[2], morePoints[2]);
				
				if (morePoints[1].y > points[1].y) view.setFill(c1);
				else view.setFill(c2);
				view.fillPolygon(points[0], points[1], morePoints[1], morePoints[0]);
				
				if (morePoints[3].y < points[3].y) view.setFill(c1);
				else view.setFill(c2);
				view.fillPolygon(morePoints[3], morePoints[2], points[2], points[3]);
			}
		}
	}

	@Override
	public void draw(View view) {
		view.stateStore();
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setFill(Color.gray(0.375));
		view.fillRectCentered(Vector.ZERO, new Vector(cage + 10, cage + 10));
		view.setFill(Color.gray(0.25));
		view.fillRectCentered(Vector.ZERO, new Vector(cage, cage));
		
		drawTunnel(view);
		
		view.stateRestore();
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
