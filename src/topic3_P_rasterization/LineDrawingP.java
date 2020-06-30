package topic3_P_rasterization;

//import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.drawingx.utils.camera.CameraSimple;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;

public class LineDrawingP implements Drawing {
	
	CameraSimple camera = new CameraSimple();
	
	@GadgetVector
	Vector p0 = new Vector(10, 29);
	
	@GadgetVector
	Vector p1 = new Vector(120, 76);
	
	@GadgetBoolean
	boolean showLine = false;

	
	@Override
	public void init(View view) {
		view.setImageSmoothing(false);
	}
	
	
	Vector offset = new Vector(0.5, 0.5);	
	
	@Override
	public void draw(View view) {
		view.setTransformation(camera.getTransformation());
		DrawingUtils.clear(view, Color.gray(0.125));
		DrawingUtils.drawGrid(view, Color.gray(0.25));
		
		int size = 800;
		WritableImage image = new WritableImage(size, size);
//		PixelWriter pw = image.getPixelWriter();
		
		p0 = p0.floor();
		p1 = p1.floor();
		
//		int x0 = (int) (p0.x + size/2);
//		int y0 = (int) (size/2 - 1 - p0.y);
//		int x1 = (int) (p1.x + size/2);
//		int y1 = (int) (size/2 - 1 - p1.y);
		
//		drawLineBresenham(pw, x0, y0, x1, y1);
		view.drawImageCentered(Vector.ZERO, image);
		
		if (showLine) {
			view.setStroke(Color.RED);
			view.setLineWidth(1.0 / view.getTransformationWorldToNative().getScale());
			view.strokeLine(p0.add(offset), p1.add(offset));
		}
	}
	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		camera.receiveEvent(view, event, state, pointerWorld, pointerViewBase);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
}
