package min_dist_2d;

public class ClosestPair {

	
	private double dist;
	private int p, q;
	
	
	public ClosestPair(double dist, int p, int q) {
		this.dist = dist;
		this.p = p;
		this.q = q;
	}


	public double dist() 	{ return dist; 	}
	public int p() 			{ return p; 	}
	public int q() 			{ return q; 	}
}
