package topic2_P_image_processing.filters.color;

import javafx.scene.paint.Color;

public class Accent extends ColorFilter {

	final double accentHue;		// koji hue se akcentuje
	final double delta = 30;	// koliko odstupanje se tolerise

	public Accent(double hue) {
		this.accentHue = hue;
	}
	
	@Override
	public Color processColor(Color input) {
		
		double dHue = Math.abs(input.getHue() - accentHue);
		
		dHue = dHue > 180 ? 360 - dHue : dHue;
		
		if (dHue < delta) return input;
		else return Color.hsb(input.getHue(), 0, input.getBrightness(), input.getOpacity());
	}

}
