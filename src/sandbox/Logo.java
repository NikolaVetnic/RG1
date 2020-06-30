package sandbox;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetInteger;
import mars.geometry.Vector;

public class Logo implements Drawing {
	
	@GadgetBoolean
	boolean colors = true;
	
	@GadgetInteger(min = 0, max = 10)
	int stroke = 5;
	
	public Color paleGold = new Color(0.8039, 0.7059, 0.3137, 1.0);
	public Color darkGray = Color.gray(0.125);
	public Color deepPurple = new Color(0.2353, 0.1765, 0.3529, 1.0);
	
	private Vector[] letter1aGold = new Vector[] {
			new Vector(-510, 179),
			new Vector(-510, 126),
			new Vector(-480, 126),
			new Vector(-391, 179),
			new Vector(-431, 179),
			new Vector(-480, 149),
			new Vector(-480, 179)
	};
	
	private Vector[] letter1bGold = new Vector[] {
			new Vector(-510, 29),
			new Vector(-391, 29),
			new Vector(-391, 15),
			new Vector(-480, 15),
			new Vector(-480, -120),
			new Vector(-510, -120)
	};
	
	private Vector[] letter1cGold = new Vector[] {
			new Vector(-417, -46),
			new Vector(-391, -46),
			new Vector(-391, -120),
			new Vector(-417, -120)
	};
	
	private Vector[] letter1aGray = new Vector[] {
			new Vector(-510, 126),
			new Vector(-480, 126),
			new Vector(-391, 179),
			new Vector(-391, 163),
			new Vector(-480, 113),
			new Vector(-510, 113)
	};
	
	private Vector[] letter1bGray = new Vector[] {
			new Vector(-480, 15),
			new Vector(-391, 15),
			new Vector(-391, 0),
			new Vector(-480, 0)
	};
	
	private Vector[] letter1cGray = new Vector[] {
			new Vector(-510, -120),
			new Vector(-480, -120),
			new Vector(-480, -132),
			new Vector(-510, -132)
	};
	
	private Vector[] letter1dGray = new Vector[] {
			new Vector(-417, -120),
			new Vector(-391, -120),
			new Vector(-391, -132),
			new Vector(-417, -132)
	};
	
	private Vector[] letter1aPurple = new Vector[] {
			new Vector(-510, 113),
			new Vector(-480, 113),
			new Vector(-391, 163),
			new Vector(-391, 126),
			new Vector(-480, 72),
			new Vector(-510, 72)
	};
	
	private Vector[] letter1bPurple = new Vector[] {
			new Vector(-480, 0),
			new Vector(-391, 0),
			new Vector(-391, -40),
			new Vector(-480, -40)
	};
	
	private Vector[] letter1cPurple = new Vector[] {
			new Vector(-510, -132),
			new Vector(-510, -171),
			new Vector(-480, -171),
			new Vector(-480, -132)
	};
	
	private Vector[] letter1dPurple = new Vector[] {
			new Vector(-417, -132),
			new Vector(-391, -132),
			new Vector(-391, -171),
			new Vector(-417, -171)
	};
	
	private Vector[] letter1aStroke = new Vector[] {
			new Vector(-510, 179),
			new Vector(-480, 179),
			new Vector(-480, 149),
			new Vector(-431, 179),
			new Vector(-391, 179),
			new Vector(-391, 126),
			new Vector(-480, 72),
			new Vector(-510, 72)
	};
	
	private Vector[] letter1bStroke = new Vector[] {
			new Vector(-510, 29),
			new Vector(-391, 29),
			new Vector(-391, -40),
			new Vector(-480, -40),
			new Vector(-480, -171),
			new Vector(-510, -171)
	};
	
	private Vector[] letter1cStroke = new Vector[] {
			new Vector(-417, -46),
			new Vector(-417, -171),
			new Vector(-391, -171),
			new Vector(-391, -46)
	};
	
	
	private Vector[] letter2aGold = new Vector[] {
			new Vector(-367, 179),
			new Vector(-342, 179),
			new Vector(-342, 126),
			new Vector(-367, 126)
	};
	
