package min_dist_2d;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;
import mars.random.fixed.continuous.PerlinNoise;

public class Main implements Drawing {
	
	
	static final int DIM = 640;
	
	
	@GadgetAnimation(speed = 0.05)
	double t = 0.0;
	
	@GadgetVector
	Vector p = Vector.ZERO;
	
	@GadgetInteger(min = 2, max = 100)
	int numPoints = 4;
	
	
	private Vector[] v;
	
	
	public Vector[] generatePoints(int numPoints, double time) {
		
		// @author MarS
		
        Vector[] out = new Vector[numPoints];
        
        PerlinNoise pnX = new PerlinNoise(4384975239875392342l);
        PerlinNoise pnY = new PerlinNoise(2759237507045784589l);
        
        double t = time * 3;
        
        for (int i = 0; i < numPoints; i++)
            out[i] = new Vector(
                    pnX.getValue(0.5 + i * 237835397, 0.5 + i * 86192364, t),
                    pnY.getValue(0.5 + i * 426865863, 0.5 + i * 16498346, t + 0.5)
            ).mul(500);
        
        return out;
    }
	
	
	public void fillPoints(View view, Color c) {
		
		view.setFill(c);
		for (Vector curr : v) view.fillCircleCentered(curr, 5.);
	}
	
	
	public void strokeShortestDistance(View view, int[] pq, Color c) {

		view.setStroke(c);
		view.setLineWidth(2.5);
		
		view.strokeCircleCentered(v[pq[0]], 7.5);
		view.strokeCircleCentered(v[pq[1]], 7.5);
		
		view.strokeCircleCentered(v[pq[0]], 2.5);
		view.strokeCircleCentered(v[pq[1]], 2.5);
		
		view.strokeLine(v[pq[0]], v[pq[1]]);
	}
	

	@Override
	public void draw(View view) {

		DrawingUtils.clear(view, Color.gray(0.125));
		
		v = generatePoints(numPoints, t);
		
		MinDist2D md = new MinDist2D(v);
		int[] pq = md.closestPair();
		
		fillPoints(view, Color.PURPLE);
		
		strokeShortestDistance(view, pq, Color.GOLD);
		
		String info = String.format("Shortest distance : %6.2f (points [%d] & [%d])", md.cpDist(), pq[0], pq[1]);
		DrawingUtils.drawInfoText(view, info);
	}
	

	public static void main(String[] args) {
		DrawingApplication.launch(DIM, DIM);
	}
}
