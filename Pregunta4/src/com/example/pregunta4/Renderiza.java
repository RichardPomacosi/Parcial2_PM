package com.example.pregunta4;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;
public class Renderiza extends GLSurfaceView implements Renderer {
	/* Objeto */
	private Cubo cubo;
	private CuboMalla cubomalla;
	private Piramide piramide;
	private Piso piso;
	private Tronco tronco;
	private Esfera esfera;
	private Triangulo triangulo;
	/* Para la rotación */
	private float trazoHorizontal;
	private float trazoVertical;
	private float antX;
	private float antY;
	public Renderiza(Context contexto) {
	super(contexto);
	/* Se inicia el renderizado */
	this.setRenderer(this);
	/* La ventana solicita recibir una entrada */
	this.requestFocus();
	/* Se establece que la ventana detecte el modo táctil */
	this.setFocusableInTouchMode(true);
	/* Se renderizará al inicio o cuando se llame a requestRender() */
	this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
	cubo = new Cubo();
	piramide=new Piramide();
	piso=new Piso();
	tronco=new Tronco();
	esfera=new Esfera(0.2f, 8, 8);
	triangulo=new Triangulo();
	/* Se habilita el modo de sombreado plano */
	gl.glShadeModel(GL10.GL_FLAT);
	/* Se habilita el ocultamiento de superficies */
	gl.glEnable(GL10.GL_DEPTH_TEST);
	/* Color de fondo */
	gl.glClearColor(0, 0, 0, 0);
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		/* Se inicializa el buffer de color y de profundidad */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		/* Se inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();

		
		/* Rota el cubo */		
		gl.glRotatef(trazoHorizontal, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(trazoVertical, 1.0f, 0.0f, 0.0f);
		gl.glPushMatrix();
		piramide.dibuja(gl); // P' = Ry Rx P
	
		gl.glPopMatrix();
		gl.glPushMatrix();
		
		gl.glTranslatef(0, -1, 0);
		gl.glScalef(2, 1, 2);
		piso.setColor((byte)211, (byte)179, (byte)79);
		piso.dibuja(gl);
		
		//Tronco
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0, -1, 0);
		gl.glScalef(0.1f, 1, 0.1f);
		tronco.dibuja(gl);
		
		//Segunda capa arbol
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0, 0.2f, 0);
		gl.glScalef(1f, 0.6f, 1f);
		piramide.dibuja(gl);
		
