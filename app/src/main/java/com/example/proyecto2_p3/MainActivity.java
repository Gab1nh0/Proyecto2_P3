package com.example.proyecto2_p3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;

    private Button btn1;

    private TextView tv1, tv2;

    private Spinner spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        btn1 = findViewById(R.id.btn1);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        spp = findViewById(R.id.spp);

        String[] options = {"Voltaje", "Corriente", "Resistencia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options);
        spp.setAdapter(adapter);

        spp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selec = parent.getItemAtPosition(position).toString();
                if (selec.equals("Voltaje")) {
                    tv1.setText("Corriente");
                    tv2.setText("Resistencia");

                } else if (selec.equals("Corriente")) {
                    tv1.setText("Voltaje");
                    tv2.setText("Resistencia");

                }else if (selec.equals("Resistencia")) {
                    tv1.setText("Voltaje");
                    tv2.setText("Corriente");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1, num2, res=0;
                String operacion = "";
                num1 = Float.parseFloat(et1.getText().toString());
                num2 = Float.parseFloat(et2.getText().toString());
                if(spp.getSelectedItem().toString().equals("Voltaje")){
                    operacion = "Voltaje";
                    res = num1 * num2;
                }else if(spp.getSelectedItem().toString().equals("Corriente")){
                    operacion = "Corriente";
                    res = num1 / num2;
                } else if (spp.getSelectedItem().toString().equals("Resistencia")) {
                    operacion = "Resistencia";
                    res = num1 / num2;
                }

                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                intent.putExtra("res", res);
                intent.putExtra("op", operacion);
                startActivity(intent);

            }
        });

    }
}