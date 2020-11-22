package com.wgabrechnung.manageme2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;

public class KontoViewholder extends RecyclerView.ViewHolder {

    private TextView view;
    public KontoViewholder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.text1);
    }

    public TextView getView(){
        return view;
    }
}
