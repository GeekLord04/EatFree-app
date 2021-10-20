package com.geekym.eatfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FoodOrder extends AppCompatActivity{
    Button swiggy,zomato,ubereats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order);

        swiggy = (Button) findViewById(R.id.swiggy);
        zomato = (Button) findViewById(R.id.zomato);
        ubereats = (Button) findViewById(R.id.ubereats);
    }

    public void fooduber(View view) {
        String url = "https://www.ubereats.com/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void foodzomato(View view) {
        String url2 = "https://www.zomato.com/";
        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        intent2.setData(Uri.parse(url2));
        startActivity(intent2);
    }

    public void foodswiggy(View view) {
        String url3 = "https://www.swiggy.com/";
        Intent intent3 = new Intent(Intent.ACTION_VIEW);
        intent3.setData(Uri.parse(url3));
        startActivity(intent3);
    }
}