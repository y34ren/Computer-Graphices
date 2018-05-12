package structure;


public class bezier_curve {

	public node[] nodeArray;

	public bezier_curve (node... nodes ){
		nodeArray = nodes;
	}


	//bezier curve 's coefficient
	public double[] coefficient() {

		int n = nodeArray.length;

		
		
		double[] result = new double[n];

				for (int j = 0; j <= n-1; j++) {

				
					result[j] = pascalValue(n-1, j);

		}
		return result;
	}

	public static double pascalValue(int i, int j) {
		if (j == 0) {
			return 1;
		} else if (j == i) {
			return 1;
		} else {
			return pascalValue(i - 1, j - 1) + pascalValue(i - 1, j);
		}
	}

	public double[] coefficient_two(double t){

		int n = nodeArray.length;
		double[] result = new double[n];
		double partA = (1 - t);

		for (int i= 0; i < n; i++){

			result[i] = (Math.pow(partA, (n-1-i))) * ( Math.pow(t, i));
			
			
		}

		
		return result;
		



	}


	public node[] bezierNode (int t){

		final double coef  = 1d/t;

		node[] result = new node[t+1];

		int n = nodeArray.length;
		
		
		double [] coArray = coefficient();

		for (int i = 0; i <= t; i++){
			double value =  i * coef;

			double [] coTwoArray = coefficient_two(value);
			
			
			
			
			
			node temp = new node();

			for (int j = 0; j < n; j++){

				temp = node.Addition(node.multiply((coArray[j] * coTwoArray[j]), nodeArray[j]), temp);
				
				
			}
			
			
			
			result[i] = temp;

			
			


		}

		return result;
	}


	


}
