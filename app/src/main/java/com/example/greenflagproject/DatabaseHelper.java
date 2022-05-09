package com.example.greenflagproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name =  "database";
    static int version = 1;
    public static String greenFlag = "greenflag";
    private final Context context;

    //this table is to store the patients information when register
    String greenF = "CREATE TABLE if not exists greenflag ('id' INTEGER PRIMARY KEY AUTOINCREMENT ,'email' TEXT, 'password' TEXT)";

    //this creates the patients and NHS tables
    public DatabaseHelper(Context context) {
        super(context, name, null , version);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(greenF);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + greenFlag);
        onCreate(db);
    }

    //this method inserts the greenflag information into the user table when registering
    public void insertgreenFlagdetails(ContentValues contentValues){
        try (Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM greenflag WHERE email = ?", new String[]{contentValues.get("email").toString()})) {
            if (cursor.moveToFirst()) {
                Toast.makeText(context, "Username already exist", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        getWritableDatabase().insert("greenFlag", "", contentValues);
        Toast.makeText(context, "Registered as user", Toast.LENGTH_SHORT).show();

    }
}