package com.example.tpcubv4.control;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpcubv4.R;

import java.util.List;

public class DeviceSensors extends AppCompatActivity {

    private Sensor sensor;
    private SensorManager sensorManager;

    public void getSensorsList() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        TextView sensorsList = findViewById(R.id.tv_item);
        String value = new String("");
        sensorsList.setText(value);
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < deviceSensors.size(); i++) {
            sensorsList.setText(sensorsList.getText() + deviceSensors.get(i).getName() + "\n");
            stringBuffer.append(deviceSensors.get(i).getName() + "\n");
        }
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null)
            Toast.makeText(getApplicationContext(), "Detected the following sensors:\n" + stringBuffer, Toast.LENGTH_LONG);
        else
            Toast.makeText(getApplicationContext(), "There are no sensors", Toast.LENGTH_SHORT);
    }

    public void getAccelerometer() {
        //TODO code here
    }

}