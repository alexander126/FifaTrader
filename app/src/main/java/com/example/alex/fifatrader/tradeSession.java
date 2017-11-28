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

                final AlertDialog.Builder builder = new AlertDialog.Builder(tradeSession.this);
                builder.setMessage("In the current session you have made " + prefs.getString("finalProfit","0")
                        + "and you have paid "+ prefs.getString("finalTax","0") + " in tax." +
                        "Do you want to end this session?")
                        .setTitle("End session");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double end = 0;
                        double newProfit = Double.parseDouble(prefs.getString("mymoney",null))+Double.parseDouble(prefs.getString("finalProfit",null));
                        editor.putString("finalProfit",Double.toString(end));
                        editor.putString("finalTax",Double.toString(end));
                        editor.putString("mymoney", Double.toString(newProfit));
                        editor.commit();
                        Intent intent = new Intent(tradeSession.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });
                builder.show();



            }
        });

    }
}
