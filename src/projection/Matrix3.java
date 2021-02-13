package projection;

public class Matrix3 {

	public double[][] m;
	
	
	public Matrix3() {
		 
		m = new double[4][4];
		 
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[0].length; j++)
				m[i][j] = i == j ? 1 : 0;
	}
	
	
	static double turnToRad(double turn) { return turn * (2 * Math.PI); 	}
	static double turnToDeg(double turn) { return turn * 360.0; 			}
	static double radToTurn(double rad)  { return rad / (2 * Math.PI); 		}
	static double radToDeg(double rad)   { return rad * (180.0 * Math.PI); 	}
	static double degToTurn(double deg)  { return deg / 360.0; 				}
	static double degToRad(double deg)   { return (deg / 180.0) * Math.PI;	}
	
	
	void translation(double x, double y, double z) {
		
		this.m[0][3] = x;
		this.m[1][3] = y;
		this.m[2][3] = z;
	}
	
	
	void nullify() {
		
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[0].length; j++)
				this.m[i][j] = i == j ? 1 : 0;
	}
	
	
	void rotationX(double rx) {
		
		this.m[1][1] =  Math.cos(rx);		this.m[1][2] = -Math.sin(rx);
		this.m[2][1] =  Math.sin(rx);		this.m[2][2] =  Math.cos(rx);
	}
	
	
	void rotationY(double ry) {
		this.m[0][0] =  Math.cos(ry);		this.m[0][2] =  Math.sin(ry);
		this.m[2][0] = -Math.sin(ry);		this.m[2][2] =  Math.cos(ry);
	}
	
	
	void rotationZ(double rz) {
		this.m[0][0] =  Math.cos(rz);		this.m[0][1] = -Math.sin(rz);
		this.m[1][0] =  Math.sin(rz);		this.m[1][1] =  Math.cos(rz);
	}
	
	
//	double[][] Rx = {	 {            1,             0,             0, 0},
//	 					 {            0,  Math.cos(rx), -Math.sin(rx), 0},
					//	 {            0,  Math.sin(rx),  Math.cos(rx), 0},
					//	 {            0,             0,             0, 1}};
//
//double[][] Ry = {{ Math.cos(ry),             0,  Math.sin(ry), 0},
//	 				{            0,             1,             0, 0},
//	 				{-Math.sin(ry),             0,  Math.cos(ry), 0},
//	 				{            0,             0,             0, 1}};
//
//double[][] Rz = {{ Math.cos(rz), -Math.sin(rz), 0, 0},
//	 				{ Math.sin(rz),  Math.cos(rz), 0, 0},
//					{            0,             0, 1, 0},
//	 				{            0,             0, 0, 1}};
	
	void rotation(double rx, double ry, double rz) {
		
		Matrix3 rotX = new Matrix3();	rotX.rotationX(rx);
		Matrix3 rotY = new Matrix3();	rotY.rotationY(ry);
		Matrix3 rotZ = new Matrix3();	rotZ.rotationZ(rz);
		
		this.nullify();
	}
	
	 
	public String toString() {
		
		String output = "[ ";
		
		for (int i = 0; i < m.length; i++) {
			output += "[ ";
			for(int j = 0; j < m[0].length; j++) {
				output += m[i][j] + " ";
			}
			output += "] ";
		}
		
		return output += "]";
	}
	
	
	public static void main(String[] args) {
		
		Matrix3 m = new Matrix3();
		
		System.out.println(m);
	}
}