	private Vector[] letter2aGray = new Vector[] {
			new Vector(-367, 126),
			new Vector(-342, 126),
			new Vector(-342, 113),
			new Vector(-367, 113)
	};
	
	private Vector[] letter2aPurple = new Vector[] {
			new Vector(-367, 113),
			new Vector(-342, 113),
			new Vector(-342, 72),
			new Vector(-367, 72)
	};
	
	private Vector[] letter2aStroke = new Vector[] {
			new Vector(-367, 179),
			new Vector(-342, 179),
			new Vector(-342, 72),
			new Vector(-367, 72)
	};

	
	private Vector[] letter3aGold = new Vector[] {
			new Vector(-316, 179),
			new Vector(-289, 179),
			new Vector(-289, 149),
			new Vector(-236, 179),
			new Vector(-199, 179),
			new Vector(-289, 126),
			new Vector(-316, 126)
	};

	private Vector[] letter3bGold = new Vector[] {
			new Vector(-316, -10),
			new Vector(-199, 56),
			new Vector(-199, 38),
			new Vector(-289, -13),
			new Vector(-289, -120),
			new Vector(-316, -120)
	};

	private Vector[] letter3cGold = new Vector[] {
			new Vector(-225, -46),
			new Vector(-225, -120),
			new Vector(-199, -120),
			new Vector(-199, -46)
	};
	
	private Vector[] letter3aGray = new Vector[] {
			new Vector(-316, 126),
			new Vector(-289, 126),
			new Vector(-199, 179),
			new Vector(-199, 163),
			new Vector(-289, 113),
			new Vector(-316, 113)
	};
	
	private Vector[] letter3bGray = new Vector[] {
			new Vector(-289, -13),
			new Vector(-199, 38),
			new Vector(-199, 25),
			new Vector(-289, -26),
			new Vector(-289, -120),
			new Vector(-316, -120),
			new Vector(-316, -132),
			new Vector(-289, -132),
			new Vector(-289, -120)
	};
	
	private Vector[] letter3cGray = new Vector[] {
			new Vector(-225, -120),
			new Vector(-225, -132),
			new Vector(-199, -132),
			new Vector(-199, -120)
	};
	
	private Vector[] letter3aPurple = new Vector[] {
			new Vector(-316, 113),
			new Vector(-289, 113),
			new Vector(-199, 163),
			new Vector(-199, 123),
			new Vector(-289, 72),
			new Vector(-316, 72)
	};
	
	private Vector[] letter3bPurple = new Vector[] {
			new Vector(-289, -26),
			new Vector(-199, 25),
			new Vector(-199, -18),
			new Vector(-289, -69),
			new Vector(-289, -171),
			new Vector(-316, -171),
			new Vector(-316, -132),
			new Vector(-289, -132)
	};
	
	private Vector[] letter3cPurple = new Vector[] {
			new Vector(-225, -132),
			new Vector(-225, -171),
			new Vector(-199, -171),
			new Vector(-199, -132)
	};
	
	private Vector[] letter3aStroke = new Vector[] {
			new Vector(-316, 179),
			new Vector(-289, 179),
			new Vector(-289, 149),
			new Vector(-236, 179),
			new Vector(-199, 179),
			new Vector(-199, 123),
			new Vector(-289, 72),
			new Vector(-316, 72)
	};
	
	private Vector[] letter3bStroke = new Vector[] {
			new Vector(-316, -10),
			new Vector(-199, 56),
			new Vector(-199, -18),
			new Vector(-289, -69),
			new Vector(-289, -171),
			new Vector(-316, -171)
	};
	
	private Vector[] letter3cStroke = new Vector[] {
			new Vector(-225, -46),
			new Vector(-199, -46),
			new Vector(-199, -171),
			new Vector(-225, -171)
	};
	
	
	private Vector[] letter4aGold = new Vector[] {
			new Vector(-174, 179),
			new Vector(-58, 179),
			new Vector(-58, 161),
			new Vector(-148, 161),
			new Vector(-148, -35),
			new Vector(-174, -35)
	};
	
