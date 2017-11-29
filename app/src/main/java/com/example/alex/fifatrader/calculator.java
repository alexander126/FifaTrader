package com.example.alex.fifatrader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class calculator extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8629737007792498/1504697791");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        MobileAds.initialize(this, "ca-app-pub-8629737007792498/3768555595");

        final SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        String yourmoney = prefs.getString("mymoney", "");

        final EditText buyingPrice = (EditText) findViewById(R.id.buyingPrice);
        final EditText sellingPrice = (EditText) findViewById(R.id.sellingPrice);
        final TextView finalProfit = (TextView) findViewById(R.id.finalProfit);
        ImageButton calculate = (ImageButton) findViewById(R.id.calculate);
        ImageButton info = (ImageButton) findViewById(R.id.info);

       final String buyPrice = buyingPrice.getText().toString();
       final String sellPrice = sellingPrice.getText().toString();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(buyingPrice.length()==0 || sellingPrice.length()==0){
                    Toast.makeText(calculator.this, "This place can not be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    double price = Double.parseDouble(buyingPrice.getText().toString());
                    double selling = Double.parseDouble(sellingPrice.getText().toString());
                    double temp = selling * 5 / 100;
                    double profit = (selling - temp) - price;
                    String profitstring = Double.toString(profit);
                    finalProfit.setText(profitstring);
                    ImageView coins = (ImageView) findViewById(R.id.coins);
                    coins.setImageResource(R.drawable.fifacoins);
                }


            }
        });
        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Intent intent = new Intent(calculator.this, TaxExplained.class);
                startActivity(intent);
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Intent intent = new Intent(calculator.this, TaxExplained.class);
                startActivity(intent);
            }
        });
    }
}
