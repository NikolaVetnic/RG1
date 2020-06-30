package topic2_P_image_processing.filters.misc;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Vignette extends Filter {

	@Override
	public Image process(Image input) {
		
		int h = (int) input.getHeight();
		int w = (int) input.getWidth();
		
		WritableImage output = new WritableImage(w, h);
		
		PixelReader pr = input.getPixelReader();
		PixelWriter pw = output.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				double dx = 2.0 * x / w - 1.0;
				double dy = 2.0 * y / h - 1.0;
				double d = Math.sqrt(dx * dx + dy * dy);
				
				if (d > 1) 
					d = 1;
				
				Color inputColor = pr.getColor(x, y);
				
				Color outputColor = Color.hsb(
						inputColor.getHue(),
						inputColor.getSaturation(),
						inputColor.getBrightness() * (1 - d),
						inputColor.getOpacity()
						);
				
				pw.setColor(x, y, outputColor);
			}
		}
		
		return output;
	}

}
