package com.example.javaoop;

import android.util.Log;

public class Puma extends Cat{
    private String pumaHelloText;
    public Puma() {
        this.name = name;
        this.age = 3;
        pumaHelloText = "I'm a cool cat.";
    }
    private String createPumaTalkText() {
        return pumaHelloText + " My name is " + name + " and I'm " + age + " years old";
    }

    @Override
    public void talk() {
        Log.i ("talk()", createPumaTalkText());
    }
}
