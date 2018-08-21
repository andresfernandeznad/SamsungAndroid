package com.andresfernandeznadalesgmail.calculadorapolacainversa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txt;
    Button meteNum;
    Button suma;
    Button resta;
    Button mult;
    Button divid;
    Button res;
    TextView tv;
    static CPI cpi = new CPI();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEntra:
                String num = txt.getText().toString();
                cpi.entra(Double.parseDouble(num));
                System.out.println(cpi);
                txt.getText().clear();
                break;
            case R.id.buttonSuma:
                cpi.suma();
                System.out.println(cpi);
                break;
            case R.id.buttonResta:
                cpi.resta();
                break;
            case R.id.buttonDivide:
                cpi.divide();
                break;
            case R.id.buttonMultiplica:
                cpi.multiplica();
                break;
            case R.id.buttonResultado:
                tv.setText("Resultado " + cpi.getResultado());
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.editText);
        meteNum = findViewById(R.id.buttonEntra);
        suma = findViewById(R.id.buttonSuma);
        resta = findViewById(R.id.buttonResta);
        mult = findViewById(R.id.buttonMultiplica);
        tv = findViewById(R.id.textView);
        divid = findViewById(R.id.buttonDivide);
        res = findViewById(R.id.buttonResultado);
        meteNum.setOnClickListener(this);
        res.setOnClickListener(this);
        divid.setOnClickListener(this);
        mult.setOnClickListener(this);
        suma.setOnClickListener(this);
        resta.setOnClickListener(this);
    }
}
