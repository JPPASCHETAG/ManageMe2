package com.wgabrechnung.manageme2.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

import java.util.ArrayList;

public class ProjekteAdapter extends RecyclerView.Adapter<ProjeketeViewHolder>  {


    private final ArrayList<String[]> arrayList;

    public ProjekteAdapter(ArrayList<String[]> liste) {
        this.arrayList = liste;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.projekete_list_item;
    }

    @NonNull
    @Override
    public ProjeketeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ProjeketeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProjeketeViewHolder holder, int position) {

        String name = arrayList.get(position)[0];
        String betrag = arrayList.get(position)[1];
        String img = arrayList.get(position)[2];

        holder.getViewBetrag().setText(betrag);
        holder.getViewName().setText(name);
        holder.getViewImg().setBackground(Drawable.createFromPath(img));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
