package ru.myproject.practika1;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class MyParser extends AsyncTask<Void, Void, Void> {

    @SuppressLint("StaticFieldLeak")
    View view;

    MyParser(View view){
        this.view=view;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(view.getContext(),"Json Data is downloading" ,Toast.LENGTH_LONG).show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HTTP_Handler http_handler=new HTTP_Handler();
        String url = "https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/Books.json";
        String jsonStr = http_handler.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {



                Gson gson= new Gson();
                List<JsonVersion2> jsonVersion2 =gson.fromJson(jsonStr,
                        new TypeToken<List<JsonVersion2>>(){}.getType());

            for (int i = 0; i <jsonVersion2.size() ; i++) {

                Log.d("msg",jsonVersion2.get(i).toString());
                System.out.println(jsonVersion2.get(i).toString());

            }

//            JSONArray jsonArray= null;
//            try {
//                jsonArray = new JSONArray(jsonStr);
//
//
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject c = jsonArray.getJSONObject(i);
//
//
//
//
//
//                   Log.d("mymsg", "AtmosphericPressure :"+c.getString("A"));
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        return null;
    }

}
