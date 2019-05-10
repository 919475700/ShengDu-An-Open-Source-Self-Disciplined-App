package com.zlf.selfdiscipline;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 慎独 主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private ImageView mfocus;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private TextView mx_data;
    private TextView my_data;
    private TextView mz_data;
    private Vibrator vibrator;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (vibrator.hasVibrator())
                    vibrator.vibrate(3000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //动画效果
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
        Transition transition = new Fade();
        transition.setDuration(800);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
        getWindow().setReturnTransition(transition);
        getWindow().setReenterTransition(transition);
        setContentView(R.layout.activity_main);
        //初始化控件
        mfocus = findViewById(R.id.focus);
        mx_data = findViewById(R.id.x_data);
        my_data = findViewById(R.id.y_data);
        mz_data = findViewById(R.id.z_data);
        mfocus.setOnClickListener(this);
        //初始化传感器
        // 陀螺仪初始化
        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this,
                gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        // 振动器初始化
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.focus:
                Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 0.5f) {
            focus_start();
        } else if (event.values[0] < -0.5f) {
            focus_start();
        }
        if (event.values[1] > 0.5f) {
            focus_start();
        } else if (event.values[1] < -0.5f) {
            focus_start();
        }
        if (event.values[2] > 0.5f) {
            focus_start();
        } else if (event.values[2] < -0.5f) {
            focus_start();
        }
    }

    private void focus_start() {
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessage(msg);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
