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

    String m_Text = "";
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


        Button entry =(Button) findViewById(R.id.addEntry);

        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(tradeSession.this);
                builder.setTitle("Title");
                //LinearLayout
                LinearLayout layout = new LinearLayout(tradeSession.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                input.setHint("name");
                layout.addView(input);

                bought.setHint("bought");

                layout.addView(bought);

                sold.setHint("sold");
                layout.addView(sold);
                builder.setView(layout);

             //   input.setInputType(InputType.TYPE_CLASS_TEXT);
             //   builder.setView(input);
                //TODO: FIX THESE
             //   bought.setInputType(InputType.TYPE_CLASS_NUMBER);
             ////   builder.setView(bought);
              //  sold.setInputType(InputType.TYPE_CLASS_NUMBER);
              //  builder.setView(sold);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        name.setText(m_Text);
                        boughtPrice.setHint(bought.getText().toString());
                        soldPrice.setText(sold.getText().toString());

                        Toast.makeText(tradeSession.this, m_Text, Toast.LENGTH_SHORT).show();

                        finish();
                        startActivity(getIntent());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

    }
}
