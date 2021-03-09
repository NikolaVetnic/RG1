package ascii;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Main implements Drawing {
	

	String[] fileNames = {
			"Mona Lisa.jpg",
			"npv_ed.jpg",
			"npv_pb.jpg",
			"building.jpg",
			"catparty.jpg",
			"christmas.jpg",
			"couple.jpg",
			"dive.jpg",
			"doggo.jpg",
			"fall.jpg",
			"forecast.jpg",
			"kitchen.jpg",
			"meterologist.jpg",
			"office.jpg",
			"skirts.jpg",
			"waiting.jpg",
	};
	
	
	String[] modes = {
			"closest",
			"random",
			"jitter",
	};

	
	List<Image> images;
	String path;

	
	@GadgetInteger(min = 0, max = 14)
	int idx = 0;

	
	@GadgetInteger(min = 0, max = 2)
	int mode = 2;
	
	
	@GadgetDouble(min = 0.01, max = 10.0)
	double scale = 1.0;
	
	
	@Override
	public void init(View view) {
		
		images = new ArrayList<Image>();
	}
	
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		Image img = new Image("images/" + fileNames[idx]);
		
		view.drawImageCentered(Vector.ZERO, new ImageASCII(img, new CharSheet()).getImage(modes[mode]), scale);
	}


	public static void main(String[] args) {
		DrawingApplication.launch(720, 720);
	}
}
