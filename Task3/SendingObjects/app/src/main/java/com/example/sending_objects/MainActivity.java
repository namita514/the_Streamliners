package com.example.sending_objects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.sending_objects.Modals.Student;
import com.example.sending_objects.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing viewBinding
        b=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setOnClickListener();
        setupHideErrorForEditText();
    }
    //set up text watcher
    private void setupHideErrorForEditText() {
        TextWatcher textWatcher=new TextWatcher() {
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
        b.stuPhone.getEditText().addTextChangedListener(textWatcher);
        b.stuName.getEditText().addTextChangedListener(textWatcher);
        b.rollNo.getEditText().addTextChangedListener(textWatcher);
    }

    //to handle the event
    private void setOnClickListener() {
        b.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendData();
            }


        });
    }
    private Student getInfo(){

        String Gender;
        //store student name
        String Name=b.stuName.getEditText().getText().toString().trim();
        //check if the string is empty
        if(Name.isEmpty()){
            b.stuName.setError("enter your name");
            return null;
        }
        // store student roll no.
        String Roll_no=b.rollNo.getEditText().getText().toString().trim();
        //check if the roll no is not entered
          if(Roll_no.isEmpty()){
              b.rollNo.setError("enter your Roll no.");
          }
          else if(Roll_no.matches("^\\d{2}[a-zA-Z]*\\d{3}")){
              b.rollNo.setError("enter roll no in proper format");
              return null;
          }
        //store student phone no.
        String Phone=b.stuPhone.getEditText().getText().toString().trim();
          //check if the phone no is entered
        if(Phone.isEmpty()){
            b.stuPhone.setError("Enter Phone NO.");
        }
        else if(!Phone.matches("^\\d{10}")){
            b.stuPhone.setError("enter valid phone no.");
        }
        //get and store student gender

        int type=b.gender.getCheckedRadioButtonId();
        if(type==R.id.female){
         Gender="Female";}
        else if(type==R.id.male){
            Gender="Male";}
        else{
            Toast.makeText(this,"please select gender",Toast.LENGTH_SHORT).show();
            return null;
        }
        Student student=new Student(Name,Gender,Roll_no,Phone);
        return student;

    }
    //to send the data to receiver activity
    private void sendData() {
        Student obj=getInfo();
        Intent intent=new Intent(this,objectReceiverActivity.class);
        intent.putExtra(Constants.studentObj,obj);
        startActivity(intent);
    }

    private void hideError(){
        b.rollNo.setError(null);
        b.stuName.setError(null);
        b.stuPhone.setError(null);
    }
}
