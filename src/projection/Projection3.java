package projection;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Projection3 implements Drawing {
	
	
	/*
	 * A sandbox mini-project created for figuring out the 3rd project-
	 * ion onto screen.
	 */ 
	
	
	@GadgetAnimation
	double time = 0.0;
	

	static final int IMG_W = 640;
	static final int IMG_H = 480;
	
	
//	@GadgetDouble(min = 0, max = 1.0)
//	double rx = time;
	
//	@GadgetDouble(min = 0, max = 1.0)
//	double ry = time;
	
//	@GadgetDouble(min = 0, max = 1.0)
//	double rz = time;
	
	
	static double[][] mulMat(double[][] a, double[][] b) {
		
		double[][] res = new double[a.length][b[0].length];
		
		for (int j = 0; j < res.length; j++) {
			
			res[j][0] = 0.0;
			for (int k = 0; k < res[0].length; k++) {
				
				double sum = 0.0;
				for (int i = 0; i < b.length; i++)
					sum += b[i][k] * a[j][i];
				
				res[j][k] = sum;
			}
		}
		
		return res;
	}
	
	
	@Override
	public void draw(View view) {
		

		double ratio = 150.0;
		
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		// initial translation
		int gx = 0;
		int gy = 0;
		int gz = 0;
		
		// rotation angles
		double rx = time;
		double ry = time * 0.25;
		double rz = time * 0.125;
		
		// post-rotation translation
		int cx = 0;
		int cy = 0;
		int cz = 5;
		
		double f = 0.1;					// focal point
		double sx = 10000.0 / IMG_W * ratio;	// img resolution
		double sy = 10000.0 / IMG_H * ratio;
		double offsetX = IMG_W / 2 * ratio;		// !!! probably not needed
		double offsetY = IMG_H / 2 * ratio;
		double skew = 0.0 * ratio;
		
		double pix = 12.0;				// ???
		
		Matrix3 GG = new Matrix3(); 	GG.translation(-gx, -gy, -gz);
		
//		double[][] G =  {{1, 0, 0, -gx},
//						 {0, 1, 0, -gy},
//						 {0, 0, 1, -gz},
//						 {0, 0, 0,  1 }};
		
		Matrix3 RX = new Matrix3();		RX.rotationX(rx);
		Matrix3 RY = new Matrix3();		RY.rotationY(ry);
		Matrix3 RZ = new Matrix3();		RZ.rotationZ(rz);
		
		Matrix3 CC = new Matrix3();		CC.translation(-cx, -cy, -cz);
		
		double[][] Rx = {{            1,             0,             0, 0},
						 {            0,  Math.cos(rx), -Math.sin(rx), 0},
						 {            0,  Math.sin(rx),  Math.cos(rx), 0},
						 {            0,             0,             0, 1}};
		
		double[][] Ry = {{ Math.cos(ry),             0,  Math.sin(ry), 0},
						 {            0,             1,             0, 0},
						 {-Math.sin(ry),             0,  Math.cos(ry), 0},
						 {            0,             0,             0, 1}};
		
		double[][] Rz = {{ Math.cos(rz), -Math.sin(rz), 0, 0},
				 		 { Math.sin(rz),  Math.cos(rz), 0, 0},
				 		 {            0,             0, 1, 0},
				 		 {            0,             0, 0, 1}};
		
		double[][] C  = {{1, 0, 0, -cx},
				 		 {0, 1, 0, -cy},
				 		 {0, 0, 1, -cz},
				 		 {0, 0, 0,  1 }};
		
		double[][] P  = {{(f * IMG_W) / (2 * sx),                   skew, 0, 0},
						 {                     0, (f * IMG_H) / (2 * sy), 0, 0},
						 {					   0, 					   0, 1, 0},
						 {					   0, 					   0, 0, 1}};
		
		
		double[][] offset = {{1, 0, 0, offsetX},
							 {0, 1, 0, offsetY},
							 {0, 0, 1,       0},
							 {0, 0, 0,       1}};		// !!! probably not needed
		
//		if (choose == 0) {
			double[][][] verts = {
					{ {-1}, {-1}, {-1}, { 1} },
					{ { 1}, {-1}, {-1}, { 1} },
					{ {-1}, { 1}, {-1}, { 1} },
					{ { 1}, { 1}, {-1}, { 1} },
					{ {-1}, {-1}, { 1}, { 1} },
					{ { 1}, {-1}, { 1}, { 1} },
					{ {-1}, { 1}, { 1}, { 1} },
					{ { 1}, { 1}, { 1}, { 1} }
			};
//		} else {
//			double[][][] verts = {
//					{ {-1}, { 1}, {-1}, { 1} },
//					{ { 1}, { 1}, {-1}, { 1} },
//					{ {-1}, { 1}, { 1}, { 1} },
//					{ { 1}, { 1}, { 1}, { 1} },
//					{ { 0}, {-1}, { 0}, { 1} }};
//					
//			}
//		}
		
		
		for (int i = 0; i < verts.length; i++) {
			verts[i][0][0] *= ratio;
			verts[i][1][0] *= ratio;
			verts[i][2][0] *= ratio;
			verts[i][3][0] *= ratio;
		}
		
		double[][][] result = new double[verts.length][verts[0].length][verts[0][0].length];
		
		for (int i = 0; i < result.length; i++) {
			
			result[i] = mulMat(GG.m, verts[i]);
//			result[i] = mulMat(Rz, result[i]);
//			result[i] = mulMat(Ry, result[i]);
//			result[i] = mulMat(Rx, result[i]);
			
			result[i] = mulMat(RZ.m, result[i]);
			result[i] = mulMat(RY.m, result[i]);
			result[i] = mulMat(RX.m, result[i]);
			
			result[i] = mulMat(CC.m, result[i]);
//			result[i] = mulMat(P, result[i]);
			
			double[][] N = {{1 / result[i][2][0],           0, 0, 0},
					{                  0, 1 / result[i][2][0], 0, 0},
					{                  0,                   0, 1, 0},
					{                  0,                   0, 0, 1}};
			
//			result[i] = mulMat(N, result[i]);
//			result[i] = mulMat(offset, result[i]);
		}
		
		view.setStroke(Color.WHITE);
		
		Vector[] projection = new Vector[verts.length];
		
		for (int i = 0; i < result.length; i++) {
			projection[i] = new Vector(
					result[i][0][0] / result[i][2][0] * 4 * ratio,
					result[i][1][0] / result[i][2][0] * 4 * ratio
					);
			
//			view.strokeCircleCentered(new Vector(x, y), 2.5);
		}
		
		int[][] edges = {
				{ 0, 1 }, { 0, 2 }, { 2, 3 }, { 1, 3 },
				{ 0, 4 }, { 1, 5 }, { 2, 6 }, { 3, 7 },
				{ 4, 5 }, { 4, 6 }, { 6, 7 }, { 5, 7 }
		};
		
//		int[][] edges = {
//				{ 0, 1}, { 0, 2}, { 2, 3}, { 1, 3},
//				{ 0, 4}, { 1, 4}, { 2, 4}, { 3, 4}
//		};
		
		for (int i = 0; i < projection.length; i++)
			view.strokeCircleCentered(projection[i], 2.5);
		
		for (int[] edge : edges) {
			view.strokeLine(projection[edge[0]], projection[edge[1]]);
		}
		
		
	}
	
	public static void main(String[] args) {
		DrawingApplication.launch(IMG_W, IMG_H);
	}
}
