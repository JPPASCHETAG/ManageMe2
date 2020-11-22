package com.wgabrechnung.manageme2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class KontoAdapter extends RecyclerView.Adapter<KontoViewholder> {

    private ArrayList<String[]> arrayList;
    private Random random;

    public KontoAdapter(int seed,ArrayList<String[]> liste) {
        this.arrayList = liste;
        this.random = new Random(seed);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.simple_list_item;
    }

    @NonNull
    @Override
    public KontoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new KontoViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontoViewholder holder, int position) {

        String betrag = arrayList.get(position)[0];
        betrag = FormatBetrag(betrag, arrayList.get(position)[4]);
        String name = arrayList.get(position)[3];
        String art = arrayList.get(position)[2];
        String datum = arrayList.get(position)[6];
        datum = FormatDatum(datum);


        holder.getViewBetrag().setText(betrag);
        holder.getViewName().setText(name);
        holder.getViewArt().setText(art);
        holder.getViewDatum().setText(datum);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public String FormatBetrag(String oldBetrag, String credit_debit){

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

    public String FormatDatum(String datum){

        String strReturn = "";

        String[] date = datum.split("-");

        for (int i = date.length-1; i>=0;i--){
            strReturn += date[i] +".";
        }

        strReturn = strReturn.substring(0,strReturn.length()-1);

        return strReturn;

    }

}