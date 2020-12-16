package com.example.pregunta4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;
public class Piso {
	private byte r =(byte)222;
	private byte g =(byte)259;
	private byte b =(byte)24;
		/**       5
		*    -----*---- -
		*   /|          /|
		*  / |   4     / |
		* --------*----  |
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
		
		};
		
		/* Los colores x c/vértice (r,g,b,a) */
		byte maxColor = (byte)255;
	private byte colores[] = new byte[] {
			// Abajo - azul
			r, g, b, maxColor, // 0 0
			r, g, b, maxColor, // 1 1
			r, g, b, maxColor, // 2 2
			r, g, b, maxColor, // 3 3
		};
		/* Indices */
		private short indices[] = new short [] {
			0, 1, 2, 0, 2, 3, //abajo
		};
		
		private FloatBuffer bufVertices;
		private ByteBuffer bufColores;
		private ShortBuffer bufIndices;
		
		
		public Piso() {
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
		public void setColor(byte x, byte y, byte z ){
			r=x;g=y;b=z;
			colores = new byte[] {
					// Abajo - azul
					r, g, b, maxColor, // 0 0
					r, g, b, maxColor, // 1 1
					r, g, b, maxColor, // 2 2
					r, g, b, maxColor, // 3 3
				};
			
			/* Lee los colores */
			bufColores = ByteBuffer.allocateDirect(colores.length);
			bufColores.put(colores);
			bufColores.position(0); // puntero al principio del buffer
		}
}
