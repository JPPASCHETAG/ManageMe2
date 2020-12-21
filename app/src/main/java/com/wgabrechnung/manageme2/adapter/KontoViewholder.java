package com.wgabrechnung.manageme2.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

public class KontoViewholder extends RecyclerView.ViewHolder {

    private final TextView viewBetrag;
    private final TextView viewName;
    private final TextView viewDatum;
    private final TextView viewArt;
    private final LinearLayout layoutBez;
    private final View view;


    public KontoViewholder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        viewBetrag = itemView.findViewById(R.id.BETRAG);
        viewName = itemView.findViewById(R.id.NAME);
        viewDatum = itemView.findViewById(R.id.DATUM);
        viewArt = itemView.findViewById(R.id.ART);
        layoutBez = itemView.findViewById(R.id.layout);

    }

    public LinearLayout getLayoutBez() {
        return layoutBez;
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

    public View getView() {
        return view;
    }
}
