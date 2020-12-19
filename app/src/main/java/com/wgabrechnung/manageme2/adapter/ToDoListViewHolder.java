package com.wgabrechnung.manageme2.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

public class ToDoListViewHolder  extends RecyclerView.ViewHolder {

    private final TextView ToDoName;
    private final ImageView checkToDo;
    private final ImageView delToDO;


    public ToDoListViewHolder(@NonNull View itemView) {
        super(itemView);
        ToDoName = itemView.findViewById(R.id.todoText);
        checkToDo = itemView.findViewById(R.id.checkToDo);
        delToDO = itemView.findViewById(R.id.delToDo);
    }

    public TextView getToDoName() {
        return ToDoName;
    }

    public ImageView getCheckToDo() {
        return checkToDo;
    }

    public ImageView getDelToDO() {
        return delToDO;
    }
}
