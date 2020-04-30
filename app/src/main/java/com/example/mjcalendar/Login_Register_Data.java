package com.example.mjcalendar;

import android.widget.EditText;

public class Login_Register_Data {
    private String name;

    public Login_Register_Data(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
