package com.wgabrechnung.manageme2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

public class ToDoListenViewHolder extends RecyclerView.ViewHolder {

    private final TextView listName;
    private final TextView listOpenTasks;


    public ToDoListenViewHolder(@NonNull View itemView) {
        super(itemView);
        listName = itemView.findViewById(R.id.todoListText);
        listOpenTasks = itemView.findViewById(R.id.todoListOpenTasks);

    }

    public TextView getListName() {
        return listName;
    }

    public TextView getListOpenTasks() {
        return listOpenTasks;
    }
}
