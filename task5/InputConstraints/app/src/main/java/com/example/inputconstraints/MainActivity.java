package com.example.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.inputconstraints.databinding.ActivityInputBinding;
import com.example.inputconstraints.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   ActivityMainBinding b;
    private int requestCode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialisation of viewBinding
        b=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setUpErrorHideListener();
    }
 // hiding error when at-least one of the checkbox get selected
    private void setUpErrorHideListener() {
        CompoundButton.OnCheckedChangeListener changeListener= new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             b.resultText.setVisibility(View.GONE);
            }

        };
        b.condition1.setOnCheckedChangeListener(changeListener);
        b.condition2.setOnCheckedChangeListener(changeListener);
        b.condition3.setOnCheckedChangeListener(changeListener);
        b.condition4.setOnCheckedChangeListener(changeListener);
        b.condition5.setOnCheckedChangeListener(changeListener);

    }
    // handling the event on the basis on constraints selected
    public void onCheckboxClicked(View view) {
        int type[]={0,0,0,0,0};
        if(!(b.condition1.isChecked()||b.condition2.isChecked()||b.condition3.isChecked()||b.condition4.isChecked()||b.condition5.isChecked())){
            b.resultText.setVisibility(View.VISIBLE);
            b.resultText.setText("Select at least one condition");
            return;
        }
        // to verify which constraint is selected
        if(b.condition1.isChecked()){
            type[0]=1;
        }
        if(b.condition2.isChecked()){
            type[1]=1;
        }
        if(b.condition3.isChecked()){
            type[2]=1;
        }
        if(b.condition4.isChecked()){
            type[3]=1;
        }
        if(b.condition5.isChecked()){
            type[4]=1;
        }
        // to send the data to the inputActivity
        Intent intent=new Intent(this,inputActivity.class);
        intent.putExtra(Constants.inputType,type);
        startActivityForResult(intent,requestCode);
    }
     // to display  the result as per selected by the user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==requestCode&&resultCode==RESULT_OK){
            String resultText=data.getStringExtra(Constants.final_string);
            b.resultText.setText(resultText);
        }
        else{
            b.resultText.setText("There is no input");
        }
        b.resultText.setVisibility(View.VISIBLE);
    }
}