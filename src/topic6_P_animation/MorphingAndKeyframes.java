package topic6_P_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class MorphingAndKeyframes implements Drawing {
	
	final Vector fieldP = new Vector(-300, -200);
	final Vector fieldD = new Vector( 600,  400);
	
	final int maxNVertices = 100;
	final int maxNKeyframes = 100;
	
	final double stateDuration = 1.0;
	
	
	Vector[][] polygons;
	double[] hues;
	
	
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetBoolean
	boolean showKeyFrames = true;
	
	@GadgetInteger(min = 3, max = maxNVertices)
	int nVertices = 3;
	
	@GadgetInteger(min = 2, max = maxNKeyframes)
	int nStates = 8;
	
	
	double smootherstep(double x) {
		// 6x^5 - 15x^4 + 10x^3
		return x * x * x * (x * (x * 6 - 15) + 10);
	}
	
	
	@Override
	public void init(View view) {
		
		polygons = new Vector[maxNKeyframes][maxNVertices];
		hues = new double[maxNKeyframes];
		
		for (int i = 0; i < maxNKeyframes; i++) {
			for (int j = 0; j < maxNVertices; j++) {
			
				polygons[i][j] = Vector.randomInBox(fieldP, fieldD);
			}
			
			hues[i] = 360 * Math.random();
		}
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setLineDashes(null);
		
		if (showKeyFrames) {
			
			view.setLineDashes(4);
			view.setLineCap(StrokeLineCap.BUTT);
			view.setLineJoin(StrokeLineJoin.ROUND);
			view.setLineWidth(1);
			view.setStroke(Color.hsb(0, 0, 1, 0.2));
			
			for (int i = 0; i < nStates; i++)
				view.strokePolygon(nVertices, polygons[i]);
		}
		
		int k0 = (int) (time / stateDuration) % nStates;
		int k1 = (k0 + 1) % nStates;
		
		double t = smootherstep((time % stateDuration) / stateDuration);
		
		Vector[] polygon = new Vector[nVertices];
		for (int i = 0; i < nVertices; i++)
			polygon[i] = Vector.lerp(polygons[k0][i], polygons[k1][i], t);
		
		Color c0 = Color.hsb(hues[k0], 0.7, 0.7, 0.7);
		Color c1 = Color.hsb(hues[k1], 0.7, 0.7, 0.7);
		view.setFill(c0.interpolate(c1, t));
		view.fillPolygon(polygon);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(400, 400);
	}
}
