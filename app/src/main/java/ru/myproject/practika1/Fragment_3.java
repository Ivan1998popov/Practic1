package ru.myproject.practika1;



import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_3 extends Fragment {


   public  TextView textView_name_book, textView_genre, textView_author;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        textView_name_book =view.findViewById(R.id.name_book);
        textView_genre =view.findViewById(R.id.name_genre);
        textView_author =view.findViewById(R.id.name_author);

        Bundle bundle = getArguments();

        if (bundle != null) {
            textView_name_book.setText(bundle.getString("tag1"));
            textView_genre.setText(bundle.getString("tag2"));
            textView_author.setText(bundle.getString("tag3"));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
