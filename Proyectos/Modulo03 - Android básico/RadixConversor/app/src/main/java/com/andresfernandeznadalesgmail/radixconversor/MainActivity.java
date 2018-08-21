package com.andresfernandeznadalesgmail.radixconversor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText dec;
    EditText out;
    int decNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dec = findViewById(R.id.editText3);
        out = findViewById(R.id.editText4);
        dec.setText(decNumber + "");
    }
    public void anyadirNum(View v) {
        btn = findViewById(v.getId());
        int numero = Integer.parseInt(btn.getText().toString());
        int valorEdit = Integer.parseInt(dec.getText().toString());
        dec.setText((valorEdit*10) + numero + "");
        decNumber = Integer.parseInt(dec.getText().toString());
    }
    @Override
    protected void onResume() {
        super.onResume();
        dec = findViewById(R.id.editText3);
        dec.setText(decNumber + "");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        decNumber = savedState.getInt("valorDec", 0);
        if (savedState != null) {
            decNumber = savedState.getInt("valorDec");
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("valorDec", decNumber);
        super.onSaveInstanceState(outState);
    }
    public void botonC(View v) {
        dec.setText(0 + "");
        decNumber = 0;
    }
    public void botonBorra(View v) {
        decNumber = decNumber/10;
        dec.setText(decNumber + "");
    }
    public void botonConver(View v) {
        RadioGroup rg = findViewById(R.id.radioGroup);
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radioButton15:
                out.setText(Integer.toBinaryString(decNumber));
                break;
            case R.id.radioButton16:
                out.setText(Integer.toHexString(decNumber).toUpperCase());
                break;
            case R.id.radioButton14:
                out.setText(Integer.toOctalString(decNumber));
                break;
        }
    }
}
