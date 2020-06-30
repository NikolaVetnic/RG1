package topic5_isometry;

import mars.geometry.Vector;


public class Map {

	private final int[][] levels;
	private final Vector size;
	
	
	
	public int levelAt(Vector p) {
		return levels[(int) p.y][(int) p.x];
	}
	
	
	public Vector size() {
		return size;
	}
	
	public int sizeX() {
		return (int) size.x;
	}

	public int sizeY() {
		return (int) size.y;
	}

	
	public Map(int[][] levels) {
		int sizeY = levels.length;
		if (sizeY == 0) {
			throw new IllegalArgumentException("Map height can not be 0");
		}
		
		int sizeX = levels[0].length;
		for (int y = 1; y < sizeY; y++) {
			if (levels[y].length != sizeX) {
				throw new IllegalArgumentException("Map rows must have equal length");
			}
		}
		
		this.levels = levels;
		this.size = new Vector(sizeX, sizeY);
	}
	
	
}
