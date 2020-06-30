package excercises;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.random.RNG;

public class ComicBam implements Drawing {
	
	@GadgetAnimation(min = 0.0, max = 1.0)
	double time = 1.0;
	

	Font font = Font.font("Comic Sans MS", FontWeight.BLACK, 144.0);
	
	int n = 11;
	
	RNG rng = new RNG();
	int[] r = new int[n];
	
	
	@Override
	public void init(View view) {
		for (int i = 0; i < n - 1; i++) r[i] = rng.nextInt(100) + 200;
		
		r[n - 1] = r[0];
	}
	
	
	double f(double x) {
		
		if        (x < 1.00 / 2.75) {
			return 7.5625 * x * x;
		} else if (x < 2.00 / 2.75) {
			return 7.5625 * (x -= (1.5   / 2.75)) * x + 0.75;
		} else if (x < 2.50 / 2.75) {
			return 7.5625 * (x -= (2.25  / 2.75)) * x + 0.9375;
		} else {
			return 7.5625 * (x -= (2.625 / 2.75)) * x + 0.984375;
		}
	}
	
	
	void drawBase(View view) {
		
		view.beginPath();
		
			Vector p0 = Vector.polar(r[0]	 	    , 0.0			   );
			
		view.moveTo(p0);
		
		for (int i = 0; i < n; i++) {
			
			Vector p1 = Vector.polar(r[i % n] * 0.45, (i % n + 0.0) / n);
			Vector p2 = Vector.polar(r[i % n] * 0.45, (i % n + 1.0) / n);
			Vector p3 = Vector.polar(r[i % n]	    , (i % n + 1.0) / n);
			
			view.bezierCurveTo(p1, p2, p3);
		}
		
		view.setFill(Color.hsb(0, 0.85, 0.75));
		view.fill();
		
		view.setStroke(Color.hsb(0, 0.75, 0.35));
		view.setLineWidth(10);
		view.stroke();
	}
	
	
	void drawText(View view) {
		
		view.setTextAlign(TextAlignment.CENTER);
		view.setTextBaseline(VPos.CENTER);
		view.setFont(font);

		view.setFill(Color.YELLOW);
		view.fillText("BAM!", Vector.ZERO);
		view.setStroke(Color.BLACK);
		view.setLineWidth(7.5);
		view.strokeText("BAM!", Vector.ZERO);
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.875));
		
		view.stateStore();
		
		Transformation t = new Transformation().scale(f(time));
		
		view.addTransformation(t);
		
		drawBase(view);
		drawText(view);
		
		view.stateRestore();
	}

	
	public static void main(String[] args) {
		
		DrawingApplication.launch(600, 600);
	}
}
