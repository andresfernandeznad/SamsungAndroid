package es.uma.processimage;

import android.graphics.Bitmap;
import android.graphics.Color;

public class FiltroMatrizNth implements FiltroImagen {
    private float[] mascara;
    private byte oimage[];
    private Bitmap b;
    private int width;
    private int height;
    private int nth;

    FiltroMatrizNth(float[] mas, Bitmap _b, int _nth) {
        mascara = mas;
        b=_b;
        height=b.getHeight();
        width=b.getWidth();
        nth=_nth;
        oimage = new byte[height*width];
    }

    public byte pixelgris(int x, int y){
        int pixel = b.getPixel(x, y);
        return (byte)((Color.red(pixel) + Color.green(pixel) + Color.blue(pixel))/3);
    }

    public void filtra(Bitmap image) {

        Thread[] threads = new Thread[nth];
        for(int i=0; i< nth; i++) {
            threads[i] = new Thread(new myTask1(i));
            threads[i].start();
        }
        for(int i=0; i< nth; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i=0; i< nth; i++) {
            threads[i] = new Thread(new myTask2(i));
            threads[i].start();
        }
        for(int i=0; i< nth; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // clase que representa las tareas, su metodo "run" es el que realiza el trabajo efectivo
    public class myTask1 implements Runnable {
        // recorre la imagen excepto los bordes
        private int myId;
        myTask1(int _myId) { myId = _myId; }
        @Override
        public void run () {
            int rango=height-1;
            int inicio=myId*rango/nth;
            int fin=((myId+1)*rango)/nth;
            for(int i=inicio; i < fin; i++){
                for (int j = 0; j < width; j++) {
                    oimage[i * width + j] = pixelgris(j,i);
                }
            }
        }  // metodo para hacer el trabajo de la tarea
    }


    public class myTask2 implements Runnable {
        // recorre la imagen excepto los bordes
        private int myId;
        myTask2(int _myId) { myId = _myId; }
        @Override
        public void run () {
            int rango=height-1-1;
            int inicio=myId*rango/nth+1;
            int fin=((myId+1)*rango)/nth+1;
            for(int i=inicio; i < fin; i++){
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
                    b.setPixel(j,i,0xff000000 | (color<<16) | (color<<8) | color);
                }
            }
        }  // metodo para hacer el trabajo de la tarea
    }

    public static FiltroMatrizNth creaFiltroBordes(Bitmap b, int nth) {
        float[] mascara = {-1.0f, -1.0f , -1.0f ,
                           -1.0f,  8.0f , -1.0f ,
                           -1.0f, -1.0f , -1.0f};
        return new FiltroMatrizNth(mascara,b,nth);
    }
}
