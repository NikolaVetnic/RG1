package topic2_P_image_processing.filters.color;

import javafx.scene.paint.Color;

public class Sepia extends ColorFilter {

	@Override
	public Color processColor(Color input) {
		
		return Color.hsb(
						43, 
						0.5, 
						input.getBrightness(),
						input.getOpacity()
						);
	}
}