	private Vector[] letter4bGold = new Vector[] {
			new Vector(-174, -104),
			new Vector(-84, -104),
			new Vector(-84, 93),
			new Vector(-58, 92),
			new Vector(-58, -120),
			new Vector(-174, -120)
	};
	
	private Vector[] letter4aGray = new Vector[] {
			new Vector(-174, 179 - 18),
			new Vector(-58, 179 - 18),
			new Vector(-58, 149),
			new Vector(-148, 149),
			new Vector(-148, -46),
			new Vector(-174, -46)
	};
	
	private Vector[] letter4bGray = new Vector[] {
			new Vector(-174, -120),
			new Vector(-58, -120),
			new Vector(-58, -132),
			new Vector(-174, -132)
	};
	
	private Vector[] letter4aPurple = new Vector[] {
			new Vector(-174, 179 - 30),
			new Vector(-58, 179 - 30),
			new Vector(-58, 106),
			new Vector(-148, 106),
			new Vector(-148, -88),
			new Vector(-174, -88)
	};
	
	private Vector[] letter4bPurple = new Vector[] {
			new Vector(-174, -132),
			new Vector(-58, -132),
			new Vector(-58, -171),
			new Vector(-174, -171)
	};
	
	private Vector[] letter4aStroke = new Vector[] {
			new Vector(-174, 179),
			new Vector(-58, 179),
			new Vector(-58, 106),
			new Vector(-148, 106),
			new Vector(-148, -88),
			new Vector(-174, -88)
	};
	
	private Vector[] letter4bStroke = new Vector[] {
			new Vector(-174, -104),
			new Vector(-84, -104),
			new Vector(-84, 93),
			new Vector(-58, 92),
			new Vector(-58, -171),
			new Vector(-174, -171)
	};
	
	
	private Vector[] letter5aGold = new Vector[] {
			new Vector(-316 + 282, 179),
			new Vector(-289 + 282, 179),
			new Vector(-289 + 282, 149),
			new Vector(-236 + 282, 179),
			new Vector(-199 + 282, 179),
			new Vector(-289 + 282, 126),
			new Vector(-316 + 282, 126)
	};
	
	private Vector[] letter5bGold = new Vector[] {
			new Vector(-174 + 142, -104),
			new Vector(-84 + 142, -104),
			new Vector(-84 + 142, 93),
			new Vector(-58 + 142, 92),
			new Vector(-58 + 142, -120),
			new Vector(-174 + 142, -120)
	};
	
	private Vector[] letter5aGray = new Vector[] {
			new Vector(-316 + 282, 126),
			new Vector(-289 + 282, 126),
			new Vector(-199 + 282, 179),
			new Vector(-199 + 282, 163),
			new Vector(-289 + 282, 113),
			new Vector(-316 + 282, 113)
	};
	
	private Vector[] letter5bGray = new Vector[] {
			new Vector(-174 + 142, -120),
			new Vector(-58 + 142, -120),
			new Vector(-58 + 142, -132),
			new Vector(-174 + 142, -132)
	};
	
	private Vector[] letter5aPurple = new Vector[] {
			new Vector(-316 + 282, 113),
			new Vector(-289 + 282, 113),
			new Vector(-199 + 282, 163),
			new Vector(-199 + 282, 123),
			new Vector(-289 + 282, 72),
			new Vector(-316 + 282, 72)
	};
	
	private Vector[] letter5bPurple = new Vector[] {
			new Vector(-174 + 142, -132),
			new Vector(-58 + 142, -132),
			new Vector(-58 + 142, -171),
			new Vector(-174 + 142, -171)
	};
	
	private Vector[] letter5aStroke = new Vector[] {
			new Vector(-316 + 282, 179),
			new Vector(-289 + 282, 179),
			new Vector(-289 + 282, 149),
			new Vector(-236 + 282, 179),
			new Vector(-199 + 282, 179),
			new Vector(-199 + 282, 123),
			new Vector(-289 + 282, 72),
			new Vector(-316 + 282, 72)
	};
	
