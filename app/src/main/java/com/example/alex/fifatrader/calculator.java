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
        Button info = (Button) findViewById(R.id.info);

       final String buyPrice = buyingPrice.getText().toString();
       final String sellPrice = sellingPrice.getText().toString();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double price = Double.parseDouble(buyingPrice.getText().toString());
                double selling = Double.parseDouble(sellingPrice.getText().toString());
                double temp = selling *5/100;
                double profit = (selling - temp) - price;
                String profitstring = Double.toString(profit);
                finalProfit.setText(profitstring);


            }
        });
        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(calculator.this, TaxExplained.class);
                startActivity(intent);
            }
        });



    }
}
