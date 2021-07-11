package com.example.intentsplayground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.intentsplayground.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private int qty =0;
    private ActivityMainBinding b;
    private int minVal, maxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(b.getRoot());
        getInitialCount();
    }


    private void getInitialCount() {
        //get data from intent

        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;
        qty = bundle.getInt(Constants.INITIAL_COUNT_KEY, 0);
        minVal = bundle.getInt(Constants.MIN_VAL, Integer.MIN_VALUE);
        maxValue = bundle.getInt(Constants.MAX_VAL, Integer.MAX_VALUE);
        b.iniText.setText(String.valueOf(qty));
        if (qty != 0) {
            b.sendBack.setVisibility(View.VISIBLE);
        }

    }


    //decrementing quantity
    public void decQty(View view) {
        b.iniText.setText(""+ qty--);
    }
    //incrementing quantity
    public void incQty(View view) {
        b.iniText.setText(""+ qty++);
    }

    public void sendBack(View view) {
        //Validate count
        if(qty>=minVal&&qty<=maxValue){
            //send the data
            Intent intent=new Intent();
            intent.putExtra(Constants.FINAL_VALUE,qty);
            setResult(RESULT_OK,intent);
            //close the activity
            finish();
        }
        else{
            Toast.makeText(this,"Data invalid",Toast.LENGTH_SHORT).show();
        }
    }
}
