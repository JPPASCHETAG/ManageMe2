package com.wgabrechnung.manageme2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wgabrechnung.manageme2.CORE_HELPER;

import java.util.ArrayList;

public class DatabaseProjekte extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PROJEKTE";
    public static String TABLE_NAME;
    private final Context context;

    public DatabaseProjekte(Context context){
        super(context,DATABASE_NAME,null,1);
        this.context = context;
        TABLE_NAME = "PROJEKTE_"+ CORE_HELPER.getUSER_KENNUNG(context);

        if(!CORE_HELPER.checkForTableExists(this.getWritableDatabase(),TABLE_NAME)){
            this.onCreate(this.getWritableDatabase());
        }

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table PROJEKTE_" + CORE_HELPER.getUSER_KENNUNG(context) + "(" +
                "    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
                "    BEZ varchar (150) not null default \"\",\n" +
                "    BETRAG float not null default 0,\n" +
                "    IMG varchar (150) not null default \"\"" +
                ");";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addDataset(Double betrag,String bez, String img){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BEZ",bez);
        contentValues.put("BETRAG",betrag);
        contentValues.put("IMG",img);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

    }


    public ArrayList<String[]> getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<String[]> arrayList = new ArrayList<>();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            String[] dataset = new String[4];

            dataset[0] = cursor.getString(cursor.getColumnIndex("BEZ"));
            dataset[1] = cursor.getString(cursor.getColumnIndex("BETRAG"));
            dataset[2] = cursor.getString(cursor.getColumnIndex("IMG"));
            dataset[3] = cursor.getString(cursor.getColumnIndex("ID"));

            arrayList.add(dataset);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList;

    }

    public ArrayList<String[]> getProjektData(int projektID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<String[]> arrayList = new ArrayList<>();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " where ID="+projektID,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            String[] dataset = new String[4];

            dataset[3] = cursor.getString(cursor.getColumnIndex("ID"));
            dataset[0] = cursor.getString(cursor.getColumnIndex("BEZ"));
            dataset[1] = cursor.getString(cursor.getColumnIndex("BETRAG"));
            dataset[2] = cursor.getString(cursor.getColumnIndex("IMG"));

            arrayList.add(dataset);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList;

    }


    public void deleteProjekt(int ProjektID){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(ProjektID)});

    }

    public void updateProjekt(String bez, String img,int ProjektID){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("BEZ",bez);
        contentValues.put("IMG",img);

        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID=?",new String[]{String.valueOf(ProjektID)});

    }



}
