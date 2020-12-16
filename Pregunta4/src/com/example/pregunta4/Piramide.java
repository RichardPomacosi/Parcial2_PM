package com.example.pregunta4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;
public class Piramide {
		/**
		*   3 --------- 2
		*   /|          /|
		*  / |   *     / |
		* 7 --------- 6  |
		* |  |        |  |
		* |  0 -------|-- 1
		* | /         | /
		* |/          |/
		* 3 --------- 2
		*/ //4     5
		/* Las coordenadas cartesianas (x, y, z) */
	private float vertices[] = new float[] {
		// Abajo
		-1, -1, -1, // 0 0
		 1, -1, -1, // 1 1
		 1, -1,  1, // 2 2
		-1, -1,  1, // 3 3		
				
		// Frente
		-1, -1,  1, // 3 4
		 1, -1,  1, // 2 5
		 0,  1, 0, //  4 6
		 
		// Atrás
		-1, -1, -1, //0 7
		 0,  1, 0, // 4 8
		 1, -1, -1, //1 9
		
		
		// Izquierda
		 -1, -1, -1, // 0 10
		 -1, -1,  1, // 3 11
		 0,  1, 0,   // 4 12

		// Derecha
		 1, -1,  1, // 2 13
		 1, -1, -1, // 1 14
		 0,  1, 0, // 4 15
		
		};
		
		/* Los colores x c/vértice (r,g,b,a) */
		byte maxColor = (byte)255;
		private byte colores[] = new byte[] {
			// Abajo - azul
			0, maxColor,0, maxColor, // 0 0
			0, maxColor,0, maxColor, // 1 1
			0, maxColor,0, maxColor, // 2 2
			0, maxColor,0, maxColor, // 3 3
			
			// Frente - lila
			(byte)150, (byte)206, (byte)31, maxColor, // 3 4
			(byte)150, (byte)206, (byte)31, maxColor, // 2 5
			(byte)150, (byte)206, (byte)31, maxColor, // 4 6
		
			// Atrás - amarillo
			(byte)112, (byte)214, (byte)61, maxColor, // 0 7
			(byte)112, (byte)214, (byte)61,maxColor, // 4 8
			(byte)112, (byte)214, (byte)61, maxColor, // 1 9
			
			// Izquierda - celeste
			(byte)229, (byte)250, (byte)81, maxColor, // 0 10
			(byte)229, (byte)250, (byte)81, maxColor, // 3 11
			(byte)229, (byte)250, (byte)81, maxColor, // 4 12
			
			// Derecha - rojo
			(byte)24, (byte)142, (byte)10, maxColor, // 2 13
			(byte)24, (byte)142, (byte)10, maxColor, // 1 14
			(byte)24, (byte)142, (byte)10, maxColor, // 4 15
			
		};
		/* Indices */
		private short indices[] = new short [] {
			0, 1, 2, 0, 2, 3, //abajo
			4, 5, 6, //frente
			7,8,9,//atras
			10,11, 12, // izquierda
			13,14,15, //derecha
		};
		
		private FloatBuffer bufVertices;
		private ByteBuffer bufColores;
		private ShortBuffer bufIndices;
		public Piramide() {
		/* Lee los vértices */
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufVertices = bufByte.asFloatBuffer(); // Convierte de byte a float
		bufVertices.put(vertices);
		bufVertices.rewind(); // puntero al principio del buffer
		
		/* Lee los colores */
		bufColores = ByteBuffer.allocateDirect(colores.length);
		bufColores.put(colores);
		bufColores.position(0); // puntero al principio del buffer
		/* Lee los indices */
		bufByte = ByteBuffer.allocateDirect(indices.length * 2);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufIndices = bufByte.asShortBuffer(); // Convierte de byte a short
		bufIndices.put(indices);
		bufIndices.rewind(); // puntero al principio del buffer
		}
		public void dibuja(GL10 gl) {
			/* Se habilita el acceso al arreglo de vértices */
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			/* Se habilita el acceso al arreglo de colores */
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			/* Se especifica los datos del arreglo de vértices */
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufVertices);
			/* Se especifica los datos del arreglo de colores */
			gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, bufColores);
			
			/* Se dibuja el cubo */
			gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
			GL10.GL_UNSIGNED_SHORT, bufIndices);
			
			/* Se deshabilita el acceso a los arreglos */
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		}
}
