package com.example.android.customfancontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

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
        LifeWheelView bodyLifeWheelStatus = findViewById(R.id.lifeWheelView);
        TextView seekBarProgress = findViewById(R.id.bodySeekBarProgress);
        seekBarProgress.setText("Seek bar progress: "+progress);
    }

    @Override
    public void onStartTrackingTouch( SeekBar seekBar ) {
        TextView seekBarProgress = findViewById(R.id.bodySeekBarProgress);
        seekBarProgress.setText("Seek bar touch started");
    }

    @Override
    public void onStopTrackingTouch( SeekBar seekBar) {
        TextView seekBarProgress = findViewById(R.id.bodySeekBarProgress);
        seekBarProgress.setText("Seek bar touch stopped");
    }
}
