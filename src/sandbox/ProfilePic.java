package sandbox;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import mars.random.RNG;

public class ProfilePic implements Drawing {

	public static final int w = 800;
	public static final int h = 800;
	
	public static final int hue_max = 20;
	

	@GadgetInteger(min = 10, max = 30)
	int n = 14;
	
	
	public void drawLines(View view) {
		
		int count_y = 0, count_p = 0;
		
		RNG rng = new RNG(6581972124973596354l);

		for (int i = -n/2; i < n/2; i++) {
			
//			double x = (rng.nextDouble() * 0.75 + 0.25) * h;
			double x = rng.nextDouble() * h;
			
			view.setStroke(Color.BLACK);
			
			Color c;
			
			double d = rng.nextDouble();
			if (d <= 0.25 && count_y < hue_max) {
				
				c = Color.hsb(50, rng.nextDouble() * 0.25 + 0.55, rng.nextDouble() * 0.45 + 0.05);
				count_y++;
			} else if (0.25 < d && d <= 0.55 && count_p < hue_max) {
				
				c = Color.hsb(260, rng.nextDouble() * 0.75 + 0.25, rng.nextDouble() * 0.75 + 0.25);
				count_p++;
			} else {
				
				c = Color.hsb(0, 0.0, rng.nextDouble());
			}
			
			view.setFill(c);
			view.fillRect(new Vector(i * w / n, -(h * 0.5)), new Vector(w / n, x));
			view.strokeRect(new Vector(i * w / n, -(h * 0.5)), new Vector(w / n, x));
			
			d = rng.nextDouble();
			if (d <= 0.55 && count_y < hue_max) {
				
				c = Color.hsb(50, rng.nextDouble() * 0.75 + 0.25, rng.nextDouble() * 0.95 + 0.05);
				count_y++;
			} else if (0.55 < d && d <= 0.75 && count_p < hue_max) {
				
				c = Color.hsb(260, rng.nextDouble() * 0.25 + 0.55, rng.nextDouble() * 0.45 + 0.05);
				count_p++;
			} else {
				
				c = Color.hsb(0, 0.0, rng.nextDouble());
			}
			
			view.setFill(c);
			view.fillRect(new Vector(i * w / n, x - h/2), new Vector(w / n, (h - x)));
			view.strokeRect(new Vector(i * w / n, x - h/2), new Vector(w / n, (h - x)));
		}
	}
	
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		drawLines(view);
	}
	

	public static void main(String[] args) {
		
		DrawingApplication.launch(w, h);
	}
}
