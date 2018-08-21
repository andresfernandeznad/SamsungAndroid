package es.uma.processimage;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.Random;
public class FiltroStereograma implements FiltroImagen {
	
	protected int tamTrama;
	protected int maxCapa;
	static final int TAM_TRAMA = 60;
	static final int MAX_CAPA = 16;
	static final Random aleatorio = new Random();
	
	public FiltroStereograma() {
		this(TAM_TRAMA, MAX_CAPA);
	}
	
	public FiltroStereograma( int tamTrama, int capas) {
		this.tamTrama = tamTrama;
		this.maxCapa  = capas;
	}
	
	public void filtra(Bitmap imagen) {
		int fWidth  = imagen.getWidth();
		int fHeight = imagen.getHeight();
		
		// Creamos una trama inicial a la izquierda
		for (int x = 0; x < tamTrama ; x++) {
			for (int y = 0; y < fHeight; y++) {
				imagen.setPixel(x, y, Color.rgb(aleatorio.nextInt(256),aleatorio.nextInt(256),aleatorio.nextInt(256)));
			}
		}
		// Completamos siguiendo el algoritmo
		for (int x = tamTrama ; x < fWidth ; x++) {
			for (int y = 0; y < fHeight; y++) {
				int altura = (255 - Color.blue(imagen.getPixel(x,y))) / (255 / maxCapa);
				imagen.setPixel(x, y, imagen.getPixel(x - tamTrama + altura, y));
			}
		}
	}
}
