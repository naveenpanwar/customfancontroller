package com.example.android.customfancontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class LifeWheelActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar bodySeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_wheel);

        bodySeekBar = findViewById(R.id.bodySeekBar);
        bodySeekBar.setOnSeekBarChangeListener(this);
        bodySeekBar.setMax(5);
    }

    @Override
    public void onProgressChanged( SeekBar seekBar, int progress, boolean fromUser) {
        LifeWheelView lview = findViewById(R.id.lifeWheelView);
        lview.setBody(progress);
        Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch( SeekBar seekBar) {
        Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartTrackingTouch( SeekBar seekBar ) {
        Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
    }
}
