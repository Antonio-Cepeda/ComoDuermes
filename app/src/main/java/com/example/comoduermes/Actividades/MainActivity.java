package com.example.comoduermes.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comoduermes.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // TextViews
    TextView tvLuz;
    TextView tvProximidad;
    TextView tvContadorLuz;
    TextView tvContadorProximidad;
    TextView tvAcelerometro;

    // Sensores
    SensorManager sm;
    Sensor sensorProximidad;
    Sensor sensorLuz;
    Sensor sensorAcelerometro;

    SensorEventListener sensorEventListener;

    // Variables
    int segundos, minutos, horas = 0;
    Calendar calendario = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sensores
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorProximidad = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorLuz = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAcelerometro = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // TextViews
        tvLuz = findViewById(R.id.textoLuz);
        tvProximidad = findViewById(R.id.textoProximidad);
        tvContadorLuz = findViewById(R.id.textoContadorLuz);
        tvContadorProximidad = findViewById(R.id.textoContadorProximidad);
        tvAcelerometro = findViewById(R.id.textoAcelerometro);


        // Por si no existen los sensores
       /*if (sensorAcelerometro == null)
           System.out.print("no hay sensor");
           
           if (sensorLuz == null)
           System.out.print("no hay sensor");

           if (sensorProximidad == null)
           System.out.print("no hay sensor");*/


        sensorEventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                // Sensor LUZ
                if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
                    float lux = sensorEvent.values[0];
                    if (sensorEvent.values[0] < 15) {
                        tvLuz.setText("Numero de luxes:" + lux);

                        //getWindow().getDecorView().setBackgroundColor(Color.BLUE);

                       /*segundos++;
                        if (segundos == 60) {
                            segundos = 0;
                            minutos += 1;
                        }
                        if (minutos == 60) {
                            minutos = 0;
                            horas += 1;
                        }
                       tvContadorLuz.setText();*/
                    } else  {
                        tvLuz.setText("Numero de luxes: " + lux);
                        //getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                        //segundos = 0;
                        // minutos = 0;
                        // horas = 0;
                        //tvContadorLuz.setText(horas + " horas " + minutos + " minutos " + segundos + " segundos");
                    }
                }


                // Sensor PROXIMIDAD
                if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    float distancia = sensorEvent.values[0];
                    if (sensorEvent.values[0] < 3.5) {
                        tvProximidad.setText("Numero de cm: " + distancia);
                        //getWindow().getDecorView().setBackgroundColor(Color.BLUE);

                        /*segundos++;
                        if (segundos == 60) {
                            segundos = 0;
                            minutos += 1;
                        }
                        if (minutos == 60) {
                            minutos = 0;
                            horas += 1;
                        }
                        tvContadorProximidad.setText(horas + " horas " + minutos + " minutos " + segundos + " segundos");*/
                    } else {
                        tvProximidad.setText("Numero de cm: " + distancia);
                        //getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                        // segundos = 0;
                        //minutos = 0;
                        // horas = 0;
                        //tvContadorProximidad.setText(horas + " horas " + minutos + " minutos " + segundos + " segundos");
                    }
                }


                // Sensor ACELERÓMETRO
                if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];
                    getWindow().getDecorView().setBackgroundColor(Color.RED);

                    if (x<1.5 && x>-1.5 && y>-1.5 && y<1.5 && z>9  && z<11){
                        tvAcelerometro.setText("El móvil está parado");
                        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    }else {
                        tvAcelerometro.setText("Estás moviendo el móvil");
                        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                    }

                }


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        startProximidad();
        startLuz();
        startAcelerometro();
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
    protected void onPause() {
        stopProximidad();
        stopLuz();
        stopAcelerometro();
        super.onPause();
    }

    @Override
    protected void onResume() {
        startProximidad();
        startLuz();
        startAcelerometro();
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
