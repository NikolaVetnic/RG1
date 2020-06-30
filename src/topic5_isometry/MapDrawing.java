package topic5_isometry;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Box;
import mars.geometry.Transformation;
import mars.geometry.Vector;


public class MapDrawing {

	private final Vector tx, ty, tz;
	
	
	private final Color[] levelColor = {
			Color.hsb(210, 0.7, 0.9),		// voda
			Color.hsb( 50, 0.5, 1.0),		// pesak
			Color.hsb(110, 0.6, 0.8),		// trava
			Color.hsb( 30, 0.6, 0.5),		// planina
			Color.hsb(  0, 0.0, 1.0)		// sneg
	};

	
	// ------
	
	
	public final int nLevels = levelColor.length;
	
	private final Transformation tRight;
	private final Transformation tLeft;
	private final Transformation tTop;

	private final Box unitBox = new Box(Vector.ZERO, Vector.UNIT_DIAGONAL);

	private final Color[] levelColorLeft  = new Color[nLevels];
	private final Color[] levelColorRight = new Color[nLevels];
		
	{
		for (int l = 0; l < nLevels; l++) {
			levelColorLeft[l]  = levelColor[l].deriveColor(0, 1, 0.8, 1);
			levelColorRight[l] = levelColor[l].deriveColor(0, 1, 0.6, 1);
		}
	}	
	

	public MapDrawing(Vector tx, Vector ty, Vector tz) {
		this.tx = tx;
		this.ty = ty;
		this.tz = tz;
		tRight = Transformation.mappingBase(tx, tz);
		tLeft  = Transformation.mappingBase(ty, tz);
		tTop   = Transformation.mappingBase(tx, ty).translate(tz);
	}
	

	/* // Elegantnija verzija, ali dosta sporija.
	 
	void drawFace(View view, Transformation t, Color c) {
		view.stateStore();
		view.addTransformation(t);
		view.setFill(c);
		view.fillRect(unitBox);
		view.stateRestore();
	}
		
		
	void drawCell(View view, Location l) {
		view.stateStore();

		Vector o = (tx.mul(l.x)).add(ty.mul(l.y)).add(tz.mul(l.z));
		view.addTransformation(Transformation.translation(o));
		
		drawFace(view, tRight, levelColorLeft [l.z]);
		drawFace(view, tLeft , levelColorRight[l.z]);
		drawFace(view, tTop  , levelColor     [l.z]);
		
		view.stateRestore();
	}

	*/
	
	void drawFace(View view, Transformation t, Color c) {
		view.addTransformation(t);
		view.setFill(c);
		view.fillRect(unitBox);
	}
		
		
	void drawCell(View view, Location l) {
		Transformation t = view.getTransformationWorldToWorldBase();

		Vector o = (tx.mul(l.x)).add(ty.mul(l.y)).add(tz.mul(l.z));
		Transformation to = t.applyAfter(Transformation.translation(o));
		view.setTransformation(to);
		
		drawFace(view, tRight, levelColorLeft [l.z]);
		view.setTransformation(to);
		drawFace(view, tLeft , levelColorRight[l.z]);
		view.setTransformation(to);
		drawFace(view, tTop  , levelColor     [l.z]);
		
		view.setTransformation(t);
	}


	public void drawMap(View view, Map map) {
		for (int y = map.sizeY() - 1; y >= 0; y--) {
			for (int x = map.sizeX() - 1; x >= 0; x--) {
				Vector p = new Vector(x, y);
				int levels = map.levelAt(p);
				for (int z = 0; z < levels; z++) {
					Location l = new Location(p, z);
					drawCell(view, l);
				}
			}
		}
	}
	
}
