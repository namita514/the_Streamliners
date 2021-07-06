package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hellotoast.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   private ActivityMainBinding b;
   private int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
//to increase the count
    public void incCount(View view) {
        b.IncCount.setText(""+number++);
    }
//to show toast
    public void showToast(View view) {
        Toast.makeText(this,R.string.Toast_msg,Toast.LENGTH_SHORT).show();
    }
}