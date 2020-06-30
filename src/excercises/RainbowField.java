package excercises;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.random.RNG;

public class RainbowField implements Drawing {
	
	static final int w = 800;
	static final int h = 800;
	
	static final int tile = 100;
	
	
	@GadgetDouble(min = -400.0, max = 200.0)
	double floorY = -150;
	
	@GadgetInteger(min = 1, max = 12)
	int n = 7;
	
	@GadgetDouble(min = -350.0, max = 350.0)
	double r = 325;
	
	
	void drawSky(View view) {
		
		view.setFill(Color.hsb(210, 0.6, 1.0));
		view.fillRectCentered(Vector.ZERO, new Vector(w / 2, h / 2));
	}
	
	
	void drawRainbow(View view) {
		
		view.setLineWidth(20);
		
		for (int i = 0; i < n; i++) {
			
			view.setStroke(Color.hsb(360 * (i + 0.0) / n, 0.75, 0.95));
			view.strokeCircle(Vector.ZERO.add(new Vector(0, floorY + 100)), r - (i * 20.0));
		}
	}
	
	
	void drawField(View view) {
		
		RNG rng = new RNG(6238957209870546723l);
		
		for (int tileY = (int) -floorY; tileY < h + tile; tileY += tile) {
			for (int tileX = -w / 2; tileX < w + tile; tileX += tile) {
				
				view.setFill(Color.hsb(120, 0.50, rng.nextDouble() * 0.50 + 0.25));
				view.fillRect(new Vector(-tileX, -tileY), new Vector(tile));
			}
		}
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		drawSky(view);
		drawRainbow(view);
		drawField(view);
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(w, h);
	}
}
