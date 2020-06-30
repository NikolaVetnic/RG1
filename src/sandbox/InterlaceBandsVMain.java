package sandbox;

import java.awt.MouseInfo;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import topic2_P_image_processing.filters.displacement.DisplacementFilter;
import topic2_P_image_processing.filters.color.ColorFilter;

class BandsV1 extends DisplacementFilter {
	
	final int bw;
	
	
	public BandsV1(double bw) {
		this.bw = (int) bw;
	}
	

	@Override
	public Vector source(Vector dst, Vector dim) {
		
		if (dst.y % bw != 0)
			return new Vector(dst.x % bw, dst.y);
		else
			return new Vector(dst.x, dst.y);
	}
}

class BandsV2 extends DisplacementFilter {
	
	final int bw;
	
	
	public BandsV2(double bw) {
		this.bw = (int) bw;
	}
	

	@Override
	public Vector source(Vector dst, Vector dim) {
		
		if (dst.y % bw != 0)
			return new Vector((int) (dst.x / bw) * bw, dst.y);
		else
			return new Vector(dst.x, dst.y);
	}
}

class Colorize extends ColorFilter {

	final double d;
	
	
	public Colorize(double d) {
		this.d = d;
	}
	
	
	@Override
	public Color processColor(Color input) {
		
		return Color.hsb(
						input.getHue() + (360 - input.getHue()) * d, 
						input.getSaturation() + (1.0 - input.getSaturation()) * d / 5.0, 
						input.getBrightness(), 
						input.getOpacity()
						);
	}
}

public class InterlaceBandsVMain implements Drawing {
	
	@GadgetBoolean
	boolean toggleVariation = true;
	
	@GadgetBoolean
	boolean changeColor = false;
	
	@GadgetInteger(min = 1, max = 100)
	int quantization = 10;
	
	
	Image image;
	
	
	@Override
	public void init(View view) {
		image = new Image("images/fall.jpg");
	}

	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		double d = (MouseInfo.getPointerInfo().getLocation().x + 1) * (1.0 / 1440);
		
		Image filteredImage1Displ = new BandsV1(d * quantization).process(image);
		Image filteredImage1Color = new Colorize(changeColor ? d : 0.0).process(filteredImage1Displ);
		
		Image filteredImage2Displ = new BandsV2(d * quantization).process(image);
		Image filteredImage2Color = new Colorize(changeColor ? d : 0.0).process(filteredImage2Displ);
		
		view.drawImageCentered(Vector.ZERO, toggleVariation ? filteredImage2Color : filteredImage1Color);
		
		DrawingUtils.drawInfoText(view, "mouse.x: " + (int) (d * quantization)); 
	}
	

	public static void main(String[] args) {
		
		DrawingApplication.launch(800, 600);
	}
}
