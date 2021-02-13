package projection;

public class Vector3 {

	private double[][] p;
	
	
	public Vector3(double x, double y, double z) {
		this.p = new double[][] { {x}, {y}, {z}, {1} };
	}
	
	
	public void apply(Matrix3 a) {
		
		for (int j = 0; j < this.p.length; j++) {
			
			this.p[j][0] = 0.0;
			for (int k = 0; k < this.p[0].length; k++) {
				
				double sum = 0.0;
				for (int i = 0; i < a.m.length; i++)
					sum += this.p[i][k] * a.m[j][i];
				
				this.p[j][k] = sum;
			}
		}
	}
}
