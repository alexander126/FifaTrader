package com.example.alex.fifatrader;

import android.content.DialogInterface;
import android.renderscript.Double2;
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


    //ee
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_session);

        TextView tax = (TextView) findViewById(R.id.tax);
        TextView profit = (TextView) findViewById(R.id.profit);
        double totalProfit = 0;
        double totalTax = 0;
        String profitEntry = getIntent().getStringExtra("profit");
        String taxEntry = getIntent().getStringExtra("tax");
        Button activity = (Button) findViewById(R.id.activity);


        tax.setText(profitEntry);
        profit.setText(taxEntry);


        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tradeSession.this, calculation.class);
                startActivity(intent);

            }
        });

    }
}
