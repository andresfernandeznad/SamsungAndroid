package com.andresfernandeznadalesgmail.myrobot;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    RadioButton rbB;
    RadioButton rbG;
    RadioGroup rg;
    ImageView iv;
    Button btn;
    EditText txt;
    int colorFondo = Color.RED;
    boolean esCircular = false;

    @Override
    public void onClick(View v) {
            /*if (v instanceof Button) {
                tv.setBackgroundColor(Color.RED);
                tv.setTextColor(Color.WHITE);
            } else {
                iv.setImageResource(R.mipmap.ic_launcher_round);
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.radioButtonBlue:
                        tv.setBackgroundColor(Color.BLUE);
                        tv.setTextColor(Color.WHITE);
                        break;
                    case R.id.radioButtonGreen:
                        tv.setBackgroundColor(Color.GREEN);
                        tv.setTextColor(Color.WHITE);
                        break;
                }
            }*/

        switch (v.getId()) {
            case R.id.buttonRed:
                Long ctl;
                String number = txt.getText().toString();
                if (number.matches("0x\\p{XDigit}{8}")) {//[0-9a-fA-F]
                    try {
                        ctl = Long.parseLong(number.substring(2), 16); //Radix base hexadecimal
                        colorFondo = ctl.intValue();
                        tv.setTextColor(Color.YELLOW);
                    } catch (Exception e) {
                        Log.i("Andres", "Excepcion: "+ e.getMessage());
                        Toast.makeText(this, "Invalid color", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.imageView:
                /*if (!esCircular) {*/
                    iv.setImageResource(R.mipmap.ic_launcher_round);
                    esCircular = true;
                /*} else {
                    iv.setImageResource(R.mipmap.ic_launcher);
                    esCircular = false;
                }*/
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.radioButtonBlue:
                        colorFondo = Color.BLUE;
                        tv.setTextColor(Color.WHITE);
                        break;
                    case R.id.radioButtonGreen:
                        colorFondo = Color.GREEN;
                        tv.setTextColor(Color.WHITE);
                        break;
                }
                break;
        }
        tv.setBackgroundColor(colorFondo);
    }
  //  Resources.Theme t;
    //private myL myListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Método que arranca la app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        rbB = findViewById(R.id.radioButtonBlue);
        rbG = findViewById(R.id.radioButtonGreen);
        rg = findViewById(R.id.radioGroup);
        iv = findViewById(R.id.imageView);
        btn = findViewById(R.id.buttonRed);
        txt =  (EditText) findViewById(R.id.editText);
        //t = this.getTheme();
        //myListener = new myL();
        btn.setOnClickListener(this); //Al crear el listener dentro de la propia clase implementando la interfaz View.OnClick...
        iv.setOnClickListener(this);
        if (null != savedInstanceState) {
            esCircular = savedInstanceState.getBoolean("countSaved");
            colorFondo = savedInstanceState.getInt("countSavedColor", Color.WHITE);
            tv.setBackgroundColor(colorFondo);
        }
        if (esCircular) {
            iv.setImageResource(R.mipmap.ic_launcher_round);
        }
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tv.setTextColor(Color.YELLOW);
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(R.mipmap.ic_launcher_round);
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.radioButtonBlue:
                        tv.setBackgroundColor(Color.BLUE);
                        tv.setTextColor(Color.YELLOW);
                        break;
                    case R.id.radioButtonGreen:
                        tv.setBackgroundColor(Color.GREEN);
                        tv.setTextColor(Color.YELLOW);
                        break;
                }
            }
        });*/
    }

    //Guarda los valores que quiero preservar.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save instance-specific state
        outState.putBoolean("countSaved", esCircular);
        outState.putInt("countSavedColor", colorFondo);
        super.onSaveInstanceState(outState);
    }

    /*@Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        // Restore state; we know savedState is not null
        esCircular = savedState.getInt("countSaved", 0);
        Log.i(TAG, "onRestoreInstanceState -> restoring countSavedState to "+countSavedState);
    }*/



    /*public void botonPulsado(View v) {
        //tv.setBackgroundColor(getResources().getColor(R.color.colorAccent, this.getTheme()));
        tv.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        tv.setTextColor(Color.WHITE);
        //btn.setBackgroundColor(0xFFC1FFFF); Colores con hexadecimales
    }

    public void robotPulsado(View view) {
        iv.setImageResource(R.mipmap.ic_launcher_round);
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radioButtonBlue:
                tv.setBackgroundColor(Color.BLUE);
                tv.setTextColor(Color.WHITE);
                break;
            case R.id.radioButtonGreen:
                tv.setBackgroundColor(Color.GREEN);
                tv.setTextColor(Color.WHITE);
                break;
        }
//        if (rbB.isChecked()) tv.setBackgroundColor(Color.BLUE);
//        if (rbG.isChecked()) tv.setBackgroundColor(Color.GREEN);
    }*/
}
