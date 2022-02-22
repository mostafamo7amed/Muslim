package com.mostafadesha.quranfm.Fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.mostafadesha.quranfm.R;

public class CompassFragment extends Fragment implements SensorEventListener {

    ImageView compass;
    private static SensorManager sensorManager;
    float current_degree =0f ,direc;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        compass = getActivity().findViewById(R.id.compass_IV);
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (getArguments() != null) {
            direc = Float.parseFloat(getArguments().getString("compass"));
        }else{
            Toast.makeText(getActivity(), "nullll", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compass, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME );
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float degree = (float) Math.round(sensorEvent.values[0]);
        degree-=direc;
        RotateAnimation animation = new RotateAnimation(current_degree , -degree,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(120);
        animation.setFillAfter(true);
        compass.startAnimation(animation);
        current_degree = (-degree);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}