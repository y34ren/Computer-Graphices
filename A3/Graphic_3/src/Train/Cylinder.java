package Train;

import java.util.ArrayList;

import structure.node;
import structure.surface;
import structure.transformation;

public class Cylinder {

	public transformation transform;
	public ArrayList<node> node_list;
	public ArrayList<surface> surface_list;

	public int segament;

	public Cylinder (int seg){
		transform = new transformation();
		node_list = new ArrayList<node> ();
		surface_list = new ArrayList<surface> ();

		setSegment ( seg);

	}

	public  void setSegment (int seg){
		if (seg < 3){
			segament = 3;
		}
		else segament = seg;

		refreshNodes();
		refreshSurface();
	}

	public void refreshNodes(){
		double PI2 = 2 * 3.14;
		double steps = PI2 / segament;

		node_list.clear();


		for (double i = 0; i < PI2; i += steps){
			double x = 0.5 * Math.cos(i);
			double y = 0.5 * Math.sin(i);

			node_list.add(new node (x,y,0.5));
			node_list.add(new node (x,y,-0.5));

		}	

	}

	public void refreshSurface(){

		surface_list.clear();
		
		int size = node_list.size();
		
		node[] Array1 = new node[size/2];
		node[] Array2 = new node[size/2];
		
		
		for (int i = 0; i < size -1; i +=2){
			Array1[i/2] = node_list.get(i);
			Array2[i/2] = node_list.get(i+1);
			
		}
		surface_list.add(new surface (Array1) );
		surface_list.add(new surface (Array2) );
		
		for (int i = 0; i < size-3; i +=2){
			surface_list.add(new surface (node_list.get(i),node_list.get(i+1), node_list.get(i+3), node_list.get(i+2)) );
	
		}
		
		surface_list.add(new surface (node_list.get(size-2),node_list.get(size -1), node_list.get(1), node_list.get(0)) );
		
		
	}
	
	public void transNode(){
		double [][]matrix = transform.getMatrix();
		for (int i = 0; i < node_list.size(); i++){
			node_list.get(i).product(matrix);
		}
	}
	public void transNode(double[][] matrix){
		for (int i = 0; i < node_list.size(); i++){
			node_list.get(i).product(matrix);
		}
		
	}
	
	public ArrayList<surface> getface(){
		transNode();
		
		return surface_list;
	}
	
	public void setColor(float R, float G, float B){
		for (int i = 0; i < surface_list.size(); i++){
			 surface_list.get(i).R = R;
			 surface_list.get(i).G = G;
			 surface_list.get(i).B = B;
			 
		}
	}

}
