package topic2_P_image_processing.filters.color;

import javafx.scene.paint.Color;

public class Desaturate extends ColorFilter {
	
	final double saturationFactor;
	
	public Desaturate(double saturation) {
		this.saturationFactor = saturation;
	}

	@Override
	public Color processColor(Color input) {
		
		return Color.hsb(input.getHue(), 
						 input.getSaturation() * saturationFactor, 
						 input.getBrightness());
	}
}