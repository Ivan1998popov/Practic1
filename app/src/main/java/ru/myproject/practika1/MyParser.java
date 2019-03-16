package ru.myproject.practika1;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class MyParser extends AsyncTask<String, Integer, List<String>> {

    private List<String> name_book = new ArrayList<>();
    private List<String> genre = new ArrayList<>();
    private List<String> author = new ArrayList<>();


    @SuppressLint("StaticFieldLeak")
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private int flag;


    MyParser(RecyclerView recyclerView, int flag) {
        this.recyclerView = recyclerView;
        adapter = recyclerView.getAdapter();
        this.flag = flag;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        HTTP_Handler http_handler = new HTTP_Handler();
        String url = "https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/Books1.json";
        String jsonStr = http_handler.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {


            Gson gson = new Gson();
            List<JsonVersion2> jsonVersion2 = gson.fromJson(jsonStr,
                    new TypeToken<List<JsonVersion2>>() {
                    }.getType());

            for (int i = 0; i < jsonVersion2.size(); i++) {

                name_book.add(jsonVersion2.get(i).getName());
                genre.add(jsonVersion2.get(i).getGenre());
                author.add(jsonVersion2.get(i).getAuthor());

            }


        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }
        return null;
    }


    @Override
    protected void onPostExecute(List<String> strings) {

        if (flag == 0) {
            adapter = new MyRecyclerViewAdapterSecond(name_book, genre, author);
            recyclerView.setAdapter(adapter);
        } else {
            Collections.reverse(name_book);
            Collections.reverse(genre);
            Collections.reverse(author);
            adapter = new MyRecyclerViewAdapterSecond(name_book, genre, author);
            recyclerView.setAdapter(adapter);
        }

    }

}
