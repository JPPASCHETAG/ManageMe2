package com.wgabrechnung.manageme2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.ViewAnimation;
import com.wgabrechnung.manageme2.adapter.ProjekteAdapter;
import com.wgabrechnung.manageme2.ui.slideshow.SlideshowFragment;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    boolean isRotate = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

//        Button kontenrundruf  = root.findViewById(R.id.btnKontoRundruf);
//        Button btnKontoDelete  = root.findViewById(R.id.btnKontoDelete);
//
//        kontenrundruf.setOnClickListener(v -> {
//
//            HashMap<String,String> URLparam = new HashMap<>();
//            URLparam.put("MODE","2");
//            String strURL = CORE_HELPER.CREATE_URL(URLparam);
//
//            HTTP_REQUEST http_request = new HTTP_REQUEST(root.getContext(),2);
//            http_request.execute(strURL);
//
//        });
//
//        btnKontoDelete.setOnClickListener(v -> {
//
//            DatabaseKonto db = new DatabaseKonto(root.getContext());
//            db.deleteContents();
//
//        });

        //Hier wird ie Liste aufgebaut
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        ArrayList<String[]> projekte = new ArrayList<>();
        String[] kl = new String[2];
        kl[0] = "Einkaufen";
        kl[1] = "285.66 €";
        String[] kl1 = new String[2];
        kl1[0] = "Auto";
        kl1[1] = "851.63 €";
        String[] kl2 = new String[2];
        kl2[0] = "Reisen 2020";
        kl2[1] = "2354,14 €";

        projekte.add(kl);
        projekte.add(kl1);
        projekte.add(kl2);

        recyclerView.setAdapter(new ProjekteAdapter(projekte));

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
                ft.replace(R.id.nav_host_fragment, newFragment, "detailFragment");
                // Start the animated transi    tion.
                ft.commit();

            }
        });

        newProjekt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hier können die anderen Fragmente geöffnet werden
            }
        });



        return root;
    }
}