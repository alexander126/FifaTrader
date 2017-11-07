package com.example.alex.fifatrader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        final SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        String yourmoney = prefs.getString("mymoney", "");

        final EditText buyingPrice = (EditText) findViewById(R.id.buyingPrice);
        final EditText sellingPrice = (EditText) findViewById(R.id.sellingPrice);
        final TextView finalProfit = (TextView) findViewById(R.id.finalProfit);
        Button calculate = (Button) findViewById(R.id.calculate);

       final String buyPrice = buyingPrice.getText().toString();
       final String sellPrice = sellingPrice.getText().toString();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int bPrice = Integer.valueOf(buyPrice);
                int sPrice = Integer.valueOf(sellPrice);
                int profit = sPrice - bPrice - (sPrice*5/100);
                finalProfit.setText(profit);
                int asd=0;
            }
        });



    }
}
