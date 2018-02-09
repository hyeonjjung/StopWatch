package org.androidtown.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer mChronometer;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    long c_time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChronometer = (Chronometer) findViewById(R.id.chronometer_view);
        mChronometer.setBase(SystemClock.elapsedRealtime());

        startBtn = (Button) findViewById(R.id.start_button);
        stopBtn = (Button) findViewById(R.id.stop_button);
        resetBtn = (Button) findViewById(R.id.reset_button);

        stopBtn.setEnabled(false);
        resetBtn.setEnabled(false);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                Log.d("s_time", String.valueOf(c_time));
                mChronometer.setBase(SystemClock.elapsedRealtime()+c_time);
                mChronometer.start();
                startBtn.setEnabled(false);
                stopBtn.setEnabled(true);
                resetBtn.setEnabled(true);
                break;
            case R.id.stop_button:
                mChronometer.stop();
                c_time = mChronometer.getBase()-SystemClock.elapsedRealtime();
                stopBtn.setEnabled(false);
                startBtn.setEnabled(true);
                resetBtn.setEnabled(true);
                break;
            case R.id.reset_button:
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                c_time = 0;
                startBtn.setEnabled(true);
                stopBtn.setEnabled(false);
                resetBtn.setEnabled(false);
                break;
        }
    }
}
