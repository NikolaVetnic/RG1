package sandbox;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
//import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Transformation;
import mars.geometry.Vector;

public class Box implements Drawing {
	
	public static final double SQRT2 = 1.41;
	public static final double SQRT3 = 1.73;
	public static final double PI = 3.14159;
	
	
	@GadgetAnimation(speed = 1.25)
	double z = 1.0;
	
	@GadgetDouble(min = 1.0, max = 200.0)
	double TILE_D = 150.0;
	
	@GadgetDouble(min = 1.0, max = 5.0)
	double TILE_S = 2.75;
	
	@GadgetDouble(min = 0.0001, max = 1.00)
	double boxPhi = 1.0;
	
//	@GadgetDouble(min = 0.0001, max = PI)
//	double lidPhi = 1.0;
	double lidPhi;
	
//	@GadgetDouble(min = 0.0001, max = 200.0)
//	double bHeight = 0.0001;
	double bHeight;
	
	@GadgetVector
	Vector offset = Vector.ZERO;
	
//	@GadgetBoolean
	boolean stroke = true;
	
	
	void fillBoxSide(View view, Vector[] vertices, int[] a, Color c, Boolean stroke) {
		view.setFill(c);
		view.fillPolygon(
				vertices[a[0]], 
				vertices[a[1]], 
				vertices[a[2]], 
				vertices[a[3]]);
		
		if (stroke) {
			view.strokePolygon(
				vertices[a[0]], 
				vertices[a[1]], 
				vertices[a[2]], 
				vertices[a[3]]);
		}
	}

	
	void drawBox(View view, Color c0, Vector offset) {
		
		bHeight = Math.abs(Math.sin(z)) * 200.00;
		
		if (bHeight >= 75.0) {
			lidPhi = PI * 0.5 + ((PI * 0.5) / 125.0) * (bHeight - 75.0);
		} else {
			lidPhi = ((PI * 0.5) / 75.0) * bHeight;
		}

		Color[] palette = {
				Color.hsb(c0.getHue(), 0.7, 0.8),
				Color.hsb(c0.getHue(), 0.6, 0.7),
				Color.hsb(c0.getHue(), 0.6, 0.6),
				Color.hsb(c0.getHue(), 0.6, 0.5),
				Color.hsb(c0.getHue(), 0.5, 0.4),
				Color.hsb(c0.getHue(), 0.5, 0.3),
				Color.hsb(c0.getHue(), 0.5, 0.1)
			};
		
		// bottom
		Transformation t1 = new Transformation()
				.rotate(boxPhi)
				.scale(1.0, 1 / TILE_S)
				;
		
		// top
		Transformation t2 = new Transformation()
				.rotate(boxPhi)
				.scale(1.0, 1 / TILE_S)
				.translate(new Vector(0, -SQRT3 * (TILE_D / 2.0)))
				;
		
		double lx = TILE_D * Math.sqrt(1 - Math.sin(lidPhi) * Math.sin(lidPhi));
			   lx = lidPhi <= PI / 2 ? lx : -lx;
		double ly = TILE_D * Math.sin(lidPhi);
		
		// lid
		Transformation t3 = new Transformation()
				.translate(new Vector(lx, 0))
				.rotate(boxPhi)
				.scale(1.0, 1 / TILE_S)
				.translate(new Vector(0, ly))
				;
		
		Vector[] vertices = {
				new Vector(-TILE_D / 2 + offset.x, -TILE_D / 2 + offset.y).transform(t1),
				new Vector(+TILE_D / 2 + offset.x, -TILE_D / 2 + offset.y).transform(t1),
				new Vector(+TILE_D / 2 + offset.x, +TILE_D / 2 + offset.y).transform(t1),
				new Vector(-TILE_D / 2 + offset.x, +TILE_D / 2 + offset.y).transform(t1),
				
				new Vector(-TILE_D / 2 + offset.x, -TILE_D / 2 + offset.y).transform(t2),
				new Vector(+TILE_D / 2 + offset.x, -TILE_D / 2 + offset.y).transform(t2),
				new Vector(+TILE_D / 2 + offset.x, +TILE_D / 2 + offset.y).transform(t2),
				new Vector(-TILE_D / 2 + offset.x, +TILE_D / 2 + offset.y).transform(t2),
				
				new Vector(-TILE_D / 2 + offset.x, -TILE_D / 2 + offset.y).transform(t3),
				new Vector(-TILE_D / 2 + offset.x, +TILE_D / 2 + offset.y).transform(t3),
		};
		
		view.setLineWidth(1);
		view.setLineCap(StrokeLineCap.ROUND);
		view.setStroke(palette[6]);
																		// bottom
			fillBoxSide(view, vertices, new int[] {4, 5, 6, 7}, palette[5], stroke);
		
		if (vertices[3].x <= vertices[0].x)								// side behind
			fillBoxSide(view, vertices, new int[] {1, 2, 6, 5}, palette[3], stroke);
		else
			fillBoxSide(view, vertices, new int[] {3, 0, 4, 7}, palette[4], stroke);
		
		if (vertices[0].x <= vertices[1].x)								// side behind
			fillBoxSide(view, vertices, new int[] {2, 3, 7, 6}, palette[2], stroke);
		else
			fillBoxSide(view, vertices, new int[] {0, 1, 5, 4}, palette[2], stroke);

		if (0.5375 <= boxPhi && boxPhi <= 0.9625 && lidPhi >= 0.25)		// is lid behind?
			fillBoxSide(view, vertices, new int[] {0, 8, 9, 3}, palette[0], stroke);
		
		drawBall(view, Color.ORANGERED, offset);
		
		if (vertices[3].x <= vertices[0].x)								// side in front 
			fillBoxSide(view, vertices, new int[] {3, 0, 4, 7}, palette[2], stroke);
		else 
			fillBoxSide(view, vertices, new int[] {1, 2, 6, 5}, palette[3], stroke);
		
		if (vertices[0].x <= vertices[1].x)								// side in front
			fillBoxSide(view, vertices, new int[] {0, 1, 5, 4}, palette[1], stroke);
		else
			fillBoxSide(view, vertices, new int[] {2, 3, 7, 6}, palette[1], stroke);
		
		if (boxPhi < 0.5375 || 0.9625 < boxPhi || lidPhi < 0.25)		// is lid in front?
			fillBoxSide(view, vertices, new int[] {0, 8, 9, 3}, palette[0], stroke);
	}
	
	
	void drawBall(View view, Color c0, Vector offset) {
		
		Color[] palette = {
				Color.hsb(c0.getHue(), 0.7, 0.8),
				Color.hsb(c0.getHue(), 0.6, 0.7),
				Color.hsb(c0.getHue(), 0.6, 0.6),
				Color.hsb(c0.getHue(), 0.6, 0.5),
				Color.hsb(c0.getHue(), 0.5, 0.4),
				Color.hsb(c0.getHue(), 0.5, 0.3),
				Color.hsb(c0.getHue(), 0.5, 0.1)
			};
		
		view.setLineWidth(1);
		view.setLineCap(StrokeLineCap.ROUND);
		view.setStroke(palette[6]);
		
		view.setFill(palette[0]);
		view.fillCircleCentered(new Vector(0 + offset.x, -SQRT3 * (TILE_D / 4.0) + bHeight), TILE_D / 2.35);
		
		if (stroke)
			view.strokeCircleCentered(new Vector (0 + offset.x, -SQRT3 * (TILE_D / 4.0) + bHeight), TILE_D / 2.35);
			
	}
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		drawBox(view, Color.GREENYELLOW, offset);
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 500);
	}
}
