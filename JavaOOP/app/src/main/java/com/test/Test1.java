package com.test;

import android.util.Log;

public class Test1 {
    public int intField;
    public String stringField;

    public Test1() {

    }

    public Test1(int intField) {
        this.intField = intField;
    }

    public Test1(int intField, String stringField) {
        this.intField = intField;
        this.stringField = stringField;
    }

    public void method() {
        Log.i ("Overload", "call empthy method");
    }
    public void method(int intField) {
        Log.i ("Overload", "call method with 1 field");
    }
    public void method(int intField, String stringField) {
        Log.i ("Overload", "call method with 2 field");
    }
}
