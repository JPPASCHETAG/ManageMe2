package com.wgabrechnung.manageme2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

import java.util.ArrayList;

public class ToDoListenAdapter extends RecyclerView.Adapter<ToDoListenViewHolder> {

    private final ArrayList<String[]> arrayList;

    public ToDoListenAdapter(ArrayList<String[]> liste) {
        this.arrayList = liste;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.todo_liste_item;
    }

    @NonNull
    @Override
    public ToDoListenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_liste_item, parent, false);
        return new ToDoListenViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ToDoListenViewHolder holder, int position) {

        String[] listen = arrayList.get(position);

        String name = listen[1];
        String betrag = listen[0];

        holder.getListName().setText(betrag);
        holder.getListOpenTasks().setText(name);

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
