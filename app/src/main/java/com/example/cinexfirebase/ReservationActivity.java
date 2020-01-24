package com.example.cinexfirebase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class ReservationActivity extends AppCompatActivity {
    TextView tvReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervation);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#85083c")));

        tvReservation = (TextView) findViewById(R.id.tvReservation);
        Intent intent = getIntent();
        String title = intent.getStringExtra("filmTitle");
        String seat = intent.getStringExtra("seatValue");
        tvReservation.setText("Rezervacija za sjedalo "+seat+" za film "+title+" je uspješno napravljena!");
    }
}
