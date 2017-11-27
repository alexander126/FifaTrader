package com.example.alex.fifatrader;

import android.content.DialogInterface;
import android.content.SharedPreferences;
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
        final SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
        final SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        TextView tax = (TextView) findViewById(R.id.tax);
        TextView profit = (TextView) findViewById(R.id.profit);
        double totalProfit = 0;
        double totalTax = 0;
        String profitEntry = getIntent().getStringExtra("profit");
        String taxEntry = getIntent().getStringExtra("tax");
        Button activity = (Button) findViewById(R.id.activity);
        Button endSession = (Button) findViewById(R.id.endSession);


        tax.setText("Your session tax is: " + prefs.getString("finalTax","0"));
        profit.setText("Your session profit is: " + prefs.getString("finalProfit","0"));



        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tradeSession.this, calculation.class);
                startActivity(intent);

            }
        });
        endSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(tradeSession.this, "Your final session profit is: " + prefs.getString("finalProfit",null)+ "\n" + "Your final session tax is: " + prefs.getString("finalTax",null) + "\n" + "Your current session has ended",Toast.LENGTH_LONG);
                toast.show();
                double end = 0;
                double newProfit = Double.parseDouble(prefs.getString("mymoney",null))+Double.parseDouble(prefs.getString("finalProfit",null));
                editor.putString("finalProfit",Double.toString(end));
                editor.putString("finalTax",Double.toString(end));
                editor.putString("mymoney", Double.toString(newProfit));
                editor.commit();

            }
        });

    }
}
