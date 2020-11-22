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


    public DatabaseKonto(Context context){
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
                "    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
                "    BETRAG float not null default 0,\n" +
                "    VZWECK varchar (150) not null default \"\",\n" +
                "    ART varchar (50) default \"\",\n" +
                "    NAME varchar (50) not null default \"\",\n" +
                "    DATE DATE default '0000-00-00',\n" +
                "    CREDIT_DEBIT varchar (50) not null default \"\",\n" +
                "    IS_SORTED tinyint (1) default 0    \n" +
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

    public boolean addDataset(Double betrag,String vzweck, String art, String name, String date, String credit_debit){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BETRAG",betrag);
        contentValues.put("VZWECK",vzweck);
        contentValues.put("ART",art);
        contentValues.put("NAME",name);
        contentValues.put("DATE",date);
        contentValues.put("CREDIT_DEBIT",credit_debit);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return true;

    }

    public void deleteContents(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + TABLE_NAME);

    }


    public ArrayList getAllText(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<String[]> arrayList = new ArrayList<>();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            String[] dataset = new String[7];

            dataset[0] = String.valueOf(cursor.getFloat(cursor.getColumnIndex("BETRAG")));
            dataset[1] = cursor.getString(cursor.getColumnIndex("VZWECK"));
            dataset[2] = cursor.getString(cursor.getColumnIndex("ART"));
            dataset[3] = cursor.getString(cursor.getColumnIndex("NAME"));
            dataset[4] = cursor.getString(cursor.getColumnIndex("CREDIT_DEBIT"));
            dataset[5] = String.valueOf(cursor.getInt(cursor.getColumnIndex("IS_SORTED")));
            dataset[6] = cursor.getString(cursor.getColumnIndex("DATE"));


            arrayList.add(dataset);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList;

    }

}
