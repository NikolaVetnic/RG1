package topic2_P_image_processing.filters.displacement;

import mars.geometry.Vector;

public class Zoom extends DisplacementFilter {
	
	final double k;
	
	public Zoom(double k) {
		this.k = k;
	}

	@Override
	public Vector source(Vector dst, Vector dim) {
		return new Vector(dst.x / k, dst.y / k);
	}

}
