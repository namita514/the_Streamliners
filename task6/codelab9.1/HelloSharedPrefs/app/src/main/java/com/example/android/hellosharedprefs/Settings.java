package com.example.android.hellosharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {
    Button saveBtn ,resetBtn;
    ToggleButton countToggle,colorToggle;
    // object for the shared preferences
    private SharedPreferences mPreferences;
    // string variable to hold the shared prefernces file
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    private int mCount = 0;
    // Current background color
    private int mColor;
    // Text view to display both count and color
    private TextView mShowCountTextView;

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        saveBtn=findViewById(R.id.savebtn);
        resetBtn=findViewById(R.id.resetBtn);
        countToggle=findViewById(R.id.countToggle);
        colorToggle=findViewById(R.id.colorToggle);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // The toggle is enabled
                            onPause();
                        } else {
                            // The toggle is disabled
                        }
                    }
                });
                colorToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // The toggle is enabled
                            onPause();
                        } else {
                            // The toggle is disabled
                        }
                    }
                });
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();

        // get an editor for the shared preference object
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        //put mCount and mColor to the shared preference object
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.apply();
    }

}