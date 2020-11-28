package com.wgabrechnung.manageme2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

import java.util.ArrayList;

import static com.wgabrechnung.manageme2.CORE_HELPER.FormatBetrag;
import static com.wgabrechnung.manageme2.CORE_HELPER.FormatDatum;

public class KontoAdapter extends RecyclerView.Adapter<KontoViewholder> {

    private final ArrayList<String[]> arrayList;

    public KontoAdapter(ArrayList<String[]> liste) {
        this.arrayList = liste;
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
        return arrayList.size();
    }

}