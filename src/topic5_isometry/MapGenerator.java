package topic5_isometry;

import mars.geometry.Vector;
import mars.random.RNG;
import mars.random.fixed.continuous.PerlinNoise;


public class MapGenerator {

	private final Vector size;
	private final long seed;

	private final int nNoiseLevels = 5;
	private final double factor = 1.62;
	private final double zoom = 16;
	private final double noiseZ = 0.5;
	
	
	private final double[] levelBoundaries = {
			 0.0, 							// voda - pesak
			 0.1, 							// pesak - trava
			 0.4, 							// trava - planina
			 0.8, 							// planina - sneg
			 Double.POSITIVE_INFINITY
	};
	

	// ------


	public MapGenerator(Vector size, long seed) {
		this.size = size;
		this.seed = seed;
	}
	

	private int levelForHeight(double h) {
		int l = 0;
		while (h > levelBoundaries[l++]);
		return l;
	}

	
	private double noiseHeight(Vector p, PerlinNoise[] pn) {
		Vector d = p;
		
		double amplitude = 1.0;
		double frequency = 1.0;
		double h = 0.0;
		
		for (int l = 0; l < nNoiseLevels; l++) {
			h += amplitude * pn[l].getValue(frequency * d.x / zoom, frequency * d.y / zoom, noiseZ);
			amplitude /= factor;
			frequency *= factor;
		}
		
		return h;
	}
	
	
	public Map generate() {
		RNG rng = new RNG(seed);
		
		PerlinNoise[] pn = new PerlinNoise[nNoiseLevels];
		for (int i = 0; i < pn.length; i++) {
			pn[i] = new PerlinNoise(rng.nextLong());
		}		
		
		int[][] levels = new int[(int) size.y][(int) size.x];
		
		for (Vector p : size) {
			levels[(int) p.y][(int) p.x] = levelForHeight(noiseHeight(p, pn));
		}
		
		return new Map(levels);
	}	
	
}
