package sandbox;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetColorPicker;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class RotatingSquare implements Drawing {
	
	@GadgetColorPicker
	Color squareFrame = Color.ANTIQUEWHITE;
	
	@GadgetColorPicker
	Color squareFill = Color.LIGHTSKYBLUE;
	
	@GadgetColorPicker
	Color circleFill = Color.BLACK;
	
//	@GadgetDouble(min = 0.0, max = 1.0)
	double rotationCenterX = 1.0;
	
//	@GadgetDouble(min = 0.0, max = 1.0)
	double rotationCenterY = 1.0;

	@GadgetDouble(min = 0.0, max = 180.0)
	double angleDeg;
	
	@GadgetBoolean
	boolean drawFrame = true;
	
	@GadgetBoolean
	boolean drawCircle = true;
	
	public Image image() {
		
		int w = 400;
		int h = 400;
		
		WritableImage image = new WritableImage(w, h);
		PixelWriter pw = image.getPixelWriter();
		
		double angleRad = Math.toRadians(angleDeg);		// pretvaranje ugla iz Gadgeta u radijane
		double sinAngle = Math.sin(angleRad);
		double cosAngle = Math.cos(angleRad);
		double x0 = 0.5 * (h - 1.0);					// centriranje iscrtavanja kvadrata na (0, 0), tj. centar
		double y0 = 0.5 * (w - 1.0);					// centriranje iscrtavanja kvadrata na (0, 0), tj. centar
		
		for (int y = 10; y < h - 10; y++) {
			for (int x = 10; x < w - 10; x++) {
				// a  = a' * cos(A) + a' * cos(90° - A)
				// a' = a / (cos(A) + cos(90° - A)
				// a  -> 	stranica kvadrata u osnovnom polozaju
				//			posto se trazi faktor skaliranja uzima se da je a = 1.0
				// a' -> 	stranica kvadrata rotirana za ugao A
				// skaliranje obezbedjuje da kvadrat bez obzira na rotaciju bude u okviru
				double scale = 1.0 / (Math.abs(Math.cos(Math.toRadians(90 - angleDeg)) + Math.abs(Math.cos(angleRad))));
				
				// ovde dolazi do skaliranja i eventualnog pomeranja centra rotacije
				double a = scale * (x - x0 * rotationCenterX);
				double b = scale * (y - y0 * rotationCenterY);
				
				// koordinate rotiranih tacaka, tj. piksela
				int xx = (int) (+a * cosAngle - b * sinAngle + x0);
				int yy = (int) (+a * sinAngle + b * cosAngle + y0);
				
				// provera uslova zasnovanog na osnovnim koordinatama,
				// iscrtavanje slike koristeci rotirane i skalirane tacke
				// okvira i unutrasnjosti kvadrata koji se rotira
				if ((10 <= x && x < 20) || (380 <= x && x < w - 10) ||
					(10 <= y && y < 20) || (380 <= y && y < h - 10)) {
					pw.setColor(xx, yy, squareFrame);
				} else {
					pw.setColor(xx, yy, squareFill);
				}
			}
		}
		
		return image;
	}
	
	public Image frame() {
		
		int w = 420;
		int h = 420;
		
		WritableImage frame = new WritableImage(w, h);
		PixelWriter pw = frame.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if ((0 <= x && x < 20) || (400 <= x && x < w) ||
					(0 <= y && y < 20) || (400 <= y && y < h)) 
				{
					Color color = drawFrame ? Color.DARKSLATEBLUE : Color.TRANSPARENT;
					pw.setColor(x, y, color);
				} else {
					Color color = drawFrame ? Color.CADETBLUE : Color.TRANSPARENT;
					pw.setColor(x, y, color);
				}
			}
		}
		
		return frame;
	}
	
	public Image circle() {
		
		int w = 400;
		int h = 400;
		
		WritableImage circle = new WritableImage(w, h);
		PixelWriter pw = circle.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				double dx = (2.0 * x / w) - 1.0;
				double dy = (2.0 * y / h) - 1.0;
				double dd = Math.sqrt(dx * dx + dy * dy);
				
				double scale = (Math.abs(Math.cos(Math.toRadians(90 - angleDeg)) + Math.abs(Math.cos(Math.toRadians(angleDeg)))));
				
				if (dd <= 1.0 / scale - 0.1) {
					Color color = drawCircle ? circleFill : Color.TRANSPARENT;
					pw.setColor(x, y, color);
				}
			}
		} 
		
		return circle;
	}
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		view.drawImageCentered(Vector.ZERO, frame());
		view.drawImageCentered(Vector.ZERO, image());
		view.drawImageCentered(Vector.ZERO, circle());
	}

	public static void main(String[] args) {
		DrawingApplication.launch(500, 500);
	}
}
