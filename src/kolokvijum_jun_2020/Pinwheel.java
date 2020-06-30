package kolokvijum_jun_2020;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class Pinwheel implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetDouble(min = -1.0, max = 1.0)
	double rSpeed = 0.25;
	
	
	double a = 100;
	double d = a * Math.sqrt(2);
	
	
	void drawBlade(View view, double phi, double hue) {
		
		view.setFill(Color.hsb(hue, 0.75, 0.65));
		view.fillPolygon(
				Vector.ZERO, 
				Vector.polar(a, 0.0 + phi + time * rSpeed), 
				Vector.polar(d, 0.125 + phi + time * rSpeed));
		
		view.setFill(Color.hsb(hue, 0.45, 0.65));
		view.fillPolygon(
				Vector.ZERO, 
				Vector.polar(d, 0.125 + phi + time * rSpeed), 
				Vector.polar(2 * a, 0.25 + phi + time * rSpeed));
	}
	
	
	void drawPinwheel(View view) {
		
		view.setStroke(Color.gray(0.875));
		view.setLineWidth(10);
		view.strokeLine(Vector.ZERO, new Vector(0, -300));
		
		for (int i = 0; i < 4; i++)
			drawBlade(view, i * 0.25, i * 90);
		
		view.setFill(Color.gray(0.875));
		view.fillCircleCentered(Vector.ZERO, 10);
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		drawPinwheel(view);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(400, 700);
	}
}
