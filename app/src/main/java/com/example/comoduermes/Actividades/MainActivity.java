package com.example.comoduermes.Actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.comoduermes.R;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // TextViews
    TextView tvResultado, tvResultadoLuz, tvResultadoAcelerometro;

    // Botones
    Button buttonStart, buttonStop, buttonCuestionario;

    // Sensores
    SensorManager sm;
    Sensor sensorProximidad, sensorLuz, sensorAcelerometro;
    SensorEventListener sensorEventListener;

    // Variables
    static List<LocalTime> historialLuzEncendida = new ArrayList<LocalTime>();
    static List<LocalTime> historialLuzApagada = new ArrayList<LocalTime>();
    //static List<LocalTime> historialProximidadCercana = new ArrayList<LocalTime>();
    //static List<LocalTime> historialProximidadLejana = new ArrayList<LocalTime>();
    static List<LocalTime> historialAcelerometroMovido = new ArrayList<LocalTime>();
    static List<LocalTime> historialAcelerometroParado = new ArrayList<LocalTime>();

    long duracionTotal, duracionLuz, duracionAcelerometro, horasLuz, minutosLuz, segundosLuz;

    // Intents
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CrearVariables();
        HaySensores();
        buttonCuestionario.setEnabled(false);

       // Desarrollo del código
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sensorEventListener = new SensorEventListener() {

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {

                        // Sensor LUZ
                        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
                            //lux = sensorEvent.values[0];
                            if (sensorEvent.values[0] < 5) {
                                historialLuzApagada.add(LocalTime.now());
                            } else {
                                historialLuzEncendida.add(LocalTime.now());
                            }
                        }


                        // Sensor PROXIMIDAD
                        /*if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                           if (sensorEvent.values[0] < 3.5) {
                                historialProximidadCercana.add(LocalTime.now());
                            } else {
                                historialProximidadLejana.add(LocalTime.now());
                            }
                        }*/


                        //Sensor ACELERÓMETRO
                        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                            float x = sensorEvent.values[0];
                            float y = sensorEvent.values[1];
                            float z = sensorEvent.values[2];

                            if (x<1.5 && x>-1.5 && y>-1.5 && y<1.5 && z>9  && z<11){
                                historialAcelerometroParado.add(LocalTime.now());
                            }else {
                                historialAcelerometroMovido.add(LocalTime.now());
                            }
                        }


                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {}
                };
                onStart();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                onStop();
                DuracionMedia(tvResultado, historialLuzApagada.get(0), historialLuzEncendida.get(0), historialAcelerometroParado.get(0), historialAcelerometroMovido.get(0));
                //Duracion(tvResultadoLuz, historialLuzApagada.get(0), historialLuzEncendida.get(0));
                //Duracion(tvResultadoAcelerometro, historialAcelerometroParado.get(0), historialAcelerometroMovido.get(0));
                buttonCuestionario.setEnabled(true);
            }
        });

        buttonCuestionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

    }

    public void startProximidad(){
        sm.registerListener(sensorEventListener, sensorProximidad, 1000*1000);
    }

    public void stopProximidad(){
        sm.unregisterListener(sensorEventListener);
    }

    public void startLuz(){
        sm.registerListener(sensorEventListener, sensorLuz, 1000*1000);
        // Ampliar el tiempo de 1 segundo a 15 minutos
    }

    public void stopLuz(){
        sm.unregisterListener(sensorEventListener);
    }

    public void startAcelerometro(){
        sm.registerListener(sensorEventListener, sensorAcelerometro, 1000*1000);
    }

    public void stopAcelerometro(){
        sm.unregisterListener(sensorEventListener);
    }

    @Override
    public void onStop() {
        stopProximidad();
        stopLuz();
        stopAcelerometro();
        super.onStop();
    }

    @Override
    public void onStart() {
        startProximidad();
        startLuz();
        startAcelerometro();
        super.onStart();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {}

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public void CrearVariables(){
        // Sensores
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorProximidad = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorLuz = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAcelerometro = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // TextViews
        tvResultado = findViewById(R.id.tvResultado);
        //tvResultadoLuz = findViewById(R.id.tvResultadoLuz);
        //tvResultadoAcelerometro = findViewById(R.id.tvResultadoAcelerometro);

        // Botones
        buttonStart = findViewById(R.id.ButtonStart);
        buttonStop = findViewById(R.id.ButtonStop);
        buttonCuestionario = findViewById(R.id.ButtonCuestionario);

        // Intents
        i = new Intent(MainActivity.this, Cuestionario.class);
    }

    public void HaySensores(){
        // Por si no existen los sensores
        if (sensorAcelerometro == null)
            System.out.print("No hay sensor acelerómetro");

        if (sensorLuz == null)
            System.out.print("No hay sensor de Luz");

        if (sensorProximidad == null)
            System.out.print("No hay sensor de Proximidad");
    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Duracion(TextView tv, LocalTime inicio1, LocalTime fin1){
        long segundos, minutos = 0, horas = 0;
        String NumSeg, NumMin, NumH;
        segundos = ChronoUnit.SECONDS.between(inicio1, fin1);


        if(segundos == 60) {
            minutos += minutos;
            segundos = 0;
        }

        if(minutos == 60) {
            horas += horas;
            minutos = 0;
        }

        NumSeg = segundos + " seg";
        NumMin = minutos + " min ";
        NumH = horas + " h ";

        if(horas < 9)
            NumH = "0" + horas + " h ";

        if(minutos < 9)
            NumMin = "0" + minutos + " min ";

        if (segundos < 9)
            NumSeg = "0" + segundos + " seg";

        tv.setText("Has dormido: " + NumH + NumMin + NumSeg);
    }*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void DuracionMedia(TextView tv, LocalTime inicio1, LocalTime fin1, LocalTime inicio2, LocalTime fin2){
        long segundos, minutos = 0, horas = 0, duracion1, duracion2;
        String NumSeg, NumMin, NumH;
        duracion1 = ChronoUnit.SECONDS.between(inicio1, fin1);
        duracion2 = ChronoUnit.SECONDS.between(inicio2, fin2);

        segundos = (duracion1 + duracion2)/2;

        if(segundos == 60) {
            minutos += minutos;
            segundos = 0;
        }

        if(minutos == 60) {
            horas += horas;
            minutos = 0;
        }

        NumSeg = segundos + " seg";
        NumMin = minutos + " min ";
        NumH = horas + " h ";

        if(horas < 9)
            NumH = "0" + horas + " h ";

        if(minutos < 9)
            NumMin = "0" + minutos + " min ";

        if (segundos < 9)
            NumSeg = "0" + segundos + " seg";

        tv.setText("Has dormido: " + NumH + NumMin + NumSeg);
    }
}
