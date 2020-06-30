package topic2_P_image_processing.filters.color;

import javafx.scene.paint.Color;

public class Invert extends ColorFilter {

	@Override
	public Color processColor(Color input) {
		
		Color c = new Color(1 - input.getRed(), 1 - input.getGreen(), 1 - input.getBlue(), 1.0);
		
		return c;
	}

}
