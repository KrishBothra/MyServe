package com.example.myserve;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Serve.db";
    public static final String TABLE_NAME = "serve_table";
    public static final String COL_1 = "DATE";
    public static final String COL_2 = "TOTAL";
    public static final String COL_3 = "MADE";
    public static final String COL_4 = "NUMBER";
    public static final String COL_5 = "TYPE";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //KAY = KEY
        db.execSQL("create table "+ TABLE_NAME +"  (DATE INTEGER PRIMARY KEY AUTOINCREMENT,TOTAL INTEGER,MADE INTEGER,NUMBER INTEGER,TYPE STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Integer total, Integer made,Integer number, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,total);
        contentValues.put(COL_3,made);
        contentValues.put(COL_4,number);
        contentValues.put(COL_5,type);
        long result = db.insert(TABLE_NAME, null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getALlData(){//This interface provides random read-write access to the result set returned by a database query.
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);//* = all
        return res;
    }

    public boolean updateData(String id, String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues,"ID = ?",new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
}
