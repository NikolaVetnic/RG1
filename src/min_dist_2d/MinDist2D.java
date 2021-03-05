package min_dist_2d;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mars.geometry.Vector;

public class MinDist2D {

	
	private ClosestPair cp;
	private IndexedVector[] points;
	
	
	public MinDist2D(Vector[] points) {
		if (points.length < 2)
			throw new IllegalArgumentException("No less than two points...");
		
//		List<Vector> vList = Arrays.asList(points);
//		Collections.shuffle(vList);
//		points = vList.toArray(points);
		
		this.points = new IndexedVector[points.length];
		for (int i = 0; i < points.length; i++) this.points[i] = new IndexedVector(points[i], i);
		
		this.cp = findDQ();
	}


	public int[] closestPair()	{ return new int[] { cp.p(), cp.q() };	}
	public double cpDist()		{ return cp.dist();						}


	private ClosestPair findDQ() {

		Arrays.sort(points, new XComp());
		
		for (int i = 0; i < points.length - 1; i++)
			if (points[i].p().distanceTo(points[i + 1].p()) < 1e-8)
				return new ClosestPair(0.0, points[i].idx(), points[i + 1].idx());
		
		return findDQ(0, points.length - 1);
	}


	private ClosestPair findDQ(int from, int to) {
		
		if (to == from)
			return new ClosestPair(Double.MAX_VALUE, from, to);
		
		if (to - from == 1)
			return new ClosestPair(points[from].p().distanceTo(points[to].p()), from, to);
		
		int median = (from + to) / 2;
		
		ClosestPair dl = findDQ(from, median);
		ClosestPair dr = findDQ(median + 1, to);
		
		ClosestPair minDist = dl.dist() < dr.dist() ? dl : dr;
		
		ClosestPair minCrossing = findMinCrossing(from, to, median, minDist.dist());
		
		return minDist.dist() < minCrossing.dist() ? minDist : minCrossing;
	}


	private ClosestPair findMinCrossing(int from, int to, int median, double delta) {
		
		double medianX = points[median].p().x;
		
		IndexedVector[] box = new IndexedVector[points.length];
		int boxCnt = 0;
		
		for (int i = from; i <= to; i++)
			if (Math.abs(points[i].p().x - medianX) < delta)
				box[boxCnt++] = points[i];
		
		Arrays.sort(box, 0, boxCnt, new YComp());
		
		double minDist = Double.MAX_VALUE;
		ClosestPair out = new ClosestPair(minDist, 0, 0);
		
		for (int i = 0; i < boxCnt - 1; i++) {
			
			IndexedVector current = box[i];
			
			for (int j = i + 1; j < boxCnt; j++) {
				if (box[j].p().y - current.p().y >= delta)
					break;
				
				double d = current.p().distanceTo(box[j].p());
				if (d < minDist) {
					minDist = d;
					out = new ClosestPair(minDist, current.idx(), box[j].idx());
				}
			}
		}
		
		return out;
	}
}
