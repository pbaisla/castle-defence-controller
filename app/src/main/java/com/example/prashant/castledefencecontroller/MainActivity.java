package com.example.prashant.castledefencecontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String IP_ADDR = "com.example.prashant.castledefencecontroller.IP_ADDR";
    private Button bt;
    private EditText edTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.button);
        edTxt = (EditText) findViewById(R.id.editText);

        edTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTxt.setText("");
            }
        });
    }

    public void submit(View v) {
        Toast.makeText(getApplicationContext(), edTxt.getText().toString(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ReadyActivity.class);
        String message = edTxt.getText().toString();
        intent.putExtra(IP_ADDR, message);
        startActivity(intent);
    }


}
