package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.counterapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int qty=0;
    private ActivityMainBinding b;
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.counterapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(b.getRoot());

        // initialising share preferences
        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);

            qty=mPreferences.getInt("Quantity",0);
            b.iniText.setText(String.format("%s", qty));

        }
    //implementing onPause() lifecycle method.
    @Override
    protected void onPause(){
        super.onPause();

        // get an editor for shared preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt("Quantity",qty);
        preferencesEditor.apply();
    }
    //decrementing quantity
    public void decQty(View view) {
        qty++;
        b.iniText.setText(String.format("%s", qty));
    }
    //incrementing quantity
    public void incQty(View view) {
        qty--;
        b.iniText.setText(String.format("%s", qty));
    }






}
