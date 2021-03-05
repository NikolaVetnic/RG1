package min_dist_2d;

import java.util.Comparator;

import mars.geometry.Vector;

public class YComp implements Comparator<IndexedVector> {
	
	@Override
	public int compare(IndexedVector o1, IndexedVector o2) {
		double d = o1.p().y - o2.p().y;
		if 		(d > 0) return  1;
		else if (d < 0) return -1;
		else			return  0;
	}
}
