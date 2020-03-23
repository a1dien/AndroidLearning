package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"tt@tt.tt"};
    String subject = "Your order from Car Shop";
    String bodyEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedIntent = getIntent();
        String userName = receivedIntent.getStringExtra("UserNameForOrder");
        String goodsName = receivedIntent.getStringExtra("goodsName");
        int quantity = receivedIntent.getIntExtra("quantity", 0);
        double price = receivedIntent.getDoubleExtra("orderPrice", 0);
        TextView orderUserName = findViewById(R.id.orderUserName);
        bodyEmail = "Customer name: " + userName + "\n" +
                "Goods name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + price/quantity + "\n" +
                "Order Price: " + price;
        orderUserName.setText(bodyEmail);

    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, bodyEmail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
