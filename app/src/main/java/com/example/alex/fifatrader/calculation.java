package com.example.alex.fifatrader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class calculation extends Activity {
    double finalProfit = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calculation);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFinishOnTouchOutside(false);

        final EditText name =(EditText) findViewById(R.id.name);

        final EditText buyingPrice = (EditText) findViewById(R.id.bought);
        final EditText sellingPrice = (EditText) findViewById(R.id.sold);
        Button confirm = (Button) findViewById(R.id.confirm);
        final SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        final SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buyingPrice.length()==0 || sellingPrice.length()==0){
                    Toast.makeText(calculation.this, "This place can not be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                        double price = Double.parseDouble(buyingPrice.getText().toString());
                        double selling = Double.parseDouble(sellingPrice.getText().toString());
                        double temp = selling * 5 / 100;
                        double profit = (selling - temp) - price;

                        finalProfit = Double.parseDouble(prefs.getString("finalProfit",null)) + Double.parseDouble(prefs.getString("profit", null));
                        editor.putString("profit", Double.toString(profit));
                        editor.putString("finalProfit", Double.toString(finalProfit));
                        editor.commit();


                        String profitstring = prefs.getString("finalProfit", null);
                        String taxstring = Double.toString(temp);

                        Intent intent = new Intent(calculation.this, tradeSession.class);
                        intent.putExtra("profit", profitstring);
                        intent.putExtra("tax", taxstring);

                        String profitCalculation = prefs.getString("finalProfit", null);
                        startActivity(intent);
                        Toast toast = Toast.makeText(calculation.this, profitCalculation, LENGTH_LONG);
                        toast.show();

                }

            }
        });

    }
}
