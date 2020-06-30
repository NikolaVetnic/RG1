package excercises;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Transformation;
import mars.geometry.Vector;

public class IFSSmiley implements Drawing {
	
	double r = 100.0;
	double xEye = 35;
	double yEye = 35;
	double rEye = 15;
	double rMouth = 65;
	double phiMouth = 1.0/3; 
	
	
	@GadgetInteger(min = 0)
	int nLevels = 5;
	
	
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
	
	
	private void drawIFS(View view, int level) {
		
		if (level == nLevels)
			return;
		
		for (int i = 0; i < 4; i++) {
			
			view.stateStore();
			
			Vector s = Vector.polar(285, 0.125 + 1.0 * i / 4);
			view.addTransformation(Transformation.scaling(0.5).translate(s));
			
			drawIFS(view, level + 1);
			
			view.stateRestore();
		}
		
		drawFace(view);
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		drawIFS(view, 0);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(800, 800);
	}
}
