package topic5_isometry;

import mars.geometry.Vector;


public class Location {
	public final int x, y, z;
	

	public Location(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	public Location(Vector p, int z) {
		this((int) p.x, (int) p.y, z);
	}
}
