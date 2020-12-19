package com.wgabrechnung.manageme2.ui.ToDos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.adapter.ToDoListenAdapter;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public static SlideshowFragment newInstance(){
        return new SlideshowFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recViewListen);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        ArrayList<String[]> liste = new ArrayList<>();
        String[] arr1 = new String[2];
        arr1[0] = "liste 1";
        arr1[1] = "12";
        liste.add(arr1);
        String[] arr2 = new String[2];
        arr2[0] = "liste2";
        arr2[1] = "102";
        liste.add(arr2);
        String[] arr3 = new String[2];
        arr3[0] = "liste 3";
        arr3[1] = "15";
        liste.add(arr3);

        ToDoListenAdapter listenAdapter = new ToDoListenAdapter(liste);
        recyclerView.setAdapter(listenAdapter);

        return root;
    }
}