package topic5_isometry;

import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.drawingx.utils.camera.CameraSimple;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;
import mars.random.RNG;


public class IsometricTerrain implements Drawing {
	Map map;
	CameraSimple camera;
	
	RNG rng = new RNG(1098523056743578881l);
	
	Vector vx = new Vector( 1, 0.5);
	Vector vy = new Vector(-1, 0.5);
	Vector vz = new Vector( 0, 1  );

	Vector o = new Vector(-44, -10);
	double d = 12.0;
	
	
	@GadgetVector
	Vector px = o.add(vx.mul(d));
	
	@GadgetVector
	Vector py = o.add(vy.mul(d));
	
	@GadgetVector
	Vector pz = o.add(vz.mul(d));
			
	
	Font font = new Font("Consolas", 3.0);
	
	
	void drawAxes(View view) {
		view.setLineCap(StrokeLineCap.ROUND);
		view.setLineJoin(StrokeLineJoin.MITER);
		view.setLineWidth(0.25);
		
		view.setFont(font);
		view.setTextAlign(TextAlignment.CENTER);
		view.setTextBaseline(VPos.CENTER);
		view.setFill(Color.WHITE);
		
		Vector[] ps     = {  px,  py,  pz };
		double[] hues   = {   0, 120, 240 };
		String[] labels = { "X", "Y", "Z" };
		
		for (int i = 0; i < ps.length; i++) {
			Vector e = ps[i].sub(o);
			
			view.setStroke(Color.hsb(hues[i], 0.7, 1.0));
			DrawingUtils.drawArrow(view, o, e, 1);
			view.fillText(labels[i], o.add(e.mul(1 + 3 / e.norm())));
		}
	}
	
	
	void setup() {
		MapGenerator mapGenerator = new MapGenerator(new Vector(64, 64), rng.nextLong());
		map = mapGenerator.generate();
	}

	
	{
		setup();
		camera = new CameraSimple(0.125, vx.mul(map.sizeX()).add(vy.mul(map.sizeY())).div(2));
	}
	
	
	@Override
	public void draw(View view) {
		view.setTransformation(camera.getTransformation());
		DrawingUtils.clear(view, Color.gray(0.125));
		
		MapDrawing mapDrawing = new MapDrawing(
				px.sub(o).div(d),
				py.sub(o).div(d),
				pz.sub(o).div(d)
		);		
		mapDrawing.drawMap(view, map);
		
		drawAxes(view);
	}

	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		camera.receiveEvent(view, event, state, pointerWorld, pointerViewBase);
		
		if (event.isKeyPress(KeyCode.ENTER)) {
			setup();
		}
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(1100, 800);
	}
}