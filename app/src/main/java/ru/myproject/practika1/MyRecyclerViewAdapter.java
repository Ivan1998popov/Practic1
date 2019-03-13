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

public class MyRecyclerViewAdapter extends
        RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<String> mDataAuthor;
    private List<String> mDataBook;
    private Integer[] mDataImage;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nameAuthor;
        public TextView nameBook;
        public ImageView imageBook;

        public MyViewHolder(View v) {
            super(v);
            nameAuthor = v.findViewById(R.id.my_text_view);
            nameBook = v.findViewById(R.id.name_item_book);
            imageBook = v.findViewById(R.id.image_book);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Click", "on item click");
                }
            });
        }
    }

    public MyRecyclerViewAdapter(List<String> myDataAuthor, List<String> myDatabook, Integer[] mDataImage) {
        mDataAuthor = myDataAuthor;
        mDataBook= myDatabook;
        this.mDataImage=mDataImage;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameAuthor.setText(mDataAuthor.get(position));
        holder.nameBook.setText(mDataBook.get(position));
        holder.imageBook.setImageResource(mDataImage[position]);
                if (holder.nameBook.getText().length()<7) {
                holder.nameBook.setTextColor(Color.RED);
        }else if(holder.nameBook.getText().length()>7&&holder.nameAuthor.getText().length()<10){
                holder.nameBook.setTextColor(Color.GREEN);
        }else if(holder.nameBook.getText().length()>10) {
            holder.nameBook.setTextColor(Color.BLUE);
        }
    }

    @Override
    public int getItemCount() {
        return mDataAuthor.size();
    }
}