	private Vector[] letter5bStroke = new Vector[] {
			new Vector(-174 + 142, -104),
			new Vector(-84 + 142, -104),
			new Vector(-84 + 142, 93),
			new Vector(-58 + 142, 92),
			new Vector(-58 + 142, -171),
			new Vector(-174 + 142, -171)
	};
	
	
	private Vector[] letter6aGold = new Vector[] {
			new Vector(-316 + 282 + 140, 179),
			new Vector(-289 + 282 + 140, 179),
			new Vector(-289 + 282 + 140, 149),
			new Vector(-236 + 282 + 140, 179),
			new Vector(-199 + 282 + 140, 179),
			new Vector(-289 + 282 + 140, 126),
			new Vector(-316 + 282 + 140, 126)
	};
	
	private Vector[] letter6bGold = new Vector[] {
			new Vector(-174 + 142 + 140, -104),
			new Vector(-84 + 142 + 140, -104),
			new Vector(-84 + 142 + 140, 93),
			new Vector(-58 + 142 + 140, 92),
			new Vector(-58 + 142 + 140, -120),
			new Vector(-174 + 142 + 140, -120)
	};
	
	private Vector[] letter6aGray = new Vector[] {
			new Vector(-316 + 282 + 140, 126),
			new Vector(-289 + 282 + 140, 126),
			new Vector(-199 + 282 + 140, 179),
			new Vector(-199 + 282 + 140, 163),
			new Vector(-289 + 282 + 140, 113),
			new Vector(-316 + 282 + 140, 113)
	};
	
	private Vector[] letter6bGray = new Vector[] {
			new Vector(-174 + 142 + 140, -120),
			new Vector(-58 + 142 + 140, -120),
			new Vector(-58 + 142 + 140, -132),
			new Vector(-174 + 142 + 140, -132)
	};
	
	private Vector[] letter6aPurple = new Vector[] {
			new Vector(-316 + 282 + 140, 113),
			new Vector(-289 + 282 + 140, 113),
			new Vector(-199 + 282 + 140, 163),
			new Vector(-199 + 282 + 140, 123),
			new Vector(-289 + 282 + 140, 72),
			new Vector(-316 + 282 + 140, 72)
	};
	
	private Vector[] letter6bPurple = new Vector[] {
			new Vector(-174 + 142 + 140, -132),
			new Vector(-58 + 142 + 140, -132),
			new Vector(-58 + 142 + 140, -171),
			new Vector(-174 + 142 + 140, -171)
	};
	
	private Vector[] letter6aStroke = new Vector[] {
			new Vector(-316 + 282 + 140, 179),
			new Vector(-289 + 282 + 140, 179),
			new Vector(-289 + 282 + 140, 149),
			new Vector(-236 + 282 + 140, 179),
			new Vector(-199 + 282 + 140, 179),
			new Vector(-199 + 282 + 140, 123),
			new Vector(-289 + 282 + 140, 72),
			new Vector(-316 + 282 + 140, 72)
	};
	
	private Vector[] letter6bStroke = new Vector[] {
			new Vector(-174 + 142 + 140, -104),
			new Vector(-84 + 142 + 140, -104),
			new Vector(-84 + 142 + 140, 93),
			new Vector(-58 + 142 + 140, 92),
			new Vector(-58 + 142 + 140, -171),
			new Vector(-174 + 142 + 140, -171)
	};
	
	
	private Vector[] letter7aGold = new Vector[] {
			new Vector(-174 + 422, 179),
			new Vector(-58 + 422, 179),
			new Vector(-58 + 422, 161),
			new Vector(-148 + 422, 161),
			new Vector(-148 + 422, -35),
			new Vector(-174 + 422, -35)
	};
	
