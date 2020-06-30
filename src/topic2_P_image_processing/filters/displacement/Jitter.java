package topic2_P_image_processing.filters.displacement;

import java.util.Random;

import mars.geometry.Vector;

public class Jitter extends DisplacementFilter {
	
	final double r;
	Random random = new Random();
	
	public Jitter(double r) {
		this.r = r;
	}

	@Override
	public Vector source(Vector dst, Vector dim) {
		
		double phi = random.nextDouble();
		double d = r * random.nextDouble();
		
		return dst.add(Vector.polar(d, phi));
	}

}
