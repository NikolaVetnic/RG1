package topic2_P_image_processing.filters.displacement;

import mars.geometry.Vector;

public class Swirl extends DisplacementFilter {
	
	final double f;
	final double a;
	
	public Swirl(double f, double a) {
		this.f = f;
		this.a = a;
	}

	@Override
	public Vector source(Vector dst, Vector dim) {
		
		// Pretvaramo poziciju u normalizovane koordinate. Vektor s se nalazi u [-1,1]x[-1,1]. 
		Vector s = dst.div(dim).mul(2).sub(new Vector(1, 1));
		
		// Udaljenost od centra, vrednost iz [0,1].
		double r = s.norm();
		
		// s.angle() je ugao koji vektor s zaklapa sa x-osom. Na to dodajemo pomeraj prema navedenoj funkciji.
		double phi = s.angle() + a * Math.cos(f * r * Math.PI * 2);
		
		// Trazenu poziciju dobijamo sa izracunatim novim uglom, a istom udaljenosti od centra.
		Vector p = Vector.polar(r, phi);
		
		// Vracamo se u koordinatni sistem slike.
		return p.add(new Vector(1, 1)).div(2).mul(dim);
	}

}
