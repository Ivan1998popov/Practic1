package ru.myproject.practika1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerViewAdapterSecond extends RecyclerView.Adapter<MyRecyclerViewAdapterSecond.MyViewHolder> {


    private List <String> name_book;
    private List <String> genre;
    private List <String> author;


    public static class MyViewHolder extends RecyclerView.ViewHolder  {

        public TextView name;


        private MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name_country);
        }


    }

    protected MyRecyclerViewAdapterSecond(List<String> name_book, List<String> genre, List<String> author) {

        this.name_book =name_book;
        this.genre =genre;
        this.author =author;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapterSecond.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view2_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerViewAdapterSecond.MyViewHolder holder,
                                 int position) {
        holder.name.setText(name_book.get(position));
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentActivity activity = (FragmentActivity) v.getContext();
                Bundle bundle = new Bundle();
                bundle.putString("tag1", name_book.get(holder.getAdapterPosition()));
                bundle.putString("tag2", genre.get(holder.getAdapterPosition()));
                bundle.putString("tag3", author.get(holder.getAdapterPosition()));

                Fragment_3 frag3 = new Fragment_3();
                frag3.setArguments(bundle);
                activity.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, frag3)
                        .addToBackStack(null)
                        .commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return name_book.size();
    }
}
