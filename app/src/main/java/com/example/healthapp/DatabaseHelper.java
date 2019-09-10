package com.example.healthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Console;

import static android.provider.Contacts.SettingsColumns.KEY;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "Account.db";
    public static final String tableName = "Account_list";
    public static final String col1 = "AccountNum";
    public static final String col2 = "Name";
    public static final String col3 = "Weight";
    public static final String col4 = "Height";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tableName + "(" + col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col2 + " TEXT, " + col3 + " REAL, " + col4 + " REAL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public boolean insertData(String name, float weight, float height) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(col2, name);
        contentValue.put(col3, weight);
        contentValue.put(col4, height);

        long result = db.insert(tableName, null, contentValue);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select * from " + tableName, null);

        return result;
    }

    public boolean checkAccount(String nameCheck) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String sql = "SELECT * FROM Account_list" + " WHERE name = ?";
        cursor = db.rawQuery(sql, new String[]{nameCheck});
        //Log("Cursor Count : " + cursor.getCount());

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*    public int getAccountNum(int pin){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = null;
            String sql = "SELECT * FROM Account_list" + " WHERE name = ?";
            cursor = db.rawQuery(sql, new String[]{pin});
            //Log("Cursor Count : " + cursor.getCount());


        }
        */

        //cursor.close();

    public Cursor getData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        //String[] args = {studentNumber};
        //String query = "select * from Student_list where StudentNumber =" + name;
        //Cursor result =  db.rawQuery(query, null);
       // Cursor result = db.rawQuery("SELECT * FROM Account_list WHERE name = ?", new String[] {name});
        String sql = "SELECT * FROM Account_list" + " WHERE name = ?";
        cursor = db.rawQuery(sql, new String[]{name});

        return cursor;
       /* if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
*/
    }

    public boolean updateData(String name,float weight,float height) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(col1,"1");
        contentValues.put(col2,name);
        contentValues.put(col3,weight);
        contentValues.put(col4,height);
        db.update(tableName, contentValues, "Name = ?",new String[] { name });
        return true;
    }
}