	private Vector[] letter7bGold = new Vector[] {
			new Vector(-174 + 422, -104),
			new Vector(-84 + 422, -104),
			new Vector(-84 + 422, 93),
			new Vector(-58 + 422, 92),
			new Vector(-58 + 422, -120),
			new Vector(-174 + 422, -120)
	};
	
	private Vector[] letter7aGray = new Vector[] {
			new Vector(-174 + 422, 179 - 18),
			new Vector(-58 + 422, 179 - 18),
			new Vector(-58 + 422, 149),
			new Vector(-148 + 422, 149),
			new Vector(-148 + 422, -46),
			new Vector(-174 + 422, -46)
	};
	
	private Vector[] letter7bGray = new Vector[] {
			new Vector(-174 + 422, -120),
			new Vector(-58 + 422, -120),
			new Vector(-58 + 422, -132),
			new Vector(-174 + 422, -132)
	};
	
	private Vector[] letter7aPurple = new Vector[] {
			new Vector(-174 + 422, 179 - 30),
			new Vector(-58 + 422, 179 - 30),
			new Vector(-58 + 422, 106),
			new Vector(-148 + 422, 106),
			new Vector(-148 + 422, -88),
			new Vector(-174 + 422, -88)
	};
	
	private Vector[] letter7bPurple = new Vector[] {
			new Vector(-174 + 422, -132),
			new Vector(-58 + 422, -132),
			new Vector(-58 + 422, -171),
			new Vector(-174 + 422, -171)
	};
	
	private Vector[] letter7aStroke = new Vector[] {
			new Vector(-174 + 422, 179),
			new Vector(-58 + 422, 179),
			new Vector(-58 + 422, 106),
			new Vector(-148 + 422, 106),
			new Vector(-148 + 422, -88),
			new Vector(-174 + 422, -88)
	};
	
	private Vector[] letter7bStroke = new Vector[] {
			new Vector(-174 + 422, -104),
			new Vector(-84 + 422, -104),
			new Vector(-84 + 422, 93),
			new Vector(-58 + 422, 92),
			new Vector(-58 + 422, -171),
			new Vector(-174 + 422, -171)
	};
	
	
	private Vector[] letter8aGold = new Vector[] {
			new Vector(-316 + 282 + 420, 179),
			new Vector(-289 + 282 + 420, 179),
			new Vector(-289 + 282 + 420, 149),
			new Vector(-236 + 282 + 420, 179),
			new Vector(-199 + 282 + 420, 179),
			new Vector(-289 + 282 + 420, 126),
			new Vector(-316 + 282 + 420, 126)
	};
	
	private Vector[] letter8bGold = new Vector[] {
			new Vector(-174 + 142 + 420, -104),
			new Vector(-84 + 142 + 420, -104),
			new Vector(-84 + 142 + 420, 93),
			new Vector(-58 + 142 + 420, 92),
			new Vector(-58 + 142 + 420, -120),
			new Vector(-174 + 142 + 420, -120)
	};
	
	private Vector[] letter8aGray = new Vector[] {
			new Vector(-316 + 282 + 420, 126),
			new Vector(-289 + 282 + 420, 126),
			new Vector(-199 + 282 + 420, 179),
			new Vector(-199 + 282 + 420, 163),
			new Vector(-289 + 282 + 420, 113),
			new Vector(-316 + 282 + 420, 113)
	};
	
	private Vector[] letter8bGray = new Vector[] {
			new Vector(-174 + 142 + 420, -120),
			new Vector(-58 + 142 + 420, -120),
			new Vector(-58 + 142 + 420, -132),
			new Vector(-174 + 142 + 420, -132)
	};
	
	private Vector[] letter8aPurple = new Vector[] {
			new Vector(-316 + 282 + 420, 113),
			new Vector(-289 + 282 + 420, 113),
			new Vector(-199 + 282 + 420, 163),
			new Vector(-199 + 282 + 420, 123),
			new Vector(-289 + 282 + 420, 72),
			new Vector(-316 + 282 + 420, 72)
	};
	
