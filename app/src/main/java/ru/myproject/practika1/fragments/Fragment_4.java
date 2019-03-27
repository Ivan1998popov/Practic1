package ru.myproject.practika1.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.myproject.practika1.R;

public class Fragment_4 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
