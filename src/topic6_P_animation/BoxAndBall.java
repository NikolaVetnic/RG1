package topic6_P_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;

public class BoxAndBall implements Drawing {
	
	@GadgetAnimation
	double time = 0.0;
	
	double dt0 = 1.0;	// podizanje poklopca
	double dt1 = 2.0;	// ubacivanje loptice
	double dt2 = 1.0;	// spustanje poklopca
	
	double time0 = 0.0;
	double time1 = time0 + dt0;
	double time2 = time1 + dt1;
	double time3 = time2 + dt2;
	
	
	double smoothStep(double x) {
		return x * x * (3 - 2 * x);
	}
	
	
	double tBall(double time) {
		
		if (time < time1) return 0;
		if (time < time2) return smoothStep((time - time1) / dt1);
						  return 1;
	}
	

	double tBox(double time) {
		
		if (time < time0) return 0.0;
		if (time < time1) return smoothStep(time / dt0);
		if (time < time2) return 1.0;
		if (time < time3) return smoothStep(1 - (time - time2) / dt2);
						  return 0.0;
	}
	
	
	void drawBall(View view, double t) {
		
		Vector p = Vector.lerp(new Vector(0, 150), new Vector(0, -50), t);
		
		view.setFill(Color.hsb(0, 0.6, 1.0));
		view.fillCircleCentered(p, 40);
	}
	
	
	void drawBox(View view, double t) {
		
		double phi = 0.30 * t;
		
		Vector a = new Vector( 50,    0);
		Vector b = new Vector( 50, -100);
		Vector c = new Vector(-50, -100);
		Vector d = new Vector(-50,    0);
		Vector e = d.add(Vector.polar(100, phi));
		
		view.setLineCap(StrokeLineCap.ROUND);
		view.setLineJoin(StrokeLineJoin.ROUND);
		view.setLineWidth(4);

		view.setFill  (Color.hsb(120, 0.5, 0.6));
		view.setStroke(Color.hsb(120, 0.5, 1.0));

		view.fillPolygon   (a, b, c, d);
		view.strokePolyline(a, b, c, d, e);
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		drawBall(view, tBall(time));
		drawBox (view, tBox (time));
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
