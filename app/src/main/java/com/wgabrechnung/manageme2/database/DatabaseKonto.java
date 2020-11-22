package com.wgabrechnung.manageme2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseKonto extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MANAGE_ME";
    public static final String TABLE_NAME = "KONTO";


    DatabaseKonto(Context context){
        super(context,DATABASE_NAME,null,1);
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
                "    ID int (11) not null,\n" +
                "    BETRAG float not null default 0,\n" +
                "    VZWECK varchar (150) not null default \"\",\n" +
                "    ART varchar (50) default \"\",\n" +
                "    NAME varchar (50) not null default \"\",\n" +
                "    DATE DATE default '0000-00-00',\n" +
                "    CREDIT_DEBIT varchar (50) not null default \"\",\n" +
                "    IS_SORTED tinyint (1) default 0,    \n" +
                "    primary key (ID)\n" +
                ");";
        db.execSQL(createTable);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addText(String txt){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("text",txt);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return true;

    }


    public ArrayList getAllText(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<String> arrayList = new ArrayList<String>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("text")));
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList;

    }

}
