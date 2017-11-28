package com.example.alex.fifatrader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.view.ContextThemeWrapper;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


import static com.example.alex.fifatrader.R.layout.activity_calculator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);
        //Samle AdMob app ID:ca-app-pub-8629737007792498~2503304919
        //TODO: fix the appearance
        MobileAds.initialize(this, "ca-app-pub-8629737007792498/5456771314");
        cnt=this;
        int theme = android.R.style.Theme_Holo_Light_Dialog;
        final EditText input = new EditText(this);
        final SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
        if(isFirstTime()){
            ContextThemeWrapper wrapper = new ContextThemeWrapper(cnt,theme);
            AlertDialog.Builder builder = new AlertDialog.Builder(wrapper);
            builder.setTitle("Enter your current money");

        //set up the input
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            //set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    editor.putString("mymoney", input.getText().toString());
                    editor.commit();
                }
            });
            builder.show();
        }
        Button text1 = (Button) findViewById(R.id.trade);
        Button bn1 = (Button) findViewById(R.id.next);
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calc_activity = new Intent(MainActivity.this, calculator.class);
                MainActivity.this.startActivity(calc_activity);
            }
        });
        text1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent trade_session = new Intent(MainActivity.this, tradeSession.class);
                startActivity(trade_session);
            }
        });

        //test for sharedprefs
        String yourmoney = prefs.getString("mymoney", "");
        TextView txt1 = (TextView)findViewById(R.id.txt1);
        txt1.setText("You have " + prefs.getString("mymoney","0"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
        }
        return !ranBefore;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.trade_session) {
            Intent intent = new Intent(MainActivity.this,tradeSession.class);
            startActivity(intent);
        } else if (id == R.id.calculator) {
            Intent intent = new Intent(MainActivity.this,calculator.class);
            startActivity(intent);

        } else if (id == R.id.taxeplain){
            Intent intent = new Intent(MainActivity.this,TaxExplained.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
