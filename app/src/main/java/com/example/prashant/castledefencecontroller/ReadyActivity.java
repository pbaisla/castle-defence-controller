package com.example.prashant.castledefencecontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ReadyActivity extends AppCompatActivity {
    TextView status_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.IP_ADDR);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        status_message = (TextView) findViewById(R.id.status_message);
        status_message.setText("Connecting to " + message);
    }

}
