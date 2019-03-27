package ru.myproject.practika1.adapters;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.myproject.practika1.R;
import ru.myproject.practika1.activities.FragmentActivity;
import ru.myproject.practika1.database.DBHelper;
import ru.myproject.practika1.fragments.Fragment_3;

public class MyRecyclerViewAdapterSecond extends RecyclerView.Adapter<MyRecyclerViewAdapterSecond.MyViewHolder> {


    private List <String> name_book;
    private List <String> genre;
    private List <String> author;


    public class MyViewHolder extends RecyclerView.ViewHolder  {

        public TextView name;
        public Button btn_open_book,btn_save_book;
        public AlertDialog alertDialog;

        private MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name_book);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setCancelable(true)
                            .setTitle(name_book.get(getAdapterPosition()))
                            .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i("AlertDialog", "on negative button click");
                        }
                    });

                    View view =
                            ((FragmentActivity)v.getContext()).getLayoutInflater().inflate(R.layout.alert_dialog_view,
                                    null);
                    builder.setView(view);
                    alertDialog =builder.create();
                    btn_open_book= view.findViewById(R.id.btn_open_book);
                    btn_save_book=view.findViewById(R.id.btn_save_book);
                    btn_save_book.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DBHelper dbHelper = new DBHelper(v.getContext());
                            SQLiteDatabase db1 = dbHelper.getReadableDatabase();
                            if(dbHelper.insertFavoriteBook(name_book.get(getAdapterPosition()),
                                    genre.get(getAdapterPosition()),
                                    author.get(getAdapterPosition()),db1)){
                                Toast.makeText(v.getContext(),"Книга добавлена в избранные!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(v.getContext(),"Эта книга уже есть в избранных!",
                                        Toast.LENGTH_LONG).show();
                            }


                            alertDialog.cancel();
                        }
                    });
                    btn_open_book.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentActivity activity = (FragmentActivity) v.getContext();
                            Bundle bundle = new Bundle();
                            bundle.putString("tag1", name_book.get(getAdapterPosition()));
                            bundle.putString("tag2", genre.get(getAdapterPosition()));
                            bundle.putString("tag3", author.get(getAdapterPosition()));

                            Fragment_3 frag3 = new Fragment_3();
                            frag3.setArguments(bundle);
                            activity.getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, frag3)
                                    .addToBackStack(null)
                                    .commit();
                            alertDialog.cancel();
                        }
                    });
                    alertDialog.show();
                    return false;
                }
            });

        }


    }

    public MyRecyclerViewAdapterSecond(List<String> name_book, List<String> genre, List<String> author) {

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
//        holder.name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FragmentActivity activity = (FragmentActivity) v.getContext();
//                Bundle bundle = new Bundle();
//                bundle.putString("tag1", name_book.get(holder.getAdapterPosition()));
//                bundle.putString("tag2", genre.get(holder.getAdapterPosition()));
//                bundle.putString("tag3", author.get(holder.getAdapterPosition()));
//
//                Fragment_3 frag3 = new Fragment_3();
//                frag3.setArguments(bundle);
//                activity.getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content_frame, frag3)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return name_book.size();
    }
}
