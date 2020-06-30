package sandbox;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;
import topic2_P_image_processing.filters.color.ColorFilter;

class FilterBrightness extends ColorFilter {
	
	Color[] palette = {
			Color.rgb(15, 56, 15),
			Color.rgb(48, 98, 48),
			Color.rgb(139, 172, 15),
			Color.rgb(155, 188, 15),
			Color.TRANSPARENT
	};

	
	@Override
	public Color processColor(Color input) {
		
		int index = (int) (input.getBrightness() * 100) / 25;
		
		return palette[index];
	}
}

class FilterRGB extends ColorFilter {
	
	Color[] palette = {
			Color.rgb(15, 56, 15),
			Color.rgb(48, 98, 48),
			Color.rgb(139, 172, 15),
			Color.rgb(155, 188, 15)
	};
	
	
	int[] arrR = {
			(int) (palette[0].getRed() * 255),
			(int) (palette[1].getRed() * 255),
			(int) (palette[2].getRed() * 255),
			(int) (palette[3].getRed() * 255)
	};
	
	int[] arrG = {
			(int) (palette[0].getGreen() * 255),
			(int) (palette[1].getGreen() * 255),
			(int) (palette[2].getGreen() * 255),
			(int) (palette[3].getGreen() * 255)
	};
	
	int[] arrB = {
			(int) palette[0].getBlue(),
			(int) palette[1].getBlue(),
			(int) palette[2].getBlue(),
			(int) palette[3].getBlue()
	};
	

	@Override
	public Color processColor(Color input) {
		
		int r = (int) (input.getRed() * 255);
		int g = (int) (input.getGreen() * 255);
		int b = (int) (input.getBlue() * 255);
		
		int choiceR = 0, choiceG = 0, choiceB = 0;
		
		for (int i = 0; i < arrR.length - 1; i++) {
			
			// za ulaznu boju se racuna kojoj GB boji je najbliza
			int midR = (arrR[i + 1] - arrR[i]) / 2 + arrR[i];
			int midG = (arrG[i + 1] - arrG[i]) / 2 + arrG[i];
			int midB = (arrB[i + 1] - arrB[i]) / 2 + arrB[i];
			
			if (r > midR) choiceR++;
			if (g > midG) choiceG++;
			if (b > midB) choiceB++;
		}
		
		// samo ako su R i B bolje poklapanje biram na osnovu njih
		if (choiceR == choiceB)
			return palette[choiceR];
		else
			return palette[choiceG];
	}
}

public class GameBoyScreen implements Drawing {
	
	@GadgetInteger(min = 2, max = 10)
	Integer pixelSize = 4;
	
	@GadgetInteger(min = 0, max = 13)
	Integer imageIndex = 0;
	
	@GadgetBoolean
	boolean onlyBrightness = false;
	
	
	String[] fileNames = {
			"Mona Lisa.jpg",
			"building.jpg",
			"catparty.jpg",
			"christmas.jpg",
			"couple.jpg",
			"dive.jpg",
			"doggo.jpg",
			"fall.jpg",
			"forecast.jpg",
			"kitchen.jpg",
			"meterologist.jpg",
			"office.jpg",
			"skirts.jpg",
			"waiting.jpg"
	};
	
	
	public Image display(Image input) {
		
		int w = (int) input.getWidth();
		int h = (int) input.getHeight();
		
		PixelReader pr = input.getPixelReader();
		
		WritableImage display = new WritableImage(w, h);
		PixelWriter pw = display.getPixelWriter();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				Color inputColor;
				
				if (x % pixelSize != 0 && y % pixelSize != 0)
					inputColor = pr.getColor(x / pixelSize * pixelSize, y / pixelSize * pixelSize);
				else
					inputColor = Color.rgb(48, 98, 48);
				pw.setColor(x, y, inputColor); 
			}
		}
		
		return display;
	}
	

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.25));
		
		Image image = new Image("images/" + fileNames[imageIndex]);
		Image gbBrightness = new FilterBrightness().process(image);
		Image gbRGB = new FilterRGB().process(image);
		
		// boolean Gadget bira da li se za racunanje koriste RGB ili samo Brightness komponenta
		view.drawImageCentered(Vector.ZERO, !onlyBrightness ? display(gbRGB) : display(gbBrightness));
		
		String caption = onlyBrightness ? "GB Filter Using Brightness only" : "GB Filter Using RGB";
		
		DrawingUtils.drawInfoText(view, "Image: " + fileNames[imageIndex] + "   Filter: " + caption);
	}
	
	
	public static void main(String[] args) {
		
		DrawingApplication.launch(800, 600);
	}
}