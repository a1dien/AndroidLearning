package com.example.javaoop;

import android.util.Log;

public class Cat {
    String name;
    int age;
    static int count;

    public Cat() {
        this.name = "John Doe";
        this.age = -1;
        count++;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        count++;
    }
    public void talk() {
        Log.i ("talk()", "Meow! My name is " + name + ", and I'm " + age + " years old");
    }
}
