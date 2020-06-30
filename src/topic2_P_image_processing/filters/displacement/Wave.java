package topic2_P_image_processing.filters.displacement;

import mars.geometry.Vector;

public class Wave extends DisplacementFilter {
	
	final double amplitude, wavelength;
	
	public Wave(double amplitude, double wavelength) {
		this.amplitude = amplitude;
		this.wavelength = wavelength;
	}

	@Override
	public Vector source(Vector dst, Vector dim) {
		
		// Modifikacija, intenzivniji talasi na desnoj strani.
		double mod = (dst.x + dim.x / 2 + 1) / dim.x;
		
		// Na y koordinatu dst pozicije dodajemo pomeraj po x osi koji nam zavisi od x koordinate, 
		// x koordinata ostaje nepromenjena.
		//
		// Pomeraj po y je sinusna funkcija, samo joj parametar moramo skalirati da perioda bude 
		// wavelength umesto 2*pi.
		return new Vector(dst.x, dst.y + amplitude * Math.sin((mod * dst.x) / wavelength * 2 * Math.PI));
	}

}
