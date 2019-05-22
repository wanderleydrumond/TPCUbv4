package com.example.tpcubv4;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpcubv4.control.DeviceSensors;
import com.example.tpcubv4.view.ExpandableAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    List<String> listGroup;
    private HashMap<String, List<String>> listData;
    public static Integer listCurrentposition;
    ExpandableListView expandableListView;
    DeviceSensors deviceSensors = new DeviceSensors();

    public static Button bSend;
    public static TextView tvSensorList, tvXAxis, tvYAxis,tvZAxis, tvLatitude, tvLongitude, tvAltitude;

    private boolean permission;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildList();

        expandableListView = findViewById(R.id.elv_expandable_list_view);
        expandableListView.setAdapter(new ExpandableAdapter(MainActivity.this, listGroup, listData));

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (listCurrentposition) {
                    case 0:
                        //TODO GPS
                        Toast.makeText(MainActivity.this, "Sensors List", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //TODO GYROSCOPE
                        Toast.makeText(MainActivity.this, "Sensors List", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        //TODO ACCELEROMETER
                        Toast.makeText(MainActivity.this, "Sensors List", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        deviceSensors.getSensorsList();
                        Toast.makeText(MainActivity.this, R.string.sensors_list, Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, "List opened", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MainActivity.this, "List closed", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setGroupIndicator(getResources().getDrawable(R.drawable.icon_group));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void buildList() {
        listGroup = new ArrayList<String>();
        listData = new HashMap<String, List<String>>();

        //Groups
        listGroup.add("GPS");
        listGroup.add("Gyroscope");
        listGroup.add("Accelerometer");
        listGroup.add("Sensors List");

        //Items
        List<String> auxiliarylist = new ArrayList<String>();
        auxiliarylist.add("Latitude");
        auxiliarylist.add("Longitude");
        auxiliarylist.add("Altitude");
        listData.put(listGroup.get(0), auxiliarylist);
        listCurrentposition = 0;

        auxiliarylist = new ArrayList<String>();
        auxiliarylist.add("X Axis");
        auxiliarylist.add("Y Axis");
        auxiliarylist.add("Z Axis");
        listData.put(listGroup.get(1), auxiliarylist);
        listCurrentposition = 1;

        auxiliarylist = new ArrayList<String>();
        auxiliarylist.add("Accelerometer value 1");
        auxiliarylist.add("Accelerometer value 2");
        auxiliarylist.add("Accelerometer value 3");
        listData.put(listGroup.get(2), auxiliarylist);
        listCurrentposition = 2;

        auxiliarylist = new ArrayList<String>();
        auxiliarylist.add("Full sensors list");
        listData.put(listGroup.get(3), auxiliarylist);
        listCurrentposition = 3;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final float alpha = (float)0.8;
        float[] gravity = new float[3];
        float[] linear_acceleration = new float[3];
        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        TextView tvXAxisValue = findViewById(R.id.tv_item);

        tvXAxisValue.setText("X:"+linear_acceleration[0]+",Y:"+linear_acceleration[1]+",Z:"+linear_acceleration[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}