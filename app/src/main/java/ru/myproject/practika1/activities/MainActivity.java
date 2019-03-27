package ru.myproject.practika1.activities;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ru.myproject.practika1.R;
import ru.myproject.practika1.database.DBHelper;


public class MainActivity extends AppCompatActivity {

    Button btn_next;
    EditText name ,password;
    ImageView myImage;
    public static int image;
    int []ID =new int[]{R.drawable.bmwm2,R.drawable.gelandewagen,R.drawable.gelic2};
    private static String KEY_NAME="key_name";
    private static String KEY_EMAIL_ADDRESS="key_email_address";

    DBHelper dbHelper;
    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name =findViewById(R.id.text_name);
        password =findViewById(R.id.text_password);
        dbHelper = new DBHelper(this);

        db1 = dbHelper.getReadableDatabase();
        check(db1);





        btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check(db1)) {
                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    Bundle myBundle = new Bundle();
                    myBundle.putString(KEY_NAME, name.getText().toString());
                    myBundle.putString(KEY_EMAIL_ADDRESS, password.getText().toString());
                    intent.putExtras(myBundle);
                    startActivity(intent);

                }else{
                    Toast.makeText(v.getContext(),"Не правильный логин или пароль!",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    public boolean check(SQLiteDatabase db3){


        Cursor cursor = db3.rawQuery("Select id, login,password from user",new String[]{});


        if(cursor!=null){
            cursor.moveToFirst();
        }

        do {

            int id =cursor.getInt(0);
            String login = cursor.getString(1);
            String password1 = cursor.getString(2);

            if(name.getText().toString().equals(login)&&password.getText().toString().equals(password1)){
                dbHelper.setId_user(id);
                cursor.close();
                return true;
            }
        } while (cursor.moveToNext());
        return false;
    }



   public void setChange_image(View view){


        image  =(image+1)%ID.length;
        myImage.setImageResource(ID[image]);

    }
}
