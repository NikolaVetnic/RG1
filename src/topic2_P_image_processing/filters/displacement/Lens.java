package topic2_P_image_processing.filters.displacement;

import mars.geometry.Vector;

public class Lens extends DisplacementFilter {
	
	final double f;
	
	public Lens(double f) {
		this.f = f;
	}

	@Override
	public Vector source(Vector dst, Vector dim) {
		
		// Pretvaramo poziciju u normalizovane koordinate. Vektor s se nalazi u [-1,1]x[-1,1].
		Vector s = dst.div(dim).mul(2).sub(new Vector(1, 1));
		
		// Udaljenost od centra, vrednost iz [0,1].
		double r = s.norm();
		
		// s.angle() je ugao koji vektor s zaklapa sa x-osom. Na to dodajemo pomeraj prema navedenoj funkciji.
		double phi = s.angle();
		
		// Udaljenost src-a od centra je funkcija udaljenosti dst-a od centra
		double l = Math.pow(r, f);
		
		// Trazena pozicija izrazena u normalizovanim koordinatama
		Vector p = Vector.polar(l, phi);
		
		// Vracamo se u koordinatni sistem slike
		return p.add(new Vector(1, 1)).div(2).mul(dim);
	} 
}