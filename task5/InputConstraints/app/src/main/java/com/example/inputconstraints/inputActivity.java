package com.example.inputconstraints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.inputconstraints.databinding.ActivityInputBinding;

public class inputActivity extends AppCompatActivity {
ActivityInputBinding b;
private  StringBuilder regex=new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        getConstraints();
        setOnClickListener();
        setUpErrorHideListener();
    }
// hiding error when the text get changed
    private void setUpErrorHideListener() {
        TextWatcher myTextWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hideError();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        b.data.getEditText().addTextChangedListener(myTextWatcher);

    }

// setting up event handler
    private void setOnClickListener() {
        b.sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOutput();
            }
        });
    }
// to send the final result to main Activity
    private void sendOutput() {
        String data=b.data.getEditText().getText().toString().trim();
        if(data.isEmpty()){
            b.data.setError("Please enter something");
            return;
        }
        if(!(data.matches(regex.toString()))){
            b.data.setError("Please enter valid input");
            return;
        }
     Intent intent=new Intent();
        intent.putExtra(Constants.final_string,data);
        setResult(RESULT_OK,intent);
        finish();
    }
// to recieve the constraints selected by the user and send by the mainActivity
    private void getConstraints() {
        Intent intent= getIntent();
        int type[]=intent.getIntArrayExtra(Constants.inputType);
        regex.append("^[");
        if (type[0] == 1) {
            regex.append("A-Z");
        }
        if (type[1] == 1) {
            regex.append("a-z");
        }
        if (type[2] == 1) {
            regex.append("0-9");
        }
        if (type[3] == 1) {
            regex.append("+\\-*/%");
        }
        if (type[4] == 1) {
            regex.append("@#$&!*");
        }
        regex.append("]*");

    }
    //utils
    private  void hideError(){
        b.data.setError(null);
    }
}