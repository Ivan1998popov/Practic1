package ru.myproject.practika1.fragments;


import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ru.myproject.practika1.adapters.AdapterForAddBooks;
import ru.myproject.practika1.R;
import ru.myproject.practika1.database.DBHelper;

public class Fragment_2 extends Fragment {



    View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_2, container, false);


        DBHelper dbHelper = new DBHelper(view.getContext());
        SQLiteDatabase db1 = dbHelper.getReadableDatabase();
        Cursor cursor =db1.rawQuery("Select name,genre,author from favorite where id_user=?"
                , new String[]{String.valueOf(DBHelper.id_user)});

        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new AdapterForAddBooks( dbHelper.selectData(cursor));
        recyclerView.setAdapter(adapter);



        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}