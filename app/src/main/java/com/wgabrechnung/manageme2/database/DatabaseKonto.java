package com.wgabrechnung.manageme2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wgabrechnung.manageme2.CORE_HELPER;
import com.wgabrechnung.manageme2.ui.konto.kontoumsatz;

import java.util.ArrayList;

public class DatabaseKonto extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "KONTO";
    public static String TABLE_NAME;


    public DatabaseKonto(Context context){
        super(context,DATABASE_NAME,null,1);
        TABLE_NAME = "KONTO_"+CORE_HELPER.getUSER_KENNUNG(context);

        if(!CORE_HELPER.checkForTableExists(this.getWritableDatabase(),TABLE_NAME)){
            this.onCreate(this.getWritableDatabase());
        }

    }

    public void deleteProjekt(int ProjektID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("PROJEKT_ID",0);
        values.put("IS_SORTED",0);

        sqLiteDatabase.update(TABLE_NAME, values,"PROJEKT_ID=?",new String[]{String.valueOf(ProjektID)});

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
                "    IS_SORTED tinyint (1) default 0,    \n" +
                "    PROJEKT_ID INTEGER default 0    \n" +
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


    public boolean addUmsatzToProjekt(Double betrag,String vzweck, String art, String name, String date, String credit_debit,int projektID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BETRAG",betrag);
        contentValues.put("VZWECK",vzweck);
        contentValues.put("ART",art);
        contentValues.put("NAME",name);
        contentValues.put("DATE",date);
        contentValues.put("CREDIT_DEBIT",credit_debit);
        contentValues.put("IS_SORTED",1);
        contentValues.put("PROJEKT_ID",projektID);

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

    public ArrayList<kontoumsatz> getKontoListAdaptder(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<kontoumsatz> arrayList = new ArrayList<>();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE IS_SORTED=0 ORDER BY DATE DESC",null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            kontoumsatz dataset = new kontoumsatz();

            dataset.setID(cursor.getInt(cursor.getColumnIndex("ID")));
            dataset.setBETRAG(String.valueOf(cursor.getFloat(cursor.getColumnIndex("BETRAG"))));
            dataset.setNAME(cursor.getString(cursor.getColumnIndex("NAME")));
            dataset.setDATUM(cursor.getString(cursor.getColumnIndex("DATE")));
            dataset.setART(cursor.getString(cursor.getColumnIndex("ART")));
            dataset.setCREDIT_DEBIT(cursor.getString(cursor.getColumnIndex("CREDIT_DEBIT")));
            dataset.setSelected(false);

            arrayList.add(dataset);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList;

    }

    public String getLastRundruf(){

        String strReturn = "";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DATE FROM " + TABLE_NAME +  " ORDER BY DATE DESC LIMIT 1",null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            strReturn = cursor.getString(cursor.getColumnIndex("DATE"));

            cursor.moveToNext();
        }
        cursor.close();

        return strReturn;
    }

    public SQLiteDatabase getDatabase(){
        return this.getWritableDatabase();
    }


    public ArrayList<kontoumsatz> getDataOfProjekt(int intProjektID){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<kontoumsatz> arrayList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE IS_SORTED=1 AND PROJEKT_ID="+intProjektID,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            kontoumsatz dataset = new kontoumsatz();

            dataset.setID(cursor.getInt(cursor.getColumnIndex("ID")));
            dataset.setBETRAG(String.valueOf(cursor.getFloat(cursor.getColumnIndex("BETRAG"))));
            dataset.setNAME(cursor.getString(cursor.getColumnIndex("NAME")));
            dataset.setDATUM(cursor.getString(cursor.getColumnIndex("DATE")));
            dataset.setART(cursor.getString(cursor.getColumnIndex("ART")));
            dataset.setCREDIT_DEBIT(cursor.getString(cursor.getColumnIndex("CREDIT_DEBIT")));
            dataset.setSelected(false);

            arrayList.add(dataset);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList;

    }


    public String getBetragMonth(int projektID){

        String strReturn = "";

        String strFirstOfMonth = CORE_HELPER.getFirstOfMonth();
        String strLastOfMonth = CORE_HELPER.getLastOfMonth();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String strSQL = "SELECT * FROM " + TABLE_NAME + " WHERE IS_SORTED=1 AND PROJEKT_ID="+projektID+" AND DATE>='"+ strFirstOfMonth +"' AND DATE<='"+strLastOfMonth +"'";
        Cursor cursor = sqLiteDatabase.rawQuery(strSQL,null);

        Double ergebnis = 0.00;

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            ergebnis = ergebnis + cursor.getDouble(cursor.getColumnIndex("BETRAG"));

            cursor.moveToNext();
        }
        cursor.close();
        strReturn = ergebnis.toString();
        int indexPoint = strReturn.indexOf(".");
        if(strReturn.substring(indexPoint,strReturn.length()).length() > 3){
            indexPoint = indexPoint+3;
            strReturn = strReturn.substring(0,indexPoint);
        }

        return strReturn;
    }

    public String getBetragGesamt(int projektID){
        String strReturn = "";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String strSQL = "SELECT * FROM " + TABLE_NAME + " WHERE IS_SORTED=1 AND PROJEKT_ID="+projektID;
        Cursor cursor = sqLiteDatabase.rawQuery(strSQL,null);

        Double ergebnis = 0.00;

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            ergebnis = ergebnis + cursor.getDouble(cursor.getColumnIndex("BETRAG"));

            cursor.moveToNext();
        }
        cursor.close();

        strReturn = ergebnis.toString();
        int indexPoint = strReturn.indexOf(".");
        if(strReturn.substring(indexPoint,strReturn.length()).length() > 3){
            indexPoint = indexPoint+3;
            strReturn = strReturn.substring(0,indexPoint);
        }

        return strReturn;
    }


    public String getProjektBetrag(String projektID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String strSQL = "SELECT SUM(BETRAG) AS BETRAG FROM " + TABLE_NAME + " WHERE IS_SORTED=1 AND PROJEKT_ID="+projektID;
        Cursor cursor = sqLiteDatabase.rawQuery(strSQL,null);
        cursor.moveToFirst();
        Double ergebnis = cursor.getDouble(cursor.getColumnIndex("BETRAG"));
        cursor.close();

        return ergebnis.toString();

    }


}
