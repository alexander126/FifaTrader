package com.example.alex.fifatrader;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class tradeSession extends AppCompatActivity {

    private static double Profit = 0;
    private static double Tax = 0;
    String m_Text = "";
    String soldV = "";
    String boughtV = "";
    //ee
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_session);
        final EditText input = new EditText(this);
        final EditText bought = new EditText(this);
        final EditText sold = new EditText(this);
        final TextView name = (TextView) findViewById(R.id.textName);
        final TextView boughtPrice = (TextView) findViewById(R.id.textBought);
        final TextView soldPrice = (TextView) findViewById(R.id.textSold);

        name.setText(getIntent().getStringExtra("nameExtra"));
        boughtPrice.setText(getIntent().getStringExtra("boughtExtra"));
        soldPrice.setText(getIntent().getStringExtra("soldExtra"));
        Button activity = (Button) findViewById(R.id.activity);
        Button entry =(Button) findViewById(R.id.addEntry);
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tradeSession.this, calculation.class);
                startActivity(intent);

            }
        });

    }
}
