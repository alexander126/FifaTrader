package com.example.alex.fifatrader;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.renderscript.Double2;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

public class tradeSession extends AppCompatActivity {
    public double END = 0;
    private InterstitialAd mInterstitialAd;
    //ee
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_session);
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
        final SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        TextView name = (TextView) findViewById(R.id.footballName);
        TextView tax = (TextView) findViewById(R.id.tax);
        TextView profit = (TextView) findViewById(R.id.profit);
        TextView currentTax = (TextView) findViewById(R.id.currentTax);
        TextView currentProfit = (TextView) findViewById(R.id.currentProfit);
        ImageButton activity = (ImageButton) findViewById(R.id.activity);
        ImageButton endSession = (ImageButton) findViewById(R.id.endSession);

        name.setText("Your last traded item is: " + prefs.getString("footballName", "-"));
        currentTax.setText("Your last trade tax is: " + prefs.getString("tax", "0"));
        currentProfit.setText("Your last trade profit is: " + prefs.getString("profit", "0"));

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
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                        }
                    }
                });

                builder.show();
            }
        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                double end = 0;
                double newProfit = Double.parseDouble(prefs.getString("mymoney",null))+Double.parseDouble(prefs.getString("finalProfit",null));
                editor.putString("finalProfit",Double.toString(end));
                editor.putString("finalTax",Double.toString(end));
                editor.putString("mymoney", Double.toString(newProfit));
                editor.commit();
                Intent intent = new Intent(tradeSession.this, MainActivity.class);
                startActivity(intent);
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
                double end = 0;
                double newProfit = Double.parseDouble(prefs.getString("mymoney",null))+Double.parseDouble(prefs.getString("finalProfit",null));
                editor.putString("finalProfit",Double.toString(END));
                editor.putString("finalTax",Double.toString(END));
                editor.putString("footballName","-");
                editor.putString("tax", Double.toString(END));
                editor.putString("profit",Double.toString(END));
                editor.putString("mymoney", Double.toString(newProfit));
                editor.commit();
                Intent intent = new Intent(tradeSession.this, MainActivity.class);
                startActivity(intent);
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                // Code to be executed when when the interstitial ad is closed.
            }
        });

    }
}
