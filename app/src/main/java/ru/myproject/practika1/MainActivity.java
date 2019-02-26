package ru.myproject.practika1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    Button btn_next;
    EditText name ,password;
    ImageView myImage;
    public static int image;
    int []ID =new int[]{R.drawable.bmwm2,R.drawable.gelandewagen,R.drawable.gelic2};
    private static String KEY_NAME="key_name";
    private static String KEY_EMAIL_ADDRESS="key_email_address";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name =findViewById(R.id.text_name);
        password =findViewById(R.id.text_password);





        btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,FragmentActivity.class);
                Bundle myBundle =new Bundle();
                myBundle.putString(KEY_NAME,name.getText().toString());
                myBundle.putString(KEY_EMAIL_ADDRESS,password.getText().toString());
                intent.putExtras(myBundle);
                startActivity(intent);
            }
        });



    }

   public void setChange_image(View view){


        image  =(image+1)%ID.length;
        myImage.setImageResource(ID[image]);

    }
}
