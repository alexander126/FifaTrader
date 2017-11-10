package com.example.alex.fifatrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class calculation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFinishOnTouchOutside(false);

        final EditText name =(EditText) findViewById(R.id.name);
        final EditText bought =(EditText) findViewById(R.id.bought);
        final EditText sold =(EditText) findViewById(R.id.sold);
        Button confirm = (Button) findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (calculation.this,tradeSession.class);
                intent.putExtra("nameExtra", name.getText().toString());
                intent.putExtra("boughtExtra", bought.getText().toString());
                intent.putExtra("soldExtra", sold.getText().toString());
                startActivity(intent);
            }
        });
    }
}
