package com.example.prashant.castledefencecontroller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ControllerActivity extends AppCompatActivity {
    Handler connectHandler = ReadyActivity.connectHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_controller);

        Intent intent = getIntent();
        String color = intent.getStringExtra(ReadyActivity.COLOR);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.cview);
        rl.setBackgroundColor(Color.parseColor(color));

        Button shoot_button = (Button) findViewById(R.id.shoot);
        shoot_button.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 250);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Performing action...");
                    if (connectHandler != null) {
                        Message msg = connectHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putString("move", "Shoot");
                        msg.setData(bundle);
                        connectHandler.sendMessage(msg);
                    }
                    mHandler.postDelayed(this, 250);
                }
            };

        });

        Button left_button = (Button) findViewById(R.id.left);
        left_button.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Performing action...");
                    if (connectHandler != null) {
                        Message msg = connectHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putString("move", "Left");
                        msg.setData(bundle);
                        connectHandler.sendMessage(msg);
                    }
                    mHandler.postDelayed(this, 100);
                }
            };

        });

        Button right_button = (Button) findViewById(R.id.right);
        right_button.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 100);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Performing action...");
                    if (connectHandler != null) {
                        Message msg = connectHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putString("move", "Right");
                        msg.setData(bundle);
                        connectHandler.sendMessage(msg);
                    }
                    mHandler.postDelayed(this, 100);
                }
            };

        });
    }

    public void moveleft(View v) {
        if (connectHandler != null) {
            Message msg = connectHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("move", "Left");
            msg.setData(bundle);
            connectHandler.sendMessage(msg);
        }
    }
    public void moveright(View v) {
        if (connectHandler != null) {
            Message msg = connectHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("move", "Right");
            msg.setData(bundle);
            connectHandler.sendMessage(msg);
        }
    }
    public void shoot(View v) {
        if (connectHandler != null) {
            Message msg = connectHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("move", "Shoot");
            msg.setData(bundle);
            connectHandler.sendMessage(msg);
        }
    }

}
