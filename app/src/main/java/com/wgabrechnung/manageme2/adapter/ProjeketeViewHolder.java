package com.wgabrechnung.manageme2.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

public class ProjeketeViewHolder extends RecyclerView.ViewHolder {

    private final TextView viewBetrag;
    private final TextView viewName;
    private final ImageView viewImg;
    private final CardView cardView;

    public ProjeketeViewHolder(@NonNull View itemView) {
        super(itemView);
        viewBetrag = itemView.findViewById(R.id.BETRAG);
        viewName = itemView.findViewById(R.id.NAME);
        viewImg = itemView.findViewById(R.id.IMAGE);
        cardView = itemView.findViewById(R.id.projektCard);

    }

    public TextView getViewBetrag(){
        return viewBetrag;
    }

    public TextView getViewName(){
        return viewName;
    }

    public ImageView getViewImg(){
        return viewImg;
    }

    public CardView getCardView() {
        return cardView;
    }
}
