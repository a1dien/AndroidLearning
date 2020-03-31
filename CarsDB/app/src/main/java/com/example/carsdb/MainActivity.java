package com.example.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DataBaseHandler;
import Model.Car;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHandler handler = new DataBaseHandler(this);
        Log.d("DB_check", String.valueOf(handler.getCarsCount()));
/*        handler.addCar(new Car("Toyota", "30 000 $"));
        handler.addCar(new Car("Opel", "25 000 $"));
        handler.addCar(new Car("Mercedes", "50 000 $"));
        handler.addCar(new Car("KIA", "28 000 $"));

        List<Car> carList = handler.getAllCars();
        for (Car car : carList) {
            Log.d ("DB_check", "ID " + car.getId() + ", Name " + car.getName() + ", Price " + car.getPrice());
        }*/
        Car car = handler.getCar(1);
/*
        Log.d ("DB_check", "ID " + car.getId() + ", Name " + car.getName() + ", Price " + car.getPrice());
        car.setName("Tesla1");
        car.setPrice("200 000 $");
        handler.updateCar(car);
        Log.d ("DB_check", "ID " + car.getId() + ", Name " + car.getName() + ", Price " + car.getPrice());
*/
        //handler.deleteCar(car);
        //Log.d ("DB_check", "ID " + car.getId() + ", Name " + car.getName() + ", Price " + car.getPrice());


    }
}
