package com.wgabrechnung.manageme2.ui.projekte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.ViewAnimation;
import com.wgabrechnung.manageme2.adapter.ProjekteAdapter;
import com.wgabrechnung.manageme2.database.DatabaseProjekte;
import com.wgabrechnung.manageme2.ui.newProjekt.NewProjektFragment;
import com.wgabrechnung.manageme2.ui.slideshow.SlideshowFragment;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    boolean isRotate = false;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Projekte");

        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        //Hier wird ie Liste aufgebaut
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        DatabaseProjekte db = new DatabaseProjekte(getContext());
        ArrayList<String[]> projekte = db.getAllData();

        FragmentManager fragmentManager = getParentFragmentManager();
        recyclerView.setAdapter(new ProjekteAdapter(projekte,fragmentManager));

        //Die kleinen fabs werden ausgeblendet
        FloatingActionButton addProjekt = root.findViewById(R.id.addProjekt);
        FloatingActionButton newProjekt = root.findViewById(R.id.newProjekt);

        ViewAnimation.init(addProjekt);
        ViewAnimation.init(newProjekt);

        //Hier wir der ActionButton aufgebaut
        final FloatingActionButton actionButton = root.findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isRotate = ViewAnimation.rotateFab(v,!isRotate,actionButton);
                if(isRotate){
                    ViewAnimation.showIn(addProjekt);
                    ViewAnimation.showIn(newProjekt);
                }else{
                    ViewAnimation.showOut(addProjekt);
                    ViewAnimation.showOut(newProjekt);
                }
            }
        });

        addProjekt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hier können die anderen Fragmente geöffnet werden

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.right_enter, R.anim.right_exit);
                SlideshowFragment newFragment = SlideshowFragment.newInstance();
                ft.replace(R.id.nav_host_fragment, newFragment, "detailFragment").addToBackStack( "ProjektFragment" );
                // Start the animated transition.
                ft.commit();

            }
        });

        newProjekt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hier können die anderen Fragmente geöffnet werden
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.right_enter, R.anim.right_exit);
                NewProjektFragment newFragment = NewProjektFragment.newInstance();
                ft.replace(R.id.nav_host_fragment, newFragment, "newProjektFragment").addToBackStack( "ProjektFragment" );
                // Start the animated transition.
                ft.commit();
            }
        });


        return root;
    }
}