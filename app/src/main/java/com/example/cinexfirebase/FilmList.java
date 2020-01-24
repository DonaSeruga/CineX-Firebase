package com.example.cinexfirebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FilmList extends ArrayAdapter<Film> {

    private Activity context;
    private List<Film> filmList;

    public FilmList (Activity context, List<Film> filmList) {
        super(context,R.layout.list_layout,filmList);
        this.context = context;
        this.filmList = filmList;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewTime);
        Film film = filmList.get(position);

        textViewTitle.setText(film.getTitle());
        textViewTime.setText(film.getTime());
        return listViewItem;
    }
}
