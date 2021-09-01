package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    private static String DatabaseName= "UsersDb.db";
    private static String tableName= "User";
    private static String username= "username";
    private static String Age = "age";

    public DBhelper(Context context){
        super(context, DatabaseName, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("CREATE TABLE " + tableNam/sqLiteDatabase.execSQL("CREATE TABLE " + tableName + e + " (" + username + " TEXT primary key, " + age + "NUMBER)");
        sqLiteDatabase.execSQL("CREATE TABLE User(username TEXT PRIMARY KEY, age NUMBER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP table if exists " + tableName);
    }

    public boolean insertData(String name, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(username, name);
        contentValues.put(Age, age);
        Long result = db.insert(tableName, null, contentValues);
        if (result == -1 ){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor readData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + tableName , null);
        return cursor;
    }

    public boolean deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, username + " = "+"'" + name + "'", null)>0;
    }
}
