package ru.myproject.practika1;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;


public class Fragment_1 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        recyclerView = view.findViewById(R.id.list_author);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String[] myAuthor =
                getResources().getStringArray(R.array.recycler_author);
        String[] myBook =  getResources().getStringArray(R.array.recycler_book);
        Integer[] myImage =  {
                R.drawable.image_1,
                R.drawable.image_2,
                R.drawable.image_3,
                R.drawable.image_4,
                R.drawable.image_5,
                R.drawable.image_6,
                R.drawable.image_7,
                R.drawable.image_8,
                R.drawable.image_9,
                R.drawable.image_10,
                R.drawable.image_11,
                R.drawable.image_12,
                R.drawable.image_13,
                R.drawable.image_14,
                R.drawable.image_15,
                R.drawable.image_16,
                R.drawable.image_17,
                R.drawable.image_18,
                R.drawable.image_19,
                R.drawable.image_20,
                R.drawable.image_21,
                R.drawable.image_22,
                R.drawable.image_23,
                R.drawable.image_24,
                R.drawable.image_25
        };
        List<String> recyclerAuthor = Arrays.asList(myAuthor);
        List<String> recyclerBook = Arrays.asList(myBook);

        adapter=new MyRecyclerViewAdapter(recyclerAuthor,recyclerBook,myImage);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}

