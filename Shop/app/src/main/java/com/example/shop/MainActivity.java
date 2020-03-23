package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goods;
    double price;
    String goodName;

    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSpinner();
        setGoods();
        userNameEditText = findViewById(R.id.yourName);
    }

    void setSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Bugatti");
        spinnerArrayList.add("Bentley");
        spinnerArrayList.add("Ferrary");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    void setGoods() {
        goods = new HashMap();
        goods.put("Bugatti", 1000.0);
        goods.put("Bentley", 5000.0);
        goods.put("Ferrary", 3000.0);
    }
    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView increaseQuantity = findViewById(R.id.Quantity);
        increaseQuantity.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.textViewPrice);
        priceTextView.setText("" + quantity * price);
    }
    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView decreaseQuantity = findViewById(R.id.Quantity);
        decreaseQuantity.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.textViewPrice);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodName = spinner.getSelectedItem().toString();
        price = (double)goods.get(goodName);
        TextView priceTextView = findViewById(R.id.textViewPrice);
        priceTextView.setText("" + quantity * price);

        ImageView goodPicture = findViewById(R.id.imageViewGoods);
        if (goodName.equals("Bugatti")) {
            goodPicture.setImageResource(R.drawable.car);
        } else if (goodName.equals("Ferrary")) {
            goodPicture.setImageResource(R.drawable.ferrari);
        }  else if (goodName.equals("Bentley")) {
            goodPicture.setImageResource(R.drawable.bentley);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        Log.d("UserName", order.userName);
        order.goodsName = goodName;
        order.quantity = quantity;
        order.orderPrice = price * quantity;
        //Log.d("Order", order.userName + " " + order.goodsName + " " + order.quantity + " " + order.orderPrice);
        Intent intentOrder = new Intent(MainActivity.this, OrderActivity.class);
        intentOrder.putExtra("UserNameForOrder", order.userName);
        intentOrder.putExtra("goodsName", order.goodsName);
        intentOrder.putExtra("quantity", order.quantity);
        intentOrder.putExtra("Price", order.orderPrice);
        intentOrder.putExtra("orderPrice", order.orderPrice);
        startActivity(intentOrder);
    }
}
