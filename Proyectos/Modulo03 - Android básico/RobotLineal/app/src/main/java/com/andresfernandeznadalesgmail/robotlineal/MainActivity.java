package com.andresfernandeznadalesgmail.robotlineal;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    RadioButton rbB;
    RadioButton rbG;
    RadioGroup rg;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Método que arranca la app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        rbB = findViewById(R.id.radioButtonBlue);
        rbG = findViewById(R.id.radioButtonGreen);
        rg = findViewById(R.id.radioGroup);
        iv = findViewById(R.id.imageView);
    }

    public void botonPulsado(View v) {
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
    }
}
