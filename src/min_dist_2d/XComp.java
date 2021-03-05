package min_dist_2d;

import java.util.Comparator;

import mars.geometry.Vector;

public class XComp implements Comparator<IndexedVector> {
	
	@Override
	public int compare(IndexedVector o1, IndexedVector o2) {
		double d = o1.p().x - o2.p().x;
		if 		(d > 0) return  1;
		else if (d < 0) return -1;
		else			return  0;
	}
}
