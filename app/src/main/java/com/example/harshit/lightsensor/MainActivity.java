package com.example.harshit.lightsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        Sensor lightSensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor != null){
            manager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float[] data = event.values;
                    float lux = data[0];
                    Log.d("LightSensor","Lux: " + lux);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            },lightSensor,3000000);
        }
        else {
            Toast.makeText(this,"No light sensor",Toast.LENGTH_SHORT).show();
        }
    }
}
