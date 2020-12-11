package com.wgabrechnung.manageme2;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CORE_HELPER {

    public CORE_HELPER(){

    }

    /**
     * Die Funktion lädt
     *
     */
    public static void setSettings(Context appContext){



    }

    /**
     * Erstellt eine URL zum Aufruf durch HTTP_REQUEST
     * @param URLparams Parameter mit name und Value
     * @return strURL für den Aufruf in HTTP_REQUEST
     */
    public static String CREATE_URL(HashMap URLparams){

        //String strUrl = "connect.php?";
        StringBuilder strUrl = new StringBuilder("http://neat-me.de/ManageMeWeb/http_request.php?");

        for (Object o : URLparams.entrySet()) {
            Map.Entry pair = (Map.Entry) o;

            strUrl.append(pair.getKey().toString());
            strUrl.append("=");
            strUrl.append(pair.getValue().toString());
            strUrl.append("&");
        }
        //das lezte & entfernen
        strUrl = new StringBuilder(strUrl.substring(0, strUrl.length() - 1));

        return strUrl.toString();

    }

    public static String FormatBetrag(String oldBetrag, String credit_debit){

        String[] nachKomma = oldBetrag.split("\\.");
        String cent = nachKomma[1];
        if(cent.length() == 1){
            cent += "0 €";
        }else{
            cent += " €";
        }

        nachKomma[1] = cent;

        String strReturn = String.join(",", nachKomma);

        if(credit_debit.equals("debit")){
            strReturn = "-" + strReturn;
        }

        return strReturn;
    }

    public static String FormatDatum(String datum){

        String strReturn = "";

        String[] date = datum.split("-");

        for (int i = date.length-1; i>=0;i--){
            strReturn += date[i] +".";
        }

        strReturn = strReturn.substring(0,strReturn.length()-1);

        return strReturn;

    }

    public static String getFirstOfMonth() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Date FirstDayOfMonth = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = format1.format(FirstDayOfMonth);


        return date;

    }

    public static String getLastOfMonth() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

        Date lastDayOfMonth = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(lastDayOfMonth);

        return date1;
    }

    public static String getCurrDate() {

        Date lastDayOfMonth = Calendar.getInstance().getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = format1.format(lastDayOfMonth);

        return date;

    }
}
