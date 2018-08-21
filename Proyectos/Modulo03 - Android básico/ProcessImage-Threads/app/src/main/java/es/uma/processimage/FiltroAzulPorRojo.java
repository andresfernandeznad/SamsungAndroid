package es.uma.processimage;


import android.graphics.Bitmap;
import android.graphics.Color;

public class FiltroAzulPorRojo implements FiltroImagen {

	public void filtra(Bitmap imagen) {
		int fWidth  = imagen.getWidth();
		int fHeight = imagen.getHeight();
		
		for (int x = 0; x < fWidth ; x++) {
			for (int y = 0; y < fHeight; y++) {
				int pixel = imagen.getPixel(x, y);
				int rojo = Color.red(pixel);
				int verde = Color.green(pixel);
				int azul = Color.blue(pixel);
				imagen.setPixel(x, y, Color.rgb(azul,verde,rojo));
			}
		}
	}
}
