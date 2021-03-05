package min_dist_2d;

import mars.geometry.Vector;

public class IndexedVector { 
	
	
	private Vector p;
	private int idx;
	
	
	public IndexedVector(Vector p, int idx) {
		this.p = p;
		this.idx = idx;
	}
	
	
	public Vector p() 	{ return p; 	}
	public int idx() 	{ return idx; 	}
}
