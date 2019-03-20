package ru.myproject.practika1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class MyParser extends AsyncTask<String, Integer, List<String>> {

    private final DBHelper dbHelper;
    private SQLiteDatabase db1;
    private List<String> name_book = new ArrayList<>();
    private List<String> genre = new ArrayList<>();
    private List<String> author = new ArrayList<>();
    public int id =0;
    @SuppressLint("StaticFieldLeak")
    private Context mContext;

    @SuppressLint("StaticFieldLeak")
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

   MyParser (Context context){
       dbHelper = new DBHelper(context);
       db1 = dbHelper.getReadableDatabase();
       mContext=context;
   }


    MyParser(Context context, RecyclerView recyclerView,int id) {
        this.recyclerView = recyclerView;
        adapter = recyclerView.getAdapter();
        dbHelper = new DBHelper(context);
        mContext=context;
        db1 = dbHelper.getReadableDatabase();
        this.id=id;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        HTTP_Handler http_handler = new HTTP_Handler();
        String url = "https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/Books1.json";
        if( isOnline(mContext)) {
            String jsonStr = http_handler.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {


                Gson gson = new Gson();
                List<JsonVersion2> jsonVersion2 = gson.fromJson(jsonStr,
                        new TypeToken<List<JsonVersion2>>() {
                        }.getType());

                dbHelper.deleteRecordsFromBook(db1);
                for (int i = 0; i < jsonVersion2.size(); i++) {

                    dbHelper.insertDataFromJson(jsonVersion2.get(i).getName(),
                            jsonVersion2.get(i).getGenre(),
                            jsonVersion2.get(i).getAuthor(), db1);

                }

                dbHelper.selectData(db1);

                recordInData();


            } else {
                Log.e(TAG, "Couldn't get json from server.");

            }
        }else{
            recordInData();
        }
        return null;
    }

    public void recordInData(){
        Cursor cursor = db1.rawQuery("Select name,genre,author from book", new String[]{});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            assert cursor != null;
            name_book.add(cursor.getString(0));
            genre.add(cursor.getString(1));
            author.add(cursor.getString(2));
        } while (cursor.moveToNext());
    }
    @Override
    protected void onPostExecute(List<String> strings) {
        if(id==1) {
            if (Fragment_1.flag == 0) {
                Fragment_1.flag = 1;
                adapter = new MyRecyclerViewAdapterSecond(name_book, genre, author);
                recyclerView.setAdapter(adapter);

            } else if (Fragment_1.flag == 1) {
                Fragment_1.flag = 0;
                Collections.reverse(name_book);
                Collections.reverse(genre);
                Collections.reverse(author);
                adapter = new MyRecyclerViewAdapterSecond(name_book, genre, author);
                recyclerView.setAdapter(adapter);
            }
        }

    }
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL url = new URL("http://www.google.com/");
                HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
                urlc.setRequestProperty("User-Agent", "test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1000); // mTimeout is in seconds
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                Log.i("warning", "Error checking internet connection", e);
                return false;
            }
        }

        return false;

    }

}
