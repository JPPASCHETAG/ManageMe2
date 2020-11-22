package com.wgabrechnung.manageme2;

import android.content.Context;

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

        //String strUrl = "http://neat-me.de/connect.php?";
        StringBuilder strUrl = new StringBuilder("http://192.168.2.175/ManageMeWeb/http_request.php?");

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

}
