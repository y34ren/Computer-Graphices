package Main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import structure.bezier_curve;
import structure.node;
import structure.surface;
import Hill.Hill;
import Train.Cube;
import Train.Train;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;


public class Main extends GLJPanel implements GLEventListener, KeyListener {

	private GL2 gl;
	public boolean view;
	public boolean light;
	public Train train;
	public Cube ground;
	public Cube ground2;
	public Hill hill;

	private float rotateX, rotateY, rotateZ;
	
	public static JLabel lb;
	public static JLabel lb2;





	public Main(GLCapabilities capabilities) {

		super(capabilities);
		setPreferredSize( new Dimension(600,600) );
		addGLEventListener(this);
		addKeyListener(this);

		// Set starting values

		view = true;
		light = true;


	}


	@Override
	public void display(GLAutoDrawable drawable) {

		final GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(1,1,1,1);

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		//view control
		if (view){
			gl.glOrtho(-3,3,-3,3,-6,6);
		}
		else {
			double near = 3/ Math.tan(0.785398); //45 degree / 2
			double far = near + 6;
			gl.glFrustum(-3, 3, -3, 3, near, far);
			gl.glTranslated(0,0,-(near + 6));


		}

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();

		gl.glEnable(GL2.GL_DEPTH_TEST);

		

		if(light){
			gl.glEnable(GL.GL_DEPTH_TEST);
			gl.glEnable(GL2.GL_LIGHT0);
			gl.glEnable(GL2.GL_COLOR_MATERIAL);
			
			gl.glDisable(GL2.GL_LIGHTING);

		}
		else{
			
			gl.glEnable(GL.GL_DEPTH_TEST);
			gl.glEnable(GL2.GL_LIGHTING);
			gl.glEnable(GL2.GL_COLOR_MATERIAL);
			
			gl.glDisable(GL2.GL_LIGHT0);

		}
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();


		gl.glRotatef(rotateZ,0,0,1);
		gl.glRotatef(rotateY,0,1,0);
		gl.glRotatef(rotateX,1,0,0);


		train = new Train();


		train.transform.shift(0, 0, 0);

		ArrayList<surface> temp = train.getface();

		for (int i = 0; i < temp.size(); i ++){
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			temp.get(i).draw(gl);
			gl.glEnd();
		}

		ground = new Cube();
		ground.transform.rotation(0, -90, 0);
		ground.transform.shift(0, 0, -2.5);
		ground.transform.scale(150, 1, 150);
		ground.setColor(0.5f, 1, 0.5f);

		temp = ground.getface();

		for (int i = 0; i < temp.size(); i ++){
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			temp.get(i).draw(gl);
			gl.glEnd();
		}

		ground2 = new Cube();
		ground2.transform.rotation(0, -90, 0);
		ground2.transform.shift(0, 0, -5f);
		ground2.transform.scale(150, 5, 150);
		ground2.setColor(0.3f, 0.2f, 0f);

		temp = ground2.getface();

		for (int i = 0; i < temp.size(); i ++){
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			temp.get(i).draw(gl);
			gl.glEnd();
		}
		
		hill = new Hill(30);
		
		
		
		hill.transform.scale(40, 40, 40);
		hill.setColor(0.4f, .03f, 0);
		
		hill.transform.shift(55, 0, -5);
		
		temp = hill.getface();

		for (int i = 0; i < temp.size(); i ++){
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			temp.get(i).draw(gl);
			gl.glEnd();
		}
		

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.8F, 0.8F, 0.8F, 1.0F);

		

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub

	}


	
	

	public static void main(String[] args) {

		JFrame window = new JFrame("Train Set");
		GLCapabilities caps = new GLCapabilities(null);

		JPanel mp = new JPanel();
		mp.setLayout(new BoxLayout(mp, BoxLayout.Y_AXIS));

		Main panel = new Main(caps);


		JPanel p2 = new JPanel();
		p2.setBorder(new TitledBorder("Options"));
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));


		lb = new JLabel("Select Light Option  - Press Q for Day, Press W for Night");
		
		p2.add(lb);

		lb2 = new JLabel("Select Veiw Option  - Press up, down, left, right arrow key to change view view");

		p2.add(lb2);
		window.setContentPane(mp);
		mp.add(panel);
		mp.add(p2);
		window.pack();
		window.setLocation(50,50);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		panel.requestFocusInWindow();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if ( key == KeyEvent.VK_LEFT )
			rotateY -= 15;
		else if ( key == KeyEvent.VK_RIGHT )
			rotateY += 15;
		else if ( key == KeyEvent.VK_DOWN)
			rotateX += 15;
		else if ( key == KeyEvent.VK_UP )
			rotateX -= 15;
		else if ( key == KeyEvent.VK_PAGE_UP )
			rotateZ += 15;
		else if ( key == KeyEvent.VK_PAGE_DOWN )
			rotateZ -= 15;
		else if ( key == KeyEvent.VK_Q)
			light = true;
		else if ( key == KeyEvent.VK_W)
			light = false;

		
		repaint();
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	

}
