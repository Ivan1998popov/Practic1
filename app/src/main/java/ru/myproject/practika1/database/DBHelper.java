package ru.myproject.practika1.database;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper
{
    private static final String dbname="mydb";
    private static final int version=1;
    public static int id_user;






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

        db.execSQL("create table favorite ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "genre text,"
                + "author text," +
                "id_user integer);");



        insertData("student","1",db);
        insertData("admin","2",db);
        insertData("ivan","3",db);
        insertData("vladimir","4",db);
        insertData("nikola","5",db);
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

    public boolean insertFavoriteBook(String name_book,String genre,String author,
                                   SQLiteDatabase database){
        ContentValues cv = new ContentValues();
        Cursor cursor =getReadableDatabase().rawQuery("Select count(*) from favorite where name=? and id_user=?",
                new String[]{name_book,String.valueOf(DBHelper.id_user)});
        cursor.moveToFirst();
        int count=0;
        try {
            do {
               count = cursor.getInt(0);
                System.out.println(count);
            } while (cursor.moveToNext());
        }catch (Exception e){
            System.out.println("нет данных");
        }
        if(count==0) {
            System.out.println();

            cv.put("name", name_book);
            cv.put("genre", genre);
            cv.put("author", author);
            cv.put("id_user", id_user);
            database.insert("favorite", null, cv);
            return true;
        }else {
            return false;
        }

    }

    public List<String> selectData(Cursor cursor){

        List<String> name_list =new ArrayList<>();
        if(cursor!=null){
            cursor.moveToFirst();
        }
        try {
            do {

                assert cursor != null;
                String name = cursor.getString(0);
                name_list.add(name);
                String genre = cursor.getString(1);
                String author = cursor.getString(2);

                Log.i("SQL_Select",
                        "name " + name +
                                " genre " + genre +
                                " author " + author + " \n");

            } while (cursor.moveToNext());
        }catch (Exception e){
            name_list.add("Список пуст!");
        }
        return name_list;
    }
    public void deleteRecordsFromBook(SQLiteDatabase database){
        database.execSQL("Delete from book");
    }

    public  void setId_user(int id_user) {
        DBHelper.id_user = id_user;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
