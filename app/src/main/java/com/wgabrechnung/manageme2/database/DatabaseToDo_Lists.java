package com.wgabrechnung.manageme2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wgabrechnung.manageme2.CORE_HELPER;

public class DatabaseToDo_Lists extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TODO";
    public static final String TABLE_NAME = "TODO_LISTS";
    private final Context context;

    public DatabaseToDo_Lists(Context context){
        super(context,DATABASE_NAME,null,1);
        this.context = context;

        if(!CORE_HELPER.checkForTableExists(this.getWritableDatabase(),TABLE_NAME)){
            this.onCreate(this.getWritableDatabase());
        }

    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "create table " + TABLE_NAME + "(" +
                "    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
                "    BEZ varchar (150) not null default \"\"" +
                ");";
        db.execSQL(createTable);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
