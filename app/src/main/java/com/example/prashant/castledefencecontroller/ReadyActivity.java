package com.example.prashant.castledefencecontroller;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReadyActivity extends AppCompatActivity {
    TextView status_message;
    Button ready_button;
    static Handler connectHandler, uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.IP_ADDR);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        status_message = (TextView) findViewById(R.id.status_message);
        status_message.setText("Connecting to " + message);

        ready_button = (Button) findViewById(R.id.ready_button);

        uiHandler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String connect_status = bundle.getString("connect_status");
                status_message.setText(connect_status);
                if (connect_status.compareTo("Connected") == 0) {
                    ready_button.setEnabled(true);
                    ready_button.setVisibility(View.VISIBLE);
                }
            }
        };

        new Thread(new Connect()).start();

        Log.e("QWERTY", "Thread started");
    }

    @Override
    protected void onDestroy() {
        if (connectHandler != null)
            connectHandler.getLooper().quit();
        super.onDestroy();
    }

    private class Connect implements Runnable {
        Socket socket = null;
        DataOutputStream out = null;
        BufferedReader in = null;

        public void run() {
            try {
                socket = new Socket("192.168.43.221", 12345);
                out = new DataOutputStream(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.writeUTF("Player connect");
                Log.e("QWERTY", "Message sent from app to server");
                String response = in.readLine();
                Log.e("QWERTY", "Message received from server " + response);
                if (uiHandler != null) {
                    Message msg = uiHandler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("connect_status", response);
                    msg.setData(bundle);
                    uiHandler.sendMessage(msg);
                    Log.e("QWERTY", "Message sent to ui");
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Looper.prepare();

            connectHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {

                }
            };

            Looper.loop();
        }
    }

}
