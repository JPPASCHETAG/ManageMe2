package com.wgabrechnung.manageme2.ui.todoListe;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wgabrechnung.manageme2.R;

public class todoListFragment extends Fragment {

    private TodoListViewModel mViewModel;

    public static todoListFragment newInstance() {
        return new todoListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.todo_list_fragment, container, false);

        RecyclerView recViewOffen = root.findViewById(R.id.recViewOffen);
        RecyclerView recViewDone = root.findViewById(R.id.recViewDone);
        ImageView img = root.findViewById(R.id.showDoneElements);



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TodoListViewModel.class);
        // TODO: Use the ViewModel
    }

}