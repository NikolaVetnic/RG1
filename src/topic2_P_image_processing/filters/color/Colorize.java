package topic2_P_image_processing.filters.color;

import javafx.scene.paint.Color;

public class Colorize extends ColorFilter {

	final double hue, saturation;
	
	public Colorize(double hue, double saturation) {
		this.hue = hue;
		this.saturation = saturation;
	}
	
	@Override
	public Color processColor(Color input) {
		
		return Color.hsb(hue, saturation, input.getBrightness(), input.getOpacity());
	}

}
