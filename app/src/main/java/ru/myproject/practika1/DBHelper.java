package ru.myproject.practika1;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper
{
    private static final String dbname="mydb";
    private static final int version=1;

    public DBHelper( Context context) {
        super(context, dbname, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table user ("
                + "id integer primary key autoincrement,"
                + "login text,"
                + "password text" + ");");

        db.execSQL("create table book ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "genre text,"
                + "author text);");


        insertData("student","12345",db);
        insertData("admin","54321",db);
        insertData("dasha","1997",db);
    }


    private void insertData(String login,String password,SQLiteDatabase database){
             ContentValues cv = new ContentValues();
        cv.put("login",login);
        cv.put("password",password);
        database.insert("user", null, cv);

    }

    public void insertDataFromJson(String name_book,String genre,String author,
                                   SQLiteDatabase database){
        ContentValues cv = new ContentValues();
        cv.put("name",name_book);
        cv.put("genre",genre);
        cv.put("author",author);
        database.insert("book", null, cv);

    }

    public void selectData( SQLiteDatabase database){
       Cursor cursor= database.rawQuery("Select name,genre,author from book",new String[]{});

        if(cursor!=null){
            cursor.moveToFirst();
        }

        do {

            assert cursor != null;
            String name = cursor.getString(0);
            String genre = cursor.getString(1);
            String author = cursor.getString(2);

            Log.i("SQL_Select",
                    "name "+ name+
                    " genre "+genre+
                    " author "+author+" \n");

        } while (cursor.moveToNext());

    }
    public void deleteRecordsFromBook(SQLiteDatabase database){
        database.execSQL("Delete from book");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
