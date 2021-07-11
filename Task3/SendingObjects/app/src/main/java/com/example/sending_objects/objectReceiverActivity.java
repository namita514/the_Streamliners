package com.example.sending_objects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.sending_objects.Modals.Student;
import com.example.sending_objects.databinding.ActivityMainBinding;
import com.example.sending_objects.databinding.ActivityObjectReceiverBinding;

public class objectReceiverActivity extends AppCompatActivity {
    ActivityObjectReceiverBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b= ActivityObjectReceiverBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        getStudentDetails();
    }

    private void getStudentDetails() {
        Intent intent=getIntent();
        Student student = (Student) intent.getSerializableExtra(Constants.studentObj);
        b.eneteredPhone.setText(student.getMobile_No());
        b.eneteredRollno.setText(student.getRoll_No());
        b.enteredGender.setText(student.getGender());
        b.enteredName.setText(student.getName());
    }

}