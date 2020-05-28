package com.example.mjcalendar;

public class Login_Register_Data {
    private String name;
    private String image;

    public Login_Register_Data(String name,String image) {
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getImage(){ return this.image;}

    public void setImage(String image){
        this.image = image;
    }
}
