package structure;

public class transformation {
	
	public double positionX, positionY, positionZ;
	public double rotationX, rotationY, rotationZ;
	public double scaleX, scaleY, scaleZ;
	
	public transformation (){
		positionX = positionY = positionZ = 0;
		rotationX = rotationY = rotationZ = 0;
		scaleX = scaleY = scaleZ = 1;
	}
	
	public void shift (double x, double y, double z){
		positionX = x;
		positionY = y;
		positionZ = z;
	}
	
	public void rotation (double x, double y, double z){
		rotationX = x;
		rotationY = y;
		rotationZ = z;
	}
	
	public void scale (double x, double y, double z){
		scaleX = x;
		scaleY = y;
		scaleZ = z;
	}
	
	public double[][] getMatrix(){
		double [][] matrix = {
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{0,0,0,1}
		};
		double [][] position = {
				{1,0,0,positionX},
				{0,1,0,positionY},
				{0,0,1,positionZ},
				{0,0,0,1}
		};
		
		double ch = (double) Math.cos(Math.toRadians(rotationZ));
		double sh = (double) Math.sin(Math.toRadians(rotationZ));
		double ca = (double) Math.cos(Math.toRadians(rotationX));
		double sa = (double) Math.sin(Math.toRadians(rotationX));
		double cb = (double) Math.cos(Math.toRadians(rotationY));
		double sb = (double) Math.sin(Math.toRadians(rotationY));
		double[][] rotation = { 
		{ ch * ca, sh * sb - ch * sa * cb, ch * sa * sb + sh * cb, 0 },
		{ sa, ca * cb, -ca * sb, 0 }, 
		{ -sh * ca, sh * sa * cb + ch * sb, -sh * sa * sb + ch * cb, 0 },
		{ 0, 0, 0, 1 } };
		
		double[][] scale = {
				{scaleX,0,0,0},
				{0,scaleY,0,0},
				{0,0,scaleZ,0},
				{0,0,0,1}
		};
		
		matrix = dotProduct(matrix,position );
		matrix = dotProduct(matrix,rotation );
		matrix = dotProduct(matrix,scale );
		
		return matrix;

		
	}
	
	private static double[][] dotProduct(double[][] a, double[][] b) {
		// initialization result
		double[][] result = new double[4][4];
		// Calculation
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				double c = 0;
				for (int i = 0; i < 4; i++)
					c += a[y][i] * b[i][x];
				result[y][x] = c;
			}
		}
		// return
		return result;
	}


}
