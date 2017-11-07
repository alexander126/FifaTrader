package com.example.alex.fifatrader;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaxExplained extends AppCompatActivity {

    private TextView textView;
    private StringBuilder text = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_explained);
        this.setFinishOnTouchOutside(true);

        TextView text = (TextView) findViewById(R.id.info);
        text.setText("In order to control the rapid rate at which some huge Ultimate Team traders/coin sellers seem to accumulate coins, EA has implemented a tax structure in FIFA 13 Ultimate Team.\n" +
                " \n" +
                "Some players suspect that it's just another way to stimulate more pack sales. 5% isn't a lot,You probably won't really even notice the taxes, unless you are a coin seller yourself.But it is important to be aware of Ultimate Team taxes for when you are involved in more significant trading numbers.\n" +
                " \n" +
                "EA takes 5% for any player-to-player trade. This includes cards of any kind, just anything you're selling or buying on the trade market. The taxes are deducted from the final sale price that is given to the seller. Theoretically, the seller gets taxed.");
    }
}
