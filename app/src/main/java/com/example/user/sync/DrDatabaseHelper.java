package com.example.user.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DrDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "AreaDR.db";
    public static final String TABLE_NAME = "dr_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "topico";
    public static final String COL_3 = "resolvido";


    public DrDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+ "(" +COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String registro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, registro);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }

    public Cursor getReolvido(int position){
        SQLiteDatabase db = this.getWritableDatabase();
        int id = position;
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_1+ " = " +id, null);
        return res;
    }

    public boolean updateData(int position, int resolvido) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(resolvido == 0){
            contentValues.put(COL_3, 1);
        }else{
            contentValues.put(COL_3, 0);
        }

        String id = "" + position;
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
}