package Train;

import java.util.ArrayList;

import structure.node;
import structure.surface;
import structure.transformation;

public class Train {

	public transformation transform;
	public ArrayList<node> node_list;
	public ArrayList<surface> surface_list;

	public int segament;

	public Cube Body;
	public Cylinder Head;
	public Cylinder Chimney;
	public Cylinder Wheel1;
	public Cylinder Wheel2;
	public Cylinder Wheel3;
	public Cylinder Wheel4;





	public Train(){
		transform = new transformation();
		node_list = new ArrayList<node> ();
		surface_list = new ArrayList<surface> ();
		segament = 50;

		//Body

		Body = new Cube();
		Body.transform.shift(-3, 0 ,5);
		Body.transform.scale(8, 8,10);
		Body.setColor(0.4f, 0.3f, 0f);

		Body.transNode();

		//Head

		Head = new Cylinder(segament);
		Head.transform.shift(5, 0,3);
		Head.transform.rotation(0, 0,90);
		Head.transform.scale(6, 6, 10);
		Head.setColor(0f, 0f, 0f);

		Head.transNode();


		//chimney

		Chimney = new Cylinder(segament);

		Chimney.transform.shift(6, 0,7);

		Chimney.transform.scale(2, 2, 4);
		Chimney.setColor(0.8f, 0.8f, 0f);

		Chimney.transNode();



		//Wheel1

		Wheel1 = new Cylinder(segament);

		Wheel1.transform.shift(-4, -3.5,0);
		Wheel1.transform.rotation(0, 90, 90);
		Wheel1.transform.scale(4, 4, 1);
		Wheel1.setColor(1f, 0f, 0f);

		Wheel1.transNode();

		//Wheel2
		Wheel2 = new Cylinder(segament);
		Wheel2.transform.shift(6, -3.5,0);

		Wheel2.transform.rotation(0, 90,90);
		
		Wheel2.transform.rotation(0, 90,90);
		Wheel2.transform.scale(4, 4, 1);
		Wheel2.setColor(1f, 0f, 0f);

		Wheel2.transNode();

		//Wheel3
		Wheel3 = new Cylinder(segament);

		Wheel3.transform.shift(-4, 3.5,0);
		Wheel3.transform.rotation(0, 90,90);
		Wheel3.transform.scale(4, 4, 1);
		Wheel3.setColor(1f, 0f, 0f);

		Wheel3.transNode();

		//Wheel4

		Wheel4 = new Cylinder(segament);

		Wheel4.transform.shift(6, 3.5,0);
		Wheel4.transform.rotation(0, 90,90);
		Wheel4.transform.scale(4, 4, 1);
		Wheel4.setColor(1f, 0f, 0f);

		Wheel4.transNode();
		
		refreshSurface();
		
		


	}
	
	public void transNode(){
		
		double [][]matrix = transform.getMatrix();
		
		
		
		Body.transNode(matrix);
		Head.transNode(matrix);
		Chimney.transNode(matrix);
		Wheel1.transNode(matrix);
		Wheel2.transNode(matrix);
		Wheel3.transNode(matrix);
		Wheel4.transNode(matrix);
		
		
		
	}
	
	public void transNode(double[][] matrix){
		
		Body.transNode(matrix);
		Head.transNode(matrix);
		Chimney.transNode(matrix);
		Wheel1.transNode(matrix);
		Wheel2.transNode(matrix);
		Wheel3.transNode(matrix);
		Wheel4.transNode(matrix);
		
	}
	
	public ArrayList<surface> getface(){
		transNode();
		
		return surface_list;
	}
	
	public void refreshSurface(){

		surface_list.clear();
		
		for (int i = 0; i < Body.surface_list.size(); i++){
			surface_list.add(Body.surface_list.get(i));
			
			
		}
		
		for (int i = 0; i < Head.surface_list.size(); i++){
			surface_list.add(Head.surface_list.get(i));
		}
		
		for (int i = 0; i < Chimney.surface_list.size(); i++){
			surface_list.add(Chimney.surface_list.get(i));
		}
		
		for (int i = 0; i < Wheel1.surface_list.size(); i++){
			surface_list.add(Wheel1.surface_list.get(i));
		}
		
		for (int i = 0; i < Wheel2.surface_list.size(); i++){
			surface_list.add(Wheel2.surface_list.get(i));
		}
		
		for (int i = 0; i < Wheel3.surface_list.size(); i++){
			surface_list.add(Wheel3.surface_list.get(i));
		}
		
		for (int i = 0; i < Wheel4.surface_list.size(); i++){
			surface_list.add(Wheel4.surface_list.get(i));
		}
		
	}

}
