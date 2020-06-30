package topic4_P_vector_graphics;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Transformation;
import mars.geometry.Vector;

public class SquishyFace implements Drawing {
	
	double r = 50.0;
	double xEye = 17.5;
	double yEye = 17.5;
	double rEye = 7.5;
	double rMouth = 32.5;
	double phiMouth = 1.0/3; 
	
	double rWall = 350;
	
	
	@GadgetVector
	Vector p = Vector.ZERO;
	
	
	private void drawFace(View view) {
		
		view.setFill(Color.hsb(60, 0.9, 0.9));
		view.fillCircleCentered(new Vector(0, 0), r);
		
		view.setFill(Color.hsb(0, 0, 0));
		view.fillCircleCentered(new Vector(-xEye, yEye), rEye);
		view.fillCircleCentered(new Vector(+xEye, yEye), rEye);
		
		view.setLineWidth(7.5);
		view.setStroke(Color.hsb(0, 0, 0));
		view.strokeArcCentered(new Vector(0, 0), new Vector(rMouth), 0.75 - phiMouth / 2.0, phiMouth);
	}

	
	@Override
	public void draw(View view) {
		
		view.stateStore();
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setFill(Color.gray(0.375));
		view.fillCircleCenteredInverted(Vector.ZERO, rWall);
		view.setFill(Color.gray(0.25));
		view.fillCircleCenteredInverted(Vector.ZERO, rWall + 8);
		
		double d = p.norm();					// udaljenost kursora od koordinatnog pocetka
		double phi = p.angle();					// ugao izmedju kursora i x-ose (u okretima)
		
		Vector c;								// konacna pozicija centra smajlija
		double k;								// faktor kompresije smajlija
		
		if (d <= rWall - r) {
			
			k = 1.0;
			c = p;
		} else {
			
			k = (rWall - r) / d;				// izracunavamo kompresiju nekom opadajucom funkcijom po d koja za d = rWall-r daje 1, a tezi 0 kad d tezi beskonacnosti.
			c = p.normalizedTo(rWall - r * k);	// racunamo centar tako da kompresovan smajli dodiruje zid
		}
		
		Transformation t = new Transformation()
				.rotate(-phi)
				.scale(k, 1/k)
				.rotate(phi)
				.translate(c)
				;
		
		view.setTransformation(t);
		drawFace(view);
		
		view.stateRestore();
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(800, 800);
	}
}