	private Vector[] letter8bPurple = new Vector[] {
			new Vector(-174 + 142 + 420, -132),
			new Vector(-58 + 142 + 420, -132),
			new Vector(-58 + 142 + 420, -171),
			new Vector(-174 + 142 + 420, -171)
	};
	
	private Vector[] letter8aStroke = new Vector[] {
			new Vector(-316 + 282 + 420, 179),
			new Vector(-289 + 282 + 420, 179),
			new Vector(-289 + 282 + 420, 149),
			new Vector(-236 + 282 + 420, 179),
			new Vector(-199 + 282 + 420, 179),
			new Vector(-199 + 282 + 420, 123),
			new Vector(-289 + 282 + 420, 72),
			new Vector(-316 + 282 + 420, 72)
	};
	
	private Vector[] letter8bStroke = new Vector[] {
			new Vector(-174 + 142 + 420, -104),
			new Vector(-84 + 142 + 420, -104),
			new Vector(-84 + 142 + 420, 93),
			new Vector(-58 + 142 + 420, 92),
			new Vector(-58 + 142 + 420, -171),
			new Vector(-174 + 142 + 420, -171)
	};
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.875));
		
		Color c1 = colors ? paleGold : Color.TRANSPARENT;
		Color c2 = colors ? darkGray : Color.TRANSPARENT;
		Color c3 = colors ? deepPurple : Color.TRANSPARENT;
		
		
		
		// Letter K, Stroke
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1aStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1bStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1cStroke);
		
		// Letter K, Gold Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1aGold);
		view.setFill(c1);
		view.fillPolygon(letter1aGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1bGold);
		view.setFill(c1);
		view.fillPolygon(letter1bGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1cGold);
		view.setFill(c1);
		view.fillPolygon(letter1cGold);
		
		// Letter K, Gray Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1aGray);
		view.setFill(c2);
		view.fillPolygon(letter1aGray);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1bGray);
		view.setFill(c2);
		view.fillPolygon(letter1bGray);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1cGray);
		view.setFill(c2);
		view.fillPolygon(letter1cGray);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1dGray);
		view.setFill(c2);
		view.fillPolygon(letter1dGray);
		
		// Letter K, Purple Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1aPurple);
		view.setFill(c3);
		view.fillPolygon(letter1aPurple);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1bPurple);
		view.setFill(c3);
		view.fillPolygon(letter1bPurple);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1cPurple);
		view.setFill(c3);
		view.fillPolygon(letter1cPurple);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter1dPurple);
		view.setFill(c3);
		view.fillPolygon(letter1dPurple);
		
		
		
		// Apostrophe, Stroke
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter2aStroke);
		
		// Apostrophe, Gold Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter2aGold);
		view.setFill(c1);
		view.fillPolygon(letter2aGold);
		
		// Apostrophe, Gray Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter2aGray);
		view.setFill(c2);
		view.fillPolygon(letter2aGray);
		
		// Apostrophe, PurpleLayer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter2aPurple);
		view.setFill(c3);
		view.fillPolygon(letter2aPurple);
		

		
		// Letter N, Stroke
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3aStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3bStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3cStroke);
		
		// Letter N, Gold Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3aGold);
		view.setFill(c1);
		view.fillPolygon(letter3aGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3bGold);
		view.setFill(c1);
		view.fillPolygon(letter3bGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3cGold);
		view.setFill(c1);
		view.fillPolygon(letter3cGold);
				
		// Letter N, Gray Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3aGray);
		view.setFill(c2);
		view.fillPolygon(letter3aGray);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3bGray);
		view.setFill(c2);
		view.fillPolygon(letter3bGray);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3cGray);
		view.setFill(c2);
		view.fillPolygon(letter3cGray);
		
		// Letter N, Purple Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3aPurple);
		view.setFill(c3);
		view.fillPolygon(letter3aPurple);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3bPurple);
		view.setFill(c3);
		view.fillPolygon(letter3bPurple);	
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter3cPurple);
		view.setFill(c3);
		view.fillPolygon(letter3cPurple);
		
		
		
		// Letter O, Stroke
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter4aStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter4bStroke);
				
		// Letter O, Purple Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter4aPurple);
		view.setFill(c3);
		view.fillPolygon(letter4aPurple);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter4bPurple);
		view.setFill(c3);
		view.fillPolygon(letter4bPurple);
				
		// Letter O, Gray Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter4aGray);
		view.setFill(c2);
		view.fillPolygon(letter4aGray);

		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter4bGray);
		view.setFill(c2);
		view.fillPolygon(letter4bGray);
		
		// Letter O, Gold Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter4aGold);
		view.setFill(c1);
		view.fillPolygon(letter4aGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter4bGold);
		view.setFill(c1);
		view.fillPolygon(letter4bGold);
		
		
		
		// Letter S, Stroke
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter5aStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter5bStroke);
		
		// Letter S, Purple Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter5aPurple);
		view.setFill(c3);
		view.fillPolygon(letter5aPurple);		

		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter5bPurple);
		view.setFill(c3);
		view.fillPolygon(letter5bPurple);
		
		// Letter S, Gray Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter5aGray);
		view.setFill(c2);
		view.fillPolygon(letter5aGray);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter5aGray);
		view.setFill(c2);
		view.fillPolygon(letter5bGray);
		
		// Letter S, Gold Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter5aGold);
		view.setFill(c1);
		view.fillPolygon(letter5aGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter5bGold);
		view.setFill(c1);
		view.fillPolygon(letter5bGold);
		
		
		
		// Letter S, Stroke
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter6aStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter6bStroke);
		
		// Letter S, Purple Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter6aPurple);
		view.setFill(c3);
		view.fillPolygon(letter6aPurple);		

		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter6bPurple);
		view.setFill(c3);
		view.fillPolygon(letter6bPurple);
		
		// Letter S, Gray Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter6aGray);
		view.setFill(c2);
		view.fillPolygon(letter6aGray);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter6aGray);
		view.setFill(c2);
		view.fillPolygon(letter6bGray);
		
		// Letter S, Gold Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter6aGold);
		view.setFill(c1);
		view.fillPolygon(letter6aGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter6bGold);
		view.setFill(c1);
		view.fillPolygon(letter6bGold);
		

		
		// Letter O, Stroke
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter7aStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter7bStroke);
				
		// Letter O, Purple Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter7aPurple);
		view.setFill(c3);
		view.fillPolygon(letter7aPurple);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter7bPurple);
		view.setFill(c3);
		view.fillPolygon(letter7bPurple);
				
		// Letter O, Gray Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter7aGray);
		view.setFill(c2);
		view.fillPolygon(letter7aGray);

		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter7bGray);
		view.setFill(c2);
		view.fillPolygon(letter7bGray);
		
		// Letter O, Gold Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter7aGold);
		view.setFill(c1);
		view.fillPolygon(letter7aGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter7bGold);
		view.setFill(c1);
		view.fillPolygon(letter7bGold);
		
		
		
		// Letter S, Stroke
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter8aStroke);
		
		view.setLineWidth(stroke);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter8bStroke);
		
		// Letter S, Purple Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter8aPurple);
		view.setFill(c3);
		view.fillPolygon(letter8aPurple);		

		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter8bPurple);
		view.setFill(c3);
		view.fillPolygon(letter8bPurple);
		
		// Letter S, Gray Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter8aGray);
		view.setFill(c2);
		view.fillPolygon(letter8aGray);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter8aGray);
		view.setFill(c2);
		view.fillPolygon(letter8bGray);
		
		// Letter S, Gold Layer
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter8aGold);
		view.setFill(c1);
		view.fillPolygon(letter8aGold);
		
		view.setLineWidth(1);
		view.setStroke(Color.BLACK);
		view.strokePolygon(letter8bGold);
		view.setFill(c1);
		view.fillPolygon(letter8bGold);
	}

	public static void main(String[] args) {
		DrawingApplication.launch(1040, 450);
	}
}
