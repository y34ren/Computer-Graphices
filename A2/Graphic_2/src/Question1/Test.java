package Question1;
//Graph Assignment 2  Test
//Pandering a 3D Cube, with function to Scale, move, rotate and camera control functions


import com.jogamp.opengl.*;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import com.jogamp.opengl.awt.GLJPanel;

public class Test extends GLJPanel implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	//Frame & Window Layouts
	public static void main(String[] args) {
		JFrame window = new JFrame("3D Cube");
		GLCapabilities caps = new GLCapabilities(null);
		Test panel = new Test(caps);
		
		window.setContentPane(panel);
		window.pack();
		window.setLocation(50,50);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		panel.requestFocusInWindow();
	}

	//Valuables 
	private float rotateX, rotateY, rotateZ;   // rotation amounts about axes, controlled by keyboard
	private float ScaleValue; // Scale Value
	
	
	private boolean dragging;  // is a drag operation in progress?
	
	private int startX, startY;  // starting location of mouse during drag
	private int prevX, prevY;    // previous location of mouse during drag
	private int notches;    // # of mouse scroll rolled;
	
	private float TransX, TransY, TransZ;   //
	
	private float CrotateX, CrotateY, CrotateZ;   // camera rotation amounts about axes, controlled by keyboard
	private float CpositionX, CpositionY, CpositionZ;   //Camera Position
	
	private boolean view; // Perspective view = false  else parallel 
	
	private GL2 gl;
	

	//Start Test();
	public Test(GLCapabilities capabilities) {
		super(capabilities);
		setPreferredSize( new Dimension(500,500) );
		addGLEventListener(this);
		addKeyListener(this);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		
		addMouseWheelListener(this);
		
		// Set strating values
		
		CrotateX = 0;
		CrotateY = 0;
		CrotateZ = 0; 
		
		CpositionX = 0;
		CpositionY = 0;
		CpositionZ = -1;
		
		rotateX = 30;
		rotateY = 30;
		rotateZ = 0;
		ScaleValue = 1;
		
		view = true;  
	}
	
	// ------------ methods of the KeyListener interface ------------

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
		else if ( key == KeyEvent.VK_HOME ){
			rotateX = rotateY = 30;
					rotateZ = 0;
			CrotateX = CrotateY = CrotateZ = 0; 
			ScaleValue = 1;
			
			CpositionX = 0;
			CpositionY = 0;
			CpositionZ = -1;
		}
		else if ( key == KeyEvent.VK_N )
			TransZ += 0.1;
		else if ( key == KeyEvent.VK_M )
			TransZ -= 0.1;
		else if ( key == KeyEvent.VK_W )
			CpositionY += 0.01;
		else if ( key == KeyEvent.VK_S )
			CpositionY -= 0.01;
		else if ( key == KeyEvent.VK_D )
			CpositionX += 0.01;
		else if ( key == KeyEvent.VK_A )
			CpositionX -= 0.01;
		else if ( key == KeyEvent.VK_Z )
			CpositionZ += 0.01;
		else if ( key == KeyEvent.VK_X )
			CpositionZ -= 0.01;
		else if ( key == KeyEvent.VK_J )
			CrotateY -= 15;
		else if ( key == KeyEvent.VK_L )
			CrotateY += 15;
		else if ( key == KeyEvent.VK_K)
			CrotateX -= 15;
		else if ( key == KeyEvent.VK_I )
			CrotateX += 15;
		else if ( key == KeyEvent.VK_U )
			CrotateZ += 15;
		else if ( key == KeyEvent.VK_O )
			CrotateZ -= 15;
		else if ( key == KeyEvent.VK_V )
			view = true;
		else if ( key == KeyEvent.VK_B )
			view = false;
		
		
		repaint();
	}

	public void keyReleased(KeyEvent e) { 
	}

	public void keyTyped(KeyEvent e) { 
	}
	
	




	
	// ----------------- define some drawing methods for this program ----------

	private void square(GL2 gl, float r, float g, float b) {
		
		
		
		gl.glColor3f(r,g,b);         // The color for the square.
		gl.glTranslatef(0,0,0.5f);    // Move square 0.5 units forward.
		gl.glNormal3f(0,0,1);        // Normal vector to square (this is actually the default).
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		
		gl.glVertex2f(-0.5f,-0.5f);    // Draw the square (before the
		gl.glVertex2f(0.5f,-0.5f);     //   the translation is applied)
		gl.glVertex2f(0.5f,0.5f);      //   on the xy-plane, with its
		gl.glVertex2f(-0.5f,0.5f);     //   at (0,0,0).
		
		
		
		
		gl.glEnd();
	}

	private void cube(GL2 gl) {

		gl.glPushMatrix();
		square(gl,1,0,0);        // front face is red
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glRotatef(180,0,1,0); // rotate square to back face
		square(gl,0,1,1);        // back face is cyan
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glRotatef(-90,0,1,0); // rotate square to left face
		square(gl,0,1,0);        // left face is green
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glRotatef(90,0,1,0); // rotate square to right face
		square(gl,1,0,1);       // right face is magenta
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glRotatef(-90,1,0,0); // rotate square to top face
		square(gl,0,0,1);        // top face is blue
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glRotatef(90,1,0,0); // rotate square to bottom face
		square(gl,1,1,0);        // bottom face is yellow
		gl.glPopMatrix();

	}
	
	// ---------------  Methods of the GLEventListener interface -----------

	public void display(GLAutoDrawable drawable) {	
		   // called when the panel needs to be drawn
		
		gl = drawable.getGL().getGL2();
		gl.glClearColor(0,0,0,0);
		gl.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );

		gl.glMatrixMode(GL2.GL_PROJECTION);  // Set up the projection.
		gl.glLoadIdentity();
		
		//view control
		if (view){
			gl.glOrtho(-3,3,-3,3,-6,6);
		}
		else {
			double near = 3/ Math.tan(0.785398); //45 degree
			double far = near + 6;
			gl.glFrustum(-3, 3, -3, 3, near, far);
			gl.glTranslated(0,0,-(near + 6));
		}
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();             // Set up modelview transform. 
	
		//camera rotation
		gl.glRotatef(-CrotateZ,0,0,1);
		gl.glRotatef(-CrotateY,0,1,0);
		gl.glRotatef(-CrotateX,1,0,0);
		
		//Cube Translate
		gl.glTranslatef(TransX - CpositionX, TransY - CpositionY, TransZ - CpositionZ);
		
	
		//Cube Rotation
		gl.glRotatef(rotateZ,0,0,1);
		gl.glRotatef(rotateY,0,1,0);
		gl.glRotatef(rotateX,1,0,0);
		
		//Cube scale
		gl.glScalef(ScaleValue, ScaleValue, 2);
		gl.glScalef(ScaleValue, ScaleValue, ScaleValue);
		
		
	
		

		cube(gl);
		
	

	}

	public void init(GLAutoDrawable drawable) {
		   // called when the panel is created
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.8F, 0.8F, 0.8F, 1.0F);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
	}

	public void dispose(GLAutoDrawable drawable) {
		// called when the panel is being disposed
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// called when user resizes the window
	}

	
	/* -------------------*/
	
	
	/* ------------------*/
	

	/**
	 * Called when the user presses a mouse button on the display.
	 */
	public void mousePressed(MouseEvent evt) {
		if (dragging) {
			return;  // don't start a new drag while one is already in progress
		}
		int x = evt.getX();
		int y = evt.getY();
		
	 
		// TODO: respond to mouse click at (x,y)
		dragging = true;  // might not always be correct!
		startX = x;
		startY = y;
		
		
		prevX = (int) (TransX*200);
		prevY = (int) (TransY*200);
		
	}

	/**
	 * Called when the user releases a mouse button after pressing it on the display.
	 */
	public void mouseReleased(MouseEvent evt) {
		if (! dragging) {
			return;
		}
		dragging = false;
		
		
		
		// TODO:  finish drag (generally nothing to do here)
	}

	/**
	 * Called during a drag operation when the user drags the mouse on the display/
	 */
	public void mouseDragged(MouseEvent evt) {
		if (! dragging) {
			return;
		}
		int x = evt.getX();
		int y = evt.getY();
	
		//Moving the Object with difference in mouse positions
		
		TransX = 0.005f *(float)(prevX + x-startX);
		TransY = 0.005f *(float)(prevY + startY-y);
		
		//TransZ is controlled by key 
		
		
		repaint();
	}

	public void mouseMoved(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	
	
	//-------------------------------------------Mouse Wheel Action To change Cube Scale
	public void mouseWheelMoved(MouseWheelEvent evt) {
		// TODO Auto-generated method stub
		notches += evt.getWheelRotation();
		ScaleValue = 1 + 0.01f * notches;
		repaint();
		
	}

	

}
//End