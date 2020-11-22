package com.wgabrechnung.manageme2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

import java.util.Random;

public class KontoAdapter extends RecyclerView.Adapter<KontoViewholder> {
    private Random random;

    public KontoAdapter(int seed) {
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
        holder.getView().setText(String.valueOf(random.nextInt()));
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}