package ru.myproject.practika1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.myproject.practika1.R;

public class SecondActivity extends AppCompatActivity {

    ImageView myImage;
    public static int image;
    int []ID =new int[]{R.drawable.bmwm2,R.drawable.gelandewagen,R.drawable.gelic2};
    TextView get_text;
    private static String KEY_IMAGE="key_image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        get_text=findViewById(R.id.getText);

        myImage =findViewById(R.id.myImage);
        if(savedInstanceState!=null){
            image=savedInstanceState.getInt(KEY_IMAGE,0);
            myImage.setImageResource(ID[image]);
        }else {
            myImage.setImageResource(ID[2]);
            image = 2;
        }

        Intent myIntent =getIntent();
        if(myIntent!=null){
            Bundle myIntentBundleExtra =myIntent.getExtras();
            String name =myIntentBundleExtra.getString("key_name");
            String text_view=name+"\n";
            get_text.setText(text_view);

        }
    }

    public void setChange_image(View view){


        image  =(image+1)%ID.length;
        myImage.setImageResource(ID[image]);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_IMAGE,image);

    }
}
