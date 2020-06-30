package excercises;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class Dog implements Drawing {
	
	@GadgetAnimation
	double time = 0.0;
	
	@GadgetDouble(min = 0.0, max = 1.0)
	double speed = 0.15;
	
	
	double stateDuration = 1.0;
	
	
	Vector[][] verts = new Vector[][] {
		{ new Vector( 125,  150), new Vector( -125,  150), new Vector( -125,  -175), new Vector( 125, -175) },	// telo
		{ new Vector( 225,  165), new Vector(   75,  165), new Vector(   75,   115), new Vector( 225,  115) },	// desno uho
		{ new Vector( -15,   65), new Vector(  -60,   65), new Vector(  -60,    15), new Vector( -15,   15) },	// levo oko
		{ new Vector(  65,   55), new Vector(   25,   55), new Vector(   25,     5), new Vector(  65,    5) },  // desno oko
		{ new Vector(  50,  -45), new Vector(  -40,  -45), new Vector(  -40,   -80), new Vector(  50,  -80) },	// njuska
		{ new Vector(  80, -145), new Vector(  -60, -145), new Vector(  -60,  -220), new Vector(  80, -220) },	// usta
		{ new Vector( -25,  175), new Vector( -175,  175), new Vector( -175,   125), new Vector( -25,  125) },	// levo uho
	};
	
	
	Color[] colors = new Color[] {
		Color.YELLOW,	
		Color.BLACK,
		Color.BLACK,
		Color.BLACK,
		Color.RED,
		Color.BLACK,
		Color.BLACK
	};
	
	
	double f(double x) {
		// 6x^5 - 15x^4 + 10x^3
		return x * x * x * (x * (x * 6 - 15) + 10);
	}
	
	
	void strokePieces(View view, int start) {
		
		int k0 = (int) ((time / stateDuration) * speed + start) % verts.length;
		int k1 = (k0 + 1) % verts.length;
		
		double t = f((speed * time) % stateDuration);
		
		Vector[] poly = new Vector[4];
		for (int i = 0; i < 4; i++) {
			poly[i] = Vector.lerp(verts[k0][i], verts[k1][i], t);
		}
		
		Color c0 = colors[k0 % verts.length];
		Color c1 = colors[k1 % verts.length];
		
		view.setLineWidth(15);
		view.setStroke(c0.interpolate(c1, t));
		view.strokePolygon(poly);		
	}


	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.hsb(120, 0.35, 0.25));
		
		for (int i = 0; i < verts.length; i++)
			strokePieces(view, i);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
