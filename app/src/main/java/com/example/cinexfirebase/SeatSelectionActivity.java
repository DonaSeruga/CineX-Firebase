package com.example.cinexfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatSelectionActivity extends AppCompatActivity {

    DatabaseReference databaseSeats;
    Spinner spinnerSeats;
    List<Seats> seats;
    TextView tvTekst;
    Button btnReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_selection);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#85083c")));

        spinnerSeats=(Spinner) findViewById(R.id.spinner);
        seats = new ArrayList<>();
        tvTekst = (TextView) findViewById(R.id.textView);
        btnReservation = (Button) findViewById(R.id.btnReservation);
        Intent intent = getIntent();
        final String id = intent.getStringExtra(MainActivity.FILM_ID);
        final String title = intent.getStringExtra(MainActivity.FILM_TITLE);
        tvTekst.setText("Odaberite sjedalo za film "+title);
        databaseSeats = FirebaseDatabase.getInstance().getReference("seats").child(id);

//        for(char letter='A'; letter <='F'; letter++){
//            for(int i=1;i<=10;i++){
//                    String seatMark = ""+letter+i;
//                    String ID = databaseSeats.push().getKey();
//
//                    Seats seat = new Seats(ID, seatMark, false);

//                    databaseSeats.child(ID).setValue(seat);
//            }
//        }

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String seat = spinnerSeats.getSelectedItem().toString();
                Query query = databaseSeats.orderByChild("seatValue").equalTo(seat);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            String idSeat=ds.child("id").getValue(String.class);
                            databaseSeats.child(idSeat).child("isTaken").setValue(true);

                            Intent intent = new Intent(SeatSelectionActivity.this, ReservationActivity.class);
                            intent.putExtra("filmTitle",title);
                            intent.putExtra("seatValue",seat);

                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    protected void onStart() {
        super.onStart();
        databaseSeats.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> seats = new ArrayList<String>();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()){
                    String seatValue = areaSnapshot.child("seatValue").getValue(String.class);
                    Boolean isTaken= areaSnapshot.child("isTaken").getValue(Boolean.class);
                    if(!isTaken){
                        seats.add(seatValue);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (SeatSelectionActivity.this,android.R.layout.simple_spinner_item,seats);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSeats.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