		//Tercera Capa arbol
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0, 0.7f, 0);
		gl.glScalef(0.7f, 0.4f, 0.7f);
		piramide.dibuja(gl);
		
		//Cuarta Capa arbol
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0, 1.2f, 0);
		gl.glScalef(0.4f, 0.3f, 0.4f);
		piramide.dibuja(gl);
		
		//Adornos cara 1
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glScalef(0.4f, 0.4f, 0.4f);
		gl.glTranslatef(0, 1f, 1.8f);
		gl.glColor4f( 0.8f, 0f, 0f, 1 );
		esfera.dibuja(gl);
		gl.glTranslatef(0.5f, -2.2f, 0.2f);
		esfera.dibuja(gl);
		
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glScalef(0.4f, 0.4f, 0.4f);
		gl.glTranslatef(-0.5f, 0.2f, 1.5f);
		gl.glColor4f( 0.8f, 0.8f, 0f, 1 );
		esfera.dibuja(gl);
		gl.glTranslatef(0.5f, 2f, -0.5f);
		gl.glColor4f( 0.1f, 0.8f, 0f, 1 );
		esfera.dibuja(gl);
		
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glScalef(0.4f, 0.4f, 0.4f);
		gl.glTranslatef(0.5f, 0.1f, 1.5f);
		gl.glColor4f( 0, 0, 0.8f, 1 );
		esfera.dibuja(gl);
		
		
		//Cara 2
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glRotatef(90, 0, 1, 0);
		gl.glScalef(0.4f, 0.4f, 0.4f);
		gl.glTranslatef(0, 1f, 1.8f);
		gl.glColor4f( 0.8f, 0f, 0f, 1 );
		esfera.dibuja(gl);
		gl.glTranslatef(0.5f, -2.2f, 0.2f);
		esfera.dibuja(gl);
		
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glRotatef(90, 0, 1, 0);
		gl.glScalef(0.4f, 0.4f, 0.4f);
		gl.glTranslatef(-0.5f, 0.2f, 1.5f);
		gl.glColor4f( 0.8f, 0.8f, 0f, 1 );
		esfera.dibuja(gl);
		gl.glTranslatef(0.5f, 2f, -0.5f);
		gl.glColor4f( 0.1f, 0.8f, 0f, 1 );
		esfera.dibuja(gl);
		
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glRotatef(90, 0, 1, 0);
		gl.glScalef(0.4f, 0.4f, 0.4f);
		gl.glTranslatef(0.5f, 0.1f, 1.5f);
		gl.glColor4f( 0, 0.3f, 0.8f, 1 );
		esfera.dibuja(gl);
		
		
		//Cara 3
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glRotatef(180, 0, 1, 0);
				gl.glScalef(0.4f, 0.4f, 0.4f);
				gl.glTranslatef(0, 1f, 1.8f);
				gl.glColor4f( 0.8f, 0.4f, 0f, 1 );
				esfera.dibuja(gl);
				gl.glTranslatef(0.5f, -2.2f, 0.2f);
				esfera.dibuja(gl);
				
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glRotatef(180, 0, 1, 0);
				gl.glScalef(0.4f, 0.4f, 0.4f);
				gl.glTranslatef(-0.5f, 0.2f, 1.5f);
				gl.glColor4f( 0.8f, 0.8f, 0f, 1 );
				esfera.dibuja(gl);
				gl.glTranslatef(0.5f, 2f, -0.5f);
				gl.glColor4f( 0.1f, 0.8f, 0f, 1 );
				esfera.dibuja(gl);
				
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glRotatef(180, 0, 1, 0);
				gl.glScalef(0.4f, 0.4f, 0.4f);
				gl.glTranslatef(0.5f, 0.1f, 1.5f);
				gl.glColor4f( 0.8f, 0.3f, 0.8f, 1 );
				esfera.dibuja(gl);
		//Cara 4
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glRotatef(-90, 0, 1, 0);
				gl.glScalef(0.4f, 0.4f, 0.4f);
				gl.glTranslatef(0, 1f, 1.8f);
				gl.glColor4f( 0.8f, 0f, 0f, 1 );
				esfera.dibuja(gl);
				gl.glTranslatef(0.5f, -2.2f, 0.2f);
				esfera.dibuja(gl);
				
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glRotatef(-90, 0, 1, 0);
				gl.glScalef(0.4f, 0.4f, 0.4f);
				gl.glTranslatef(-0.5f, 0.2f, 1.5f);
				gl.glColor4f( 0.8f, 0.3f, 0.7f, 1 );
				esfera.dibuja(gl);
				gl.glTranslatef(0.5f, 2f, -0.5f);
				gl.glColor4f( 0.1f, 0.2f, 0.6f, 1 );
				esfera.dibuja(gl);
				
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glRotatef(-90, 0, 1, 0);
				gl.glScalef(0.4f, 0.4f, 0.4f);
				gl.glTranslatef(0.5f, 0.1f, 1.5f);
				gl.glColor4f( 0.4f, 0.3f, 0.8f, 1 );
				esfera.dibuja(gl);
		//Estrella
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glRotatef(180, 1, 0, 0);
				gl.glTranslatef(0, -1.5f, 0);
				gl.glScalef(0.1f, 0.1f, 0.05f);
				triangulo.dibuja(gl);
		//Estrella
				gl.glPopMatrix();
				gl.glPushMatrix();
				gl.glTranslatef(0, 1.7f, 0);
				gl.glScalef(0.1f, 0.1f, 0.05f);
				triangulo.dibuja(gl);
				
		gl.glPopMatrix();
		/* Se asegura que se ejecute las anteriores instrucciones */
		gl.glFlush();
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
	/* Ventana de despliegue */
	gl.glViewport(0, 0, w, h);
	/* Matriz de Proyección */
	gl.glMatrixMode(GL10.GL_PROJECTION);
	/* Se inicializa la Matriz de Proyección */
	gl.glLoadIdentity();
	/* Proyección paralela */
	if (w <= h)
	gl.glOrthof(-2, 2, -2 * (float) h / (float) w, 2 * (float) h
	/ (float) w, -10, 10);
	else
	gl.glOrthof(-2 * (float) w / (float) h, 2 * (float) w / (float) h,
	-2, 2, -10, 10);
	/* Matriz del Modelo-Vista */
	gl.glMatrixMode(GL10.GL_MODELVIEW);
	/* Se inicializa la Matriz del Modelo-Vista */
	gl.glLoadIdentity();
	}
	/**
	* Maneja los eventos del movimiento en la pantalla táctil.
	*/
	@Override
	public boolean onTouchEvent(MotionEvent e) {
	float x = e.getX();
	float y = e.getY();
	switch (e.getAction()) {
	case MotionEvent.ACTION_MOVE:
	float dx = x - antX;
	float dy = y - antY;
	trazoHorizontal = trazoHorizontal + dx * 0.5625f; // 180 / 320 = 0.5625
	trazoVertical = trazoVertical + dy * 0.5625f; // 270 / 480 = 0.5625
	requestRender();
	}
	antX = x;
	antY = y;
	return true;
	}
}