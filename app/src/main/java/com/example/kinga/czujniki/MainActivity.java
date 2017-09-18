package com.example.kinga.czujniki;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SensorEventListener{

    private static final int RECORD_REQUEST_CODE =101 ;
    private static final String TAG ="MainActivity" ;
    TextView tx;
    SensorManager manager;
    List<Sensor> msensorList;
    Sensor mSensor;
    ImageView iv;
    private boolean running;
    int x=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // tx = (TextView) findViewById(R.id.tx);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //int permissionCheck = ContextCompat.checkSelfPermission(this,//pozwolenie
          //      Manifest.permission.BODY_SENSORS);
        //if (permissionCheck != PackageManager.PERMISSION_GRANTED ){
          //  Log.i(TAG, "Permission to record denied");
            //makeRequest();
        //}
        //msensorList = manager.getSensorList(Sensor.TYPE_ALL);
        //for (Sensor s : msensorList) {
          //  tx.append("\n\n" + x+". "+s.getName());
            //x++;
        //}

        mSensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);//czujnik zblizeniowy
        iv = (ImageView) findViewById(R.id.imageView); //inicjacja obrazka
        //manager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);


    }/*
    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.BODY_SENSORS,
                },
                RECORD_REQUEST_CODE);
    }*/

    protected void onResume() {  //to poprostu jt potrzebne
        super.onResume();
        running = true;
        manager.registerListener( this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }

    protected void onPause() { //i to tez
        super.onPause();
        manager.unregisterListener(this);
        running = false;
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] == 0) {
            iv.setImageResource(R.drawable.blisko); //obrazek jt jak sie zblizy reke
        } else {
            iv.setImageResource(R.drawable.daleko);// jt jak sie zabierze
        }
    }


}