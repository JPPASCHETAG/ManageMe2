package com.wgabrechnung.manageme2.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

public class KontoViewholder extends RecyclerView.ViewHolder {

    private final TextView viewBetrag;
    private final TextView viewName;
    private final TextView viewDatum;
    private final TextView viewArt;
    private final LinearLayout layoutBez;
    private final CheckBox checkBox;

    public KontoViewholder(@NonNull View itemView) {
        super(itemView);
        viewBetrag = itemView.findViewById(R.id.BETRAG);
        viewName = itemView.findViewById(R.id.NAME);
        viewDatum = itemView.findViewById(R.id.DATUM);
        viewArt = itemView.findViewById(R.id.ART);
        layoutBez = itemView.findViewById(R.id.layoutBez);
        checkBox = itemView.findViewById(R.id.checkbox);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Position is " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        //Long Press
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(v.getContext(), "long Position is " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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
