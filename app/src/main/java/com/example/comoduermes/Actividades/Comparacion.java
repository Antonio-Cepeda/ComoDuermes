package com.example.comoduermes.Actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.comoduermes.R;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Comparacion extends AppCompatActivity {

    //TextViews
    TextView tvOpinion, tvResultadosEstudio;

    //Botones
    Button buttonMenuPrincipal;

    //Intent
    Intent iMP;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparacion);

        iMP = new Intent(Comparacion.this, MainActivity.class);
        iMP.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        tvOpinion = findViewById(R.id.tvOpinion);
        tvResultadosEstudio = findViewById(R.id.tvResultadosEstudio);
        buttonMenuPrincipal = findViewById(R.id.ButtonMP);

        PuntuacionOpinion(Cuestionario.calificacion);
        PuntuacionCodigo();

        buttonMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(iMP);
                System.exit(0);
            }
        });
    }

    public void PuntuacionOpinion (int i){
        if (i > 14){
            tvOpinion.setText("Has dormido muy bien");
        }else if(i<14 && i>10){
            tvOpinion.setText("Has dormido bien");
        }else if (i <= 10 && i > 6){
            tvOpinion.setText("has domrido regular");
        }else if (i <= 6){
            tvOpinion.setText("Has dormido muy mal");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void PuntuacionCodigo(){
        MainActivity.DuracionMedia(tvResultadosEstudio, MainActivity.historialLuzApagada.get(0), MainActivity.historialLuzEncendida.get(0), MainActivity.historialAcelerometroParado.get(0), MainActivity.historialAcelerometroMovido.get(0));
    }
}