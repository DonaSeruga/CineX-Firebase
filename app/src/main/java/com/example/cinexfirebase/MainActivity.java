package com.example.cinexfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String FILM_TITLE = "filmtitle";
    public static final String FILM_ID = "filmid";

    ListView filmListView;
    DatabaseReference databaseFilm;
    ArrayList<Film> filmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#85083c")));


        databaseFilm = FirebaseDatabase.getInstance().getReference("films");
        filmListView = (ListView) findViewById(R.id.filmListView);
        filmList = new ArrayList<>();

//        String id = databaseFilm.push().getKey();
//        Film film = new Film(id, "Pulp Fiction", "23:40");
//        databaseFilm.child(id).setValue(film);

        filmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film film = filmList.get(position);
                Intent intent = new Intent(MainActivity.this, SeatSelectionActivity.class);

                intent.putExtra(FILM_ID,film.getID());
                intent.putExtra(FILM_TITLE,film.getTitle());

                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        filmList.clear();
        databaseFilm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot filmSnapshot : dataSnapshot.getChildren()){
                    Film film = filmSnapshot.getValue(Film.class);
                    filmList.add(film);
                }
                FilmList adapter = new FilmList(MainActivity.this,filmList);

                filmListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
