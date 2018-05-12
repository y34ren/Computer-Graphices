package Train;

import java.util.ArrayList;

import structure.node;
import structure.surface;
import structure.transformation;

public class Cube {

	public transformation transform;
	public ArrayList<node> node_list;
	public ArrayList<surface> surface_list;
	
	public Cube(){
		transform = new transformation();
		node_list = new ArrayList<node> ();
		surface_list = new ArrayList<surface> ();
		
		node_list.add(new node(0.5f, 0.5f, 0.5f)); 	// 0 /front / right / up
		node_list.add(new node(-0.5f, 0.5f, 0.5f)); // 1/back / right /up
		node_list.add(new node(-0.5f, -0.5f, 0.5f)); //2/back / left/ up
		node_list.add(new node(-0.5f, -0.5f, -0.5f)); //3/back/ left/down
		node_list.add(new node(-0.5f, 0.5f, -0.5f)); //4/back/right/down
		node_list.add(new node(0.5f, -0.5f, 0.5f)); //5/front/ left/ up
		node_list.add(new node(0.5f, -0.5f, -0.5f)); //6/ front / left/ down
		node_list.add(new node(0.5f, 0.5f, -0.5f)); //7/front/right/down
		
		surface_list.add(new surface(node_list.get(0), node_list.get(7),node_list.get(6),node_list.get(5)) );
		surface_list.add(new surface(node_list.get(1), node_list.get(2),node_list.get(3),node_list.get(4)) );
		surface_list.add(new surface(node_list.get(0), node_list.get(5),node_list.get(2),node_list.get(1)) );
		surface_list.add(new surface(node_list.get(7), node_list.get(4),node_list.get(3),node_list.get(6)) );
		surface_list.add(new surface(node_list.get(0), node_list.get(1),node_list.get(4),node_list.get(7)) );
		surface_list.add(new surface(node_list.get(5), node_list.get(6),node_list.get(3),node_list.get(2)) );
		
		
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
