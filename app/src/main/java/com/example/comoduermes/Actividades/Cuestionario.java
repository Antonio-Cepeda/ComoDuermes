package com.example.comoduermes.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.comoduermes.R;

public class Cuestionario extends AppCompatActivity implements View.OnClickListener {

    //Pregunta 1
    Button a1, b1, c1, d1, e1;

    //Pregunta 2
    Button a2, b2, c2, d2, e2;

    //Pregunta 3
    Button a3, b3, c3, d3, e3;

    // Resultado
    static int calificacion = 0;

    //Intents
    Intent iatras, iadelante;

    // Botones
    Button volver;
    Button enviar;

    // TextViews
    TextView tvcomprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        iatras = new Intent(Cuestionario.this, MainActivity.class);
        iadelante = new Intent(Cuestionario.this, Comparacion.class);

        CrearRespuestas1();
        CrearRespuestas2();
        CrearRespuestas3();

        CrearBotones();

        tvcomprobar = findViewById(R.id.tvComprobar);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Buttona1:
                calificacion += 5;
                deshabilitar1();
                break;
            case R.id.Buttonb1:
                calificacion += 4;
                deshabilitar1();
                break;
            case R.id.Buttonc1:
                calificacion += 3;
                deshabilitar1();
                break;
            case R.id.Buttond1:
                calificacion += 2;
                deshabilitar1();
                break;
            case R.id.Buttone1:
                calificacion += 1;
                deshabilitar1();
                break;

            case R.id.Buttona2:
                calificacion += 1;
                deshabilitar2();
                break;
            case R.id.Buttonb2:
                calificacion += 3;
                deshabilitar2();
                break;
            case R.id.Buttonc2:
                calificacion += 5;
                deshabilitar2();
                break;
            case R.id.Buttond2:
                calificacion += 4;
                deshabilitar2();
                break;
            case R.id.Buttone2:
                calificacion += 2;
                deshabilitar2();
                break;


            case R.id.Buttona3:
                    calificacion += 5;
                    deshabilitar3();
                    break;
            case R.id.Buttonb3:
                calificacion += 4;
                deshabilitar3();
                break;
            case R.id.Buttonc3:
                calificacion += 3;
                deshabilitar3();
                break;
            case R.id.Buttond3:
                calificacion += 2;
                deshabilitar3();
                break;
            case R.id.Buttone3:
                calificacion += 1;
                deshabilitar3();
                break;

            case R.id.ButtonVolver:
                startActivity(iatras);
                finish();
                break;

            case R.id.ButtonEnviar:
                startActivity(iadelante);
                finish();
                break;
        }
        tvcomprobar.setText("La calificaón es de: " + calificacion);
    }

    public void deshabilitar1(){
        a1.setEnabled(false);
        b1.setEnabled(false);
        c1.setEnabled(false);
        d1.setEnabled(false);
        e1.setEnabled(false);
    }

    public void deshabilitar2(){
        a2.setEnabled(false);
        b2.setEnabled(false);
        c2.setEnabled(false);
        d2.setEnabled(false);
        e2.setEnabled(false);
    }

    public void deshabilitar3(){
        a3.setEnabled(false);
        b3.setEnabled(false);
        c3.setEnabled(false);
        d3.setEnabled(false);
        e3.setEnabled(false);
    }

    public void CrearRespuestas1(){
        a1 = findViewById(R.id.Buttona1);
        a1.setOnClickListener(this);
        b1 = findViewById(R.id.Buttonb1);
        b1.setOnClickListener(this);
        c1 = findViewById(R.id.Buttonc1);
        c1.setOnClickListener(this);
        d1 = findViewById(R.id.Buttond1);
        d1.setOnClickListener(this);
        e1 = findViewById(R.id.Buttone1);
        e1.setOnClickListener(this);
    }

    public void CrearRespuestas2(){
        a2 = findViewById(R.id.Buttona2);
        a2.setOnClickListener(this);
        b2 = findViewById(R.id.Buttonb2);
        b2.setOnClickListener(this);
        c2 = findViewById(R.id.Buttonc2);
        c2.setOnClickListener(this);
        d2 = findViewById(R.id.Buttond2);
        d2.setOnClickListener(this);
        e2 = findViewById(R.id.Buttone2);
        e2.setOnClickListener(this);
    }

    public void CrearRespuestas3(){
        a3 = findViewById(R.id.Buttona3);
        a3.setOnClickListener(this);
        b3 = findViewById(R.id.Buttonb3);
        b3.setOnClickListener(this);
        c3 = findViewById(R.id.Buttonc3);
        c3.setOnClickListener(this);
        d3 = findViewById(R.id.Buttond3);
        d3.setOnClickListener(this);
        e3 = findViewById(R.id.Buttone3);
        e3.setOnClickListener(this);
    }

    public void CrearBotones(){
        volver = findViewById(R.id.ButtonVolver);
        volver.setOnClickListener(this);
        enviar = findViewById(R.id.ButtonEnviar);
        enviar.setOnClickListener(this);
    }
}