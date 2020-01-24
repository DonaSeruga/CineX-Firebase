package com.example.cinexfirebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SeatList extends ArrayAdapter<Seats> {
    private Activity context;
    private List<Seats> seats;

    public SeatList (Activity context, List<Seats> seats) {
        super(context,R.layout.seat_selection,seats);
        this.context = context;
        this.seats = seats;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        Seats seat = seats.get(position);

        return listViewItem;
    }
}
