package ru.myproject.practika1;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerViewAdapterSecond extends RecyclerView.Adapter<MyRecyclerViewAdapterSecond.MyViewHolder> {


    private String [] name_country;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name_country);

        }
    }

    public MyRecyclerViewAdapterSecond(String [] myData) {

        name_country=myData;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapterSecond.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view2_item, parent, false);
        MyRecyclerViewAdapterSecond.MyViewHolder vh = new MyRecyclerViewAdapterSecond.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapterSecond.MyViewHolder holder, int position) {
        holder.name.setText(name_country[position]);
    }

    @Override
    public int getItemCount() {
        return name_country.length;
    }
}
