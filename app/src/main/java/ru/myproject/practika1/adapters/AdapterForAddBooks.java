package ru.myproject.practika1.adapters;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.myproject.practika1.R;
import ru.myproject.practika1.activities.FragmentActivity;
import ru.myproject.practika1.database.DBHelper;
import ru.myproject.practika1.fragments.Fragment_3;

public class AdapterForAddBooks extends RecyclerView.Adapter<AdapterForAddBooks.MyViewHolder> {

    private List<String> name_book;


    public AdapterForAddBooks(List<String> name_book) {
        this.name_book = name_book;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_book);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbHelper = new DBHelper(v.getContext());
                    SQLiteDatabase db1 = dbHelper.getReadableDatabase();
                    String name ,author,genre;
                    Cursor cursor= db1.rawQuery("Select name,author,genre from favorite where name=?",
                            new String[]{name_book.get(getAdapterPosition())});
                    cursor.moveToFirst();
                    do{
                         name =cursor.getString(0);
                         author=cursor.getString(1);
                         genre =cursor.getString(2);
                    }while (cursor.moveToNext());

                    FragmentActivity activity = (FragmentActivity) v.getContext();
                    Bundle bundle = new Bundle();
                    bundle.putString("tag1", name);
                    bundle.putString("tag2", genre);
                    bundle.putString("tag3", author);

                    Fragment_3 frag3 = new Fragment_3();
                    frag3.setArguments(bundle);
                    activity.getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, frag3)
                            .addToBackStack(null)
                            .commit();

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setCancelable(true)
                            .setTitle(name_book.get(getAdapterPosition()))
                            .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i("AlertDialog", "on negative button click");
                                }
                            })
                            .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DBHelper dbHelper = new DBHelper(v.getContext());
                                    SQLiteDatabase db1 = dbHelper.getReadableDatabase();
                                    db1.delete("favorite","name=?",
                                            new String[]{name_book.get(getAdapterPosition())});
                                    name_book.remove(getAdapterPosition());
                                    notifyItemRangeChanged(getAdapterPosition(),name_book.size());
                                    notifyDataSetChanged();
                                    Toast.makeText(v.getContext(),"Выбранная книга удалена!",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                    AlertDialog alertDialog =builder.create();
                    alertDialog.show();
                    return false;
                }
            });

        }
    }


    @NonNull
    @Override
    public AdapterForAddBooks.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view2_item, viewGroup, false);
        return new AdapterForAddBooks.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForAddBooks.MyViewHolder holder, int position) {
        holder.name.setText(name_book.get(position));
    }


    @Override
    public int getItemCount() {
        return name_book.size();
    }


}
