package topic2_P_image_processing.filters.displacement;

import mars.geometry.Vector;

public class Rotate180 extends DisplacementFilter {

	@Override
	public Vector source(Vector dst, Vector dim) {
		
		return new Vector(dim.x - dst.x - 1, dim.y - dst.y - 1);
	}
}
