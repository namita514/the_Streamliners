package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.counterapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int qty=0;
    private ActivityMainBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(b.getRoot());
    }
    //decrementing quantity
    public void decQty(View view) {
        b.iniText.setText(""+ qty--);
    }
    //incrementing quantity
    public void incQty(View view) {
        b.iniText.setText(""+ qty++);
    }
}