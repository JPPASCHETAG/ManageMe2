package com.wgabrechnung.manageme2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wgabrechnung.manageme2.CORE_HELPER;

public class DatabaseToDo  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TODO";
    public static String TABLE_NAME;
    private final Context context;

    public DatabaseToDo(Context con, int id){
        super(con,DATABASE_NAME,null,1);
        this.context = con;

        TABLE_NAME = "TODO_LISTS_" + id;

        if(!CORE_HELPER.checkForTableExists(this.getWritableDatabase(),TABLE_NAME)){
            this.onCreate(this.getWritableDatabase());
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "create table " + TABLE_NAME + "(" +
                "    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
                "    BEZ varchar (150) not null default \"\"," +
                "    CHECKED inyint (1) not null default 0" +
                ");";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
