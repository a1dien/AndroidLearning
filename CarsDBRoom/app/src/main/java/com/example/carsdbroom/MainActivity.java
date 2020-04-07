package com.example.carsdbroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Data.CarsAppDataBase;
import Model.Car;

public class MainActivity extends AppCompatActivity {

    private CarsAdapter carsAdapter;
    private ArrayList<Car> carArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    //  private DatabaseHandler databaseHandler;
    private CarsAppDataBase carsAppDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        carsAppDataBase = Room.databaseBuilder(getApplicationContext(), CarsAppDataBase.class, "CarsDB")
                .build();

//        databaseHandler = new DatabaseHandler(this);

//        cars.addAll(databaseHandler.getAllCars());
        new GetAllCarsAsyncTask().execute();

        carsAdapter = new CarsAdapter(this, carArrayList, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(carsAdapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAndEditCars(false, null, -1);
            }
        });


    }

    public void addAndEditCars(final boolean isUpdate, final Car car, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.layout_add_car, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(view);

        TextView newCarTitle = view.findViewById(R.id.newCarTitle);
        final EditText nameEditText = view.findViewById(R.id.nameEditText);
        final EditText priceEditText = view.findViewById(R.id.priceEditText);

        newCarTitle.setText(!isUpdate ? "Add Car" : "Edit Car");

        if (isUpdate && car != null) {
            nameEditText.setText(car.getName());
            priceEditText.setText(car.getPrice());
        }

        alertDialogBuilderUserInput
                .setCancelable(true)
                .setPositiveButton(isUpdate ? "Update" : "Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(isUpdate ? "Delete" : "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isUpdate) {
                            deleteCar(car, position);
                        } else {
                            dialog.cancel();
                        }
                    }
                });
        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nameEditText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter car name!", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(priceEditText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter car price!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                if (isUpdate && car != null) {
                    updateCar(nameEditText.getText().toString(), priceEditText.getText().toString(), position);
                } else {
                    createCar(nameEditText.getText().toString(), priceEditText.getText().toString());
                }
            }
        });
    }

    private void createCar(String name, String price) {
        new CreateCarAsyncTask().execute(new Car(0, name, price));
    }

    private void deleteCar(Car car, int position) {
        carArrayList.remove(position);
        new DeleteCarAsyncTask().execute(car);
    }

    private void updateCar(String name, String price, int position) {
        Car car = carArrayList.get(position);
        car.setName(name);
        car.setPrice(price);

        new UpdateCarAsyncTask().execute(car);

        carArrayList.set(position, car);

        carsAdapter.notifyDataSetChanged();
    }


    private class GetAllCarsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            carArrayList.addAll(carsAppDataBase.getCarDAO().getAllCars());
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            carsAdapter.notifyDataSetChanged();

        }
    }
    private class CreateCarAsyncTask extends AsyncTask<Car, Void, Void> {

        @Override
        protected Void doInBackground(Car... cars) {
            long id = carsAppDataBase.getCarDAO().addCar(
                    cars[0]);

            Car car = carsAppDataBase.getCarDAO().getCar(id);

            if (car != null) {
                carArrayList.add(0, car);
                carsAdapter.notifyDataSetChanged();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            carsAdapter.notifyDataSetChanged();
        }
    }

    private class UpdateCarAsyncTask extends AsyncTask<Car, Void, Void> {

        @Override
        protected Void doInBackground(Car... cars) {
            carsAppDataBase.getCarDAO().updateCar(cars[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            carsAdapter.notifyDataSetChanged();

        }
    }

    private class DeleteCarAsyncTask extends AsyncTask<Car, Void, Void> {

        @Override
        protected Void doInBackground(Car... cars) {
            carsAppDataBase.getCarDAO().deleteCar(cars[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            carsAdapter.notifyDataSetChanged();
        }
    }
}
