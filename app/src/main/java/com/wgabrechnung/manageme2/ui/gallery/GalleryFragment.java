package com.wgabrechnung.manageme2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.CORE_HELPER;
import com.wgabrechnung.manageme2.HTTP_REQUEST;
import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.adapter.KontoAdapter;
import com.wgabrechnung.manageme2.adapter.ProjekteAdapter;
import com.wgabrechnung.manageme2.database.DatabaseKonto;

import java.util.ArrayList;
import java.util.HashMap;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        Button kontenrundruf  = root.findViewById(R.id.btnKontoRundruf);
        Button btnKontoDelete  = root.findViewById(R.id.btnKontoDelete);

        kontenrundruf.setOnClickListener(v -> {

            HashMap<String,String> URLparam = new HashMap<>();
            URLparam.put("MODE","2");
            String strURL = CORE_HELPER.CREATE_URL(URLparam);

            HTTP_REQUEST http_request = new HTTP_REQUEST(root.getContext(),2);
            http_request.execute(strURL);

        });

        btnKontoDelete.setOnClickListener(v -> {

            DatabaseKonto db = new DatabaseKonto(root.getContext());
            db.deleteContents();

        });


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

        return root;
    }
}