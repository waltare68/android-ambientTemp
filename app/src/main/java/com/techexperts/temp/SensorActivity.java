package com.techexperts.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private ImageView img;
    private SensorManager sensorManager;
    private Sensor testSensor;
    private TextView ambient;
    private Boolean isTemperatureAvail;
    private RelativeLayout bg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        bg=findViewById(R.id.bg);
        ambient=findViewById(R.id.ambientTemp);
        //bg.setBackground(Color.RED);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null){
            testSensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureAvail=true;

        }else{
            //toast sensor ot available
            Toast.makeText(this,"Sensor Unavailable!",Toast.LENGTH_SHORT).show();
            isTemperatureAvail=false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
      int value;
      value= (int) event.values[0];
      //do some calc with value
    if (value >=40) {
       // bg.setBackground(Color.RED);//red
    }
     if ((value >=0) && (value<= 20)){
        // bg.setBackground(Color.YELLOW);//yellow
    }
     if ((value >=0) && (value<= 20)){
        // bg.setBackground(Color.WHITE);//white

        }
    if (value <0){
        //bg.setBackground(color.black);//blue

     }
     ambient.setText((int) event.values[0]+"º C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTemperatureAvail){
            // sensorManager.registerListener(this.testSensor, sensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this,testSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTemperatureAvail){
            sensorManager.unregisterListener(this);
        }
    }
    //inflate the menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(SensorActivity.this,MainActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}