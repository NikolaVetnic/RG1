package sandbox;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class FrowneyFace implements Drawing {

	@GadgetDouble(min = 0, max = 200)
	double r = 200;
	
	@GadgetDouble(min = 0, max = 200)
	double xEye = 70.0;
	
	@GadgetDouble(min = 0, max = 300)
	double yEye = 70.0;
	
	@GadgetDouble(min = 0, max = 42.5)
	double rEye = 30.0;
	
	@GadgetDouble(min = 0, max = 100)
	double rMouth = 130.0;
	
	@GadgetDouble(min = 0, max = 0.5)
	double phiMouth = 1.0 / 8;
	
	@GadgetDouble(min = 0.0, max = 220.0)
	double vM = 32.5;
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.125));
		
		// Glava
		view.setFill(Color.hsb(60, 0.9, 0.9));
		view.fillCircleCentered(new Vector(0, 0), r);
		
		// Oci
		view.setFill(Color.hsb(0, 0, 0));
		view.fillCircleCentered(new Vector(-xEye, yEye), rEye);		
		view.fillCircleCentered(new Vector(xEye, yEye), rEye);
		
		// Obrve
		view.strokeLine(new Vector(xEye - 50, yEye + 15), new Vector(xEye + 50, yEye + 65));
		view.strokeLine(new Vector(-xEye + 50, yEye + 15), new Vector(-xEye - 50, yEye + 65));
		
		// Brk
		view.setFill(Color.hsb(0, 0, 0));
		view.fillRectCentered(new Vector(0, -25), new Vector(vM, vM));
		
		// Usta
		view.setLineWidth(15);
		view.setStroke(Color.hsb(0, 0, 0));
		view.strokeArcCentered(new Vector(0, -r), new Vector(rMouth), phiMouth, 0.5 - 2 * phiMouth);
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
