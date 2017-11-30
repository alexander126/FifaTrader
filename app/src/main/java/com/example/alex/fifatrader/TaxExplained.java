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
        text.setText("Ea wants to control the rapid rate at which some huge Ultimate Team traders/coin sellers seem to accumulate coins, that's why they have implemented a tax structure in FIFA Ultimate Team.\n" +
                " \n" +
                "Most of the players think that it's a way of stimulating people to buy more packs. 5% isn't a lot,Some of you won't even notice the tax, but big coins sellers do.It is important to be aware of Ultimate Team taxes for when you are involved in more significant trading numbers.\n" +
                " \n" +
                "EA takes 5% for any player-to-player trade. This includes cards of any kind, just anything you're selling or buying on the trade market. The taxes are deducted from the final sale price that is given to the seller. Theoretically, the seller gets taxed.");
    }
}
