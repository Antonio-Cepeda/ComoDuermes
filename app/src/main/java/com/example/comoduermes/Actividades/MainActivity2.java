package com.example.comoduermes.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.comoduermes.R;

public class MainActivity2 extends AppCompatActivity {

    Button buttonStart, buttonStop;
    static boolean funcionando;
    TextView tvFuncionando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvFuncionando = findViewById(R.id.tvFuncionando);

        buttonStart = findViewById(R.id.ButtonStart);
        buttonStop = findViewById(R.id.ButtonStop);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcionando = true;
                //MainActivity.startProximidad();
                //tvFuncionando.setText("El booleano está a: " + funcionando);
            }
        });


        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcionando = false;
                //tvFuncionando.setText("El booleano está a: " + funcionando);
            }
        });

        tvFuncionando.setText("Numero de lumenes: " + MainActivity.lux);

    }

}