package sandbox;

import java.awt.MouseInfo;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.geometry.Vector;
import topic2_P_image_processing.filters.displacement.DisplacementFilter;

class BandsH1 extends DisplacementFilter {

	final int bw;
	
	
	public BandsH1(double bw) {
		this.bw = (int) bw;
	}
	
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		
		if (dst.y % bw != 0)
			return new Vector(dst.x, dst.y % bw);
		else
			return new Vector(dst.x, dst.y);
	}
}

class BandsH2 extends DisplacementFilter {

	final double bw;
	
	
	public BandsH2(double bw) {
		this.bw = bw;
	}
	
	
	@Override
	public Vector source(Vector dst, Vector dim) {
		
		if (dst.y % (int) bw != 0)
			return new Vector(dst.x, (int) (dst.y / (int) bw) * (int) bw);
		else
			return new Vector(dst.x, dst.y);
	}
}

public class InterlaceBandsH implements Drawing {
	
	@GadgetBoolean
	boolean operatorToggle = false;
	
	
	Image image;
	
	
	@Override
	public void init(View view) {
		image = new Image("images/fall.jpg");
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		// calculate mouse y coordinate
		double d = MouseInfo.getPointerInfo().getLocation().y * (1.00 / 900);
		
		// bandwidth dependant on mouse y coordinate
		Image filteredImage1 = new BandsH1(d * 9 + 1).process(image);
		Image filteredImage2 = new BandsH2(d * 9 + 1).process(image);
		
		view.drawImageCentered(Vector.ZERO, operatorToggle ? filteredImage1 : filteredImage2);
	}

	public static void main(String[] args) {
		
		DrawingApplication.launch(800, 600);
	}
}
