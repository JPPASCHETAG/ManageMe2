package com.wgabrechnung.manageme2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

public class KontoViewholder extends RecyclerView.ViewHolder {

    private final TextView viewBetrag;
    private final TextView viewName;
    private final TextView viewDatum;
    private final TextView viewArt;

    public KontoViewholder(@NonNull View itemView) {
        super(itemView);
        viewBetrag = itemView.findViewById(R.id.BETRAG);
        viewName = itemView.findViewById(R.id.NAME);
        viewDatum = itemView.findViewById(R.id.DATUM);
        viewArt = itemView.findViewById(R.id.ART);
    }

    public TextView getViewBetrag(){
        return viewBetrag;
    }

    public TextView getViewName(){
        return viewName;
    }

    public TextView getViewArt(){
        return viewArt;
    }

    public TextView getViewDatum(){
        return viewDatum;
    }

}
