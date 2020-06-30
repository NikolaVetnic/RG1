package topic2_P_image_processing.filters.color;

import javafx.scene.paint.Color;

public class Grayscale extends ColorFilter {

	@Override
	public Color processColor(Color input) {
		
		double r = input.getRed();
		double g = input.getGreen();
		double b = input.getBlue();
		
		double d = (r + g + b) / 3.0;
		
		return new Color(d, d, d, input.getOpacity());
	}

}
