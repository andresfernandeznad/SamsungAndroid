package es.uma.processimage;

import android.graphics.Bitmap;
import android.graphics.Color;

public class FiltroMatrizOpt implements FiltroImagen {
    private float[] mascara;

    public FiltroMatrizOpt(float[] mas) {
        mascara = mas;
    }

    public byte pixelgris(Bitmap b, int x, int y){
        int pixel = b.getPixel(x, y);
        return (byte)((Color.red(pixel) + Color.green(pixel) + Color.blue(pixel))/3);
    }

    public void filtra(Bitmap image) {
 //       Kernel kernel = new Kernel(dimension, dimension, mascara);
 //       BufferedImageOp bright = new ConvolveOp(kernel);
 //       BufferedImage convolvedImage = bright.filter(image, null);

        int width=image.getWidth();
        int height=image.getHeight();
        int size=width*height;
        byte oimage[] = new byte[size];
        for(int i=0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                oimage[i * width + j] = pixelgris(image,j,i);
            }
        }

        // recorre la imagen excepto los bordes

        for(int i=1; i < height -1; i++){
            int row=i*width;
            int prevrow=row-width;
            int nextrow=row+width;
            for(int j=1; j < width - 1 ;j++){
                float fcolor = mascara[0] * ((int)oimage[prevrow+j-1]&0xFF) +
                         mascara[1] * ((int)oimage[prevrow+j]&0xFF) +
                         mascara[2] * ((int)oimage[prevrow+j+1]&0xFF) +
                         mascara[3] * ((int)oimage[row+j-1]&0xFF) +
                         mascara[4] * ((int)oimage[row+j]&0xFF) +
                         mascara[5] * ((int)oimage[row+j+1]&0xFF) +
                         mascara[6] * ((int)oimage[nextrow+j-1]&0xFF) +
                         mascara[7] * ((int)oimage[nextrow+j]&0xFF) +
                         mascara[8] * ((int)oimage[nextrow+j+1]&0xFF);

                int color = fcolor>255? 255 : fcolor<0 ? 0 : Math.round(fcolor);
                // asigna el mismo color en RGB y valor alfa a 1
                image.setPixel(j,i,0xff000000 | (color<<16) | (color<<8) | color);
            }
        }
    }

    public static FiltroMatrizOpt creaFiltroMedia() {
        float[] mascara =
                {       1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
                        1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
                        1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f};

        return new FiltroMatrizOpt(mascara);
    }

    public static FiltroMatrizOpt creaFiltroBordes() {
        float[] mascara = {-1.0f, -1.0f , -1.0f ,
                           -1.0f,  8.0f , -1.0f ,
                           -1.0f, -1.0f , -1.0f};
        return new FiltroMatrizOpt(mascara);
    }

    public static FiltroMatrizOpt creaFiltroEnfoque() {
        float[] mascara = {0f, -1.0f , 0f ,
                        -1.0f ,  5.0f, -1.0f ,
                          0f , -1.0f , 0f};
        return new FiltroMatrizOpt(mascara);
    }

}
