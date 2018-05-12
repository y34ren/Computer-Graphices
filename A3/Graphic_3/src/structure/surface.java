package structure;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

public class surface {
	
	public ArrayList<node> node_list;
	
	public node normal; 
	
	public float R,G,B;
	
	public surface(node... nodes){
		node_list = new ArrayList<node>();
		
		for (int i = 0; i< nodes.length; i++){
			node_list.add(nodes[i]);
		}
		
	//	int i = 0;
	//	do{
		
		//	normal = node.crossPro ( node.subtraction (node_list.get(i) , node_list.get(i+1)) ,
			//		node.subtraction (node_list.get(i+2) , node_list.get(i+1)));
	//		i++;
	//	} while (node.nodeZero (normal));
	
	}
	
	
	public void NeativeNormal (){
		
		normal = node.multiply(-1d, normal); 
	}
	
	public void draw(GL2 gl){
		gl.glColor3f(R, G, B);
		
		for (int i= 0; i < node_list.size(); i ++){
			gl.glVertex3d(node_list.get(i).x / 25d, node_list.get(i).y / 25d, node_list.get(i).z / 25d);
		}
	}
	
	

}
