package es.uma.processimage;

import android.graphics.Bitmap;
import android.graphics.Color;

public class FiltroMatriz implements FiltroImagen {
    private float[] mascara;

    public FiltroMatriz(float[] mas) {
        mascara = mas;
    }

    public int pixelgris(Bitmap b, int x, int y){
        int pixel = b.getPixel(x, y);
        return (Color.red(pixel) + Color.green(pixel) + Color.blue(pixel))/3;
    }

    public void filtra(Bitmap image) {
 //       Kernel kernel = new Kernel(dimension, dimension, mascara);
 //       BufferedImageOp bright = new ConvolveOp(kernel);
 //       BufferedImage convolvedImage = bright.filter(image, null);

        int width=image.getWidth();
        int height=image.getHeight();
        float fcolor;
        int color;
        Bitmap oimage = image.copy(Bitmap.Config.ARGB_8888,true);
        // recorre la imagen excepto los bordes

        for(int x=1; x < width -1; x++){
            for(int y=1; y < height - 1 ;y++){
                fcolor = mascara[0] * pixelgris(oimage,x-1,y-1) +
                         mascara[1] * pixelgris(oimage,x-1,y) +
                         mascara[2] * pixelgris(oimage,x-1,y+1) +
                         mascara[3] * pixelgris(oimage,x,y-1) +
                         mascara[4] * pixelgris(oimage,x,y) +
                         mascara[5] * pixelgris(oimage,x,y+1) +
                         mascara[6] * pixelgris(oimage,x+1,y-1) +
                         mascara[7] * pixelgris(oimage,x+1,y) +
                         mascara[8] * pixelgris(oimage,x+1,y+1);

                color = fcolor>255? 255 : fcolor<0 ? 0 : Math.round(fcolor);
                // asigna el mismo color en RGB y valor alfa a 1
                image.setPixel(x,y,0xff000000 | (color<<16) | (color<<8) | color);
            }
        }
    }

    public static FiltroMatriz creaFiltroMedia() {
        float[] mascara =
                {       1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
                        1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
                        1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f};

        return new FiltroMatriz(mascara);
    }

    public static FiltroMatriz creaFiltroBordes() {
        float[] mascara = {-1.0f, -1.0f , -1.0f ,
                           -1.0f,  8.0f , -1.0f ,
                           -1.0f, -1.0f , -1.0f};
        return new FiltroMatriz(mascara);
    }

    public static FiltroMatriz creaFiltroEnfoque() {
        float[] mascara = {0f, -1.0f , 0f ,
                        -1.0f ,  5.0f, -1.0f ,
                          0f , -1.0f , 0f};
        return new FiltroMatriz(mascara);
    }

}
