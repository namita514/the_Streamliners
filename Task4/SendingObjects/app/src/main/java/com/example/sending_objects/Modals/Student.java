package com.example.sending_objects.Modals;

import java.io.Serializable;

public class Student implements Serializable {
    private String Name,Gender,Roll_No,Mobile_No;

    public Student(String name, String gender, String roll_No, String mobile_No) {
        Name = name;
        Gender = gender;
        Roll_No = roll_No;
        Mobile_No = mobile_No;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getRoll_No() {
        return Roll_No;
    }

    public String getMobile_No() {
        return Mobile_No;
    }
}
