package topic2_P_image_processing.filters.color;

import javafx.scene.paint.Color;

public class Saturate extends ColorFilter {
	
	final double k;
	
	public Saturate(double k) {
		this.k = k;
	}

	@Override
	public Color processColor(Color input) {
		double s = input.getSaturation();
		
		return Color.hsb(
						input.getHue(),
						s + k * (1 - s),
						input.getBrightness(),
						input.getOpacity()
						);
	}

}
