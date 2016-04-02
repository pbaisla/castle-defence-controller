package com.example.prashant.castledefencecontroller;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ControllerActivity extends AppCompatActivity {
    Handler connectHandler = ReadyActivity.connectHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_controller);
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
