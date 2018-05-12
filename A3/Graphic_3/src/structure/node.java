package structure;

public class node {
	
	public double x,y,z;
	
	public node (){
		x = 0;
		y = 0;
		z = 0;
	}
	
	public node (double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	public void Negative_value (){
		x = -x;
		y = -y;
		z = -z;
	}
	
	
	
	
	public static node Addition (node... p){
		node result = new node();
		for (int i = 0; i < p.length; i++){
			result.x += p[i].x;
			result.y += p[i].y;
			result.z += p[i].z;
		}
		
		return result;
	}
	
	public static node subtraction (node P, node Q){
		node result = new node();
		
		result.x  = P.x - Q.x;
		result.y  = P.y - Q.y;
		result.z  = P.z - Q.z;
		
		return result;
	
	}
	
	public static node multiply (double x, node y){
		
		node result = new node();
		
		result.x = y.x * x;
		result.y = y.y * x;
		result.z = y.z * x;
		
		return result;
	}
	
	public void dotPro (node N){
		this.x *= N.x;
		this.y *= N.y;
		this.z *= N.z;
	}
	
	public static node crossPro (node P, node Q){
		node result = new node();
		result.x = (P.y* Q.z) - (P.z * Q.y);
		result.y = (P.z* Q.x) - (P.x * Q.z);
		result.z = (P.x* Q.y) - (P.y * Q.x);
		return result;
	}
	
	public static boolean nodeZero (node P){
		
			return (P.x == 0 && P.y ==0 && P.z ==0 );
		
	}
	
	public void product( double[][] matrix) {
		double newX, newY, newZ, newNormal;
		
		newX = x * matrix[0][0] + y * matrix[0][1] + z * matrix[0][2] + matrix[0][3];
		newY = x * matrix[1][0] + y * matrix[1][1] + z * matrix[1][2] + matrix[1][3];
		newZ = x * matrix[2][0] + y * matrix[2][1] + z * matrix[2][2] + matrix[2][3];
		newNormal = x * matrix[3][0] + y * matrix[3][1] + z * matrix[3][2] + matrix[3][3];
		this.x = newX / newNormal;
		this.y = newY / newNormal;
		this.z = newZ / newNormal;
		
	}
	
	
}
