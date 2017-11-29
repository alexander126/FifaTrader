package com.example.alex.fifatrader;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.view.ContextThemeWrapper;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Alex on 11/29/2017.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //BUTTON ABOUT
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        findPreference("key4");
        Preference key4 = (Preference) findPreference("key4");
        key4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                }
                return true;
            }
        });
        SharedPreferences prefs = getContext().getSharedPreferences("name", android.content.Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        final Preference key2 = (Preference) findPreference("key2");
        key2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                int theme;
                if (Build.VERSION.SDK_INT < 23) theme = AlertDialog.THEME_HOLO_DARK;
                else theme = android.R.style.Theme_Holo_Light;
                final EditText input = new EditText(getContext());
                ContextThemeWrapper wrapper = new ContextThemeWrapper(getContext(), theme);
                AlertDialog.Builder builder = new AlertDialog.Builder(wrapper);
                builder.setTitle("Enter the amount of coins: ");

                // Set up the input
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.remove("mymoney");
                        editor.putString("mymoney", input.getText().toString());
                        editor.commit();
                        Toast toast = Toast.makeText(getContext(), "Restart the application", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                });
                builder.show();
                return true;
            }
        });
        findPreference("key3");
        Preference key3 = (Preference) findPreference("key3");
        key3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent Intent = new Intent(getContext(), aboutActivity.class);
                startActivity(Intent);
                return true;
            }
        });
    }
}
