package com.wgabrechnung.manageme2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListViewHolder> {

    private final ArrayList<String[]> arrayList;

    public ToDoListAdapter(ArrayList<String[]> liste) {
        this.arrayList = liste;
    }

    @NonNull
    @Override
    public ToDoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_offen, parent, false);
        return new ToDoListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ToDoListViewHolder holder, int position) {
        String[] listen = arrayList.get(position);

        String name = listen[1];
        String betrag = listen[0];

        holder.getToDoName().setText(name);
        holder.getCheckToDo().setVisibility(View.VISIBLE);
        holder.getDelToDO().setVisibility(View.VISIBLE);

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
