package com.example.alex.fifatrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class aboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0.1");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .addItem(versionElement)
                .setImage(R.drawable.adsoft)
                .setDescription("Fifa Trader application developed by ADev Software")
                .addGroup("Contact us")
                .addEmail("aboutadgroup@gmail.com")
                .create();

        setContentView(aboutPage);
    }
}
