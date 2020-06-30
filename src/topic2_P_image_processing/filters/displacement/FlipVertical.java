package topic2_P_image_processing.filters.displacement;

import mars.geometry.Vector;

public class FlipVertical extends DisplacementFilter {

	@Override
	public Vector source(Vector dst, Vector dim) {
		return new Vector(dst.x, dim.y - dst.y - 1);
	}
}
