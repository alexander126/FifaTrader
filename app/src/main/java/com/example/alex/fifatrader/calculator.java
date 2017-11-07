package com.example.alex.fifatrader;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        final SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        String yourmoney = prefs.getString("mymoney", "");
        TextView textLine = (TextView) findViewById(R.id.textLine);
        textLine.setText(yourmoney);
    }
}
