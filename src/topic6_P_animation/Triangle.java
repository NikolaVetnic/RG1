package topic6_P_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;
import mars.utils.Numeric;

public class Triangle implements Drawing {
	
	@GadgetAnimation
	double time = 0.0;
	
	@GadgetDouble(min = 0.0, max = 1000.0)
	double speed = 300;
	
	@GadgetVector
	Vector p0 = new Vector(-200, -200);
	
	@GadgetVector
	Vector p1 = new Vector(300, -100);
	
	@GadgetVector
	Vector p2 = new Vector(100, 200);

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setLineJoin(StrokeLineJoin.ROUND);
		view.setStroke(Color.DODGERBLUE);
		view.setLineWidth(4);
		view.strokePolygon(p0, p1, p2);
		
		double l0 = p0.distanceTo(p1);
		double l1 = p1.distanceTo(p2);
		double l2 = p2.distanceTo(p0);
		double l  = l0 + l1 + l2;
		
		// vremena normalizujemo tako da je vreme obilazenja trougla jednako 1.0
		double dt0 = l0 / l;
		double dt1 = l1 / l;
		double dt2 = l2 / l;
		
		// momenti prolaska kroz temena trougla
		double t0 = 0.0;
		double t1 = t0 + dt0;
		double t2 = t1 + dt1;
		
		// objasnjenje metoda dato u originalnom fajlu
		double t = Numeric.mod(time * speed / l, 1);
		
		Vector q0, q1;		// polazna i krajnja tacka trenutne stranice
		double k;			// faktor interpolacije
		
		if (t < t1) { q0 = p0; q1 = p1; k = (t - t0) / dt0; } else
		if (t < t2) { q0 = p1; q1 = p2; k = (t - t1) / dt1; } else
					{ q0 = p2; q1 = p0; k = (t - t2) / dt2; }
		
		// racunamo poziciju interpolacijom izmedju pocetne i krajnje tacke
		Vector p = Vector.lerp(q0, q1, k);
		
		view.setFill(Color.ORANGERED);
		view.fillCircleCentered(p, 16);
	}

	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
}
