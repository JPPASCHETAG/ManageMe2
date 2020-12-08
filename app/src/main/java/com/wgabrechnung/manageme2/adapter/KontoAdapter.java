package com.wgabrechnung.manageme2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.ui.konto.kontoumsatz;

import java.util.ArrayList;

import static com.wgabrechnung.manageme2.CORE_HELPER.FormatBetrag;
import static com.wgabrechnung.manageme2.CORE_HELPER.FormatDatum;

public class KontoAdapter extends RecyclerView.Adapter<KontoViewholder> {

    private final ArrayList<kontoumsatz> arrayList;

    public KontoAdapter(ArrayList<kontoumsatz> liste) {
        this.arrayList = liste;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.simple_list_item;
    }

    @NonNull
    @Override
    public KontoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false);
        return new KontoViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontoViewholder holder, int position) {

        final kontoumsatz umsatz = arrayList.get(position);

        String betrag = umsatz.getBETRAG();
        betrag = FormatBetrag(betrag, umsatz.getCREDIT_DEBIT());
        String name = umsatz.getNAME();
        String art = umsatz.getART();
        String datum = umsatz.getDATUM();
        datum = FormatDatum(datum);

        if(umsatz.isSelected()){
            holder.getLayoutBez().setBackgroundResource(R.drawable.rounded_corners_border);
        }else{
            holder.getLayoutBez().setBackgroundResource(R.drawable.rounded_corners);
        }

        holder.getLayoutBez().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                umsatz.setSelected(!umsatz.isSelected());
                notifyItemChanged(position);
            }
        });

        holder.getViewBetrag().setText(betrag);
        holder.getViewName().setText(name);
        holder.getViewArt().setText(art);
        holder.getViewDatum().setText(datum);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}