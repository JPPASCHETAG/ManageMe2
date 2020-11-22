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

import com.wgabrechnung.manageme2.CORE_HELPER;
import com.wgabrechnung.manageme2.HTTP_REQUEST;
import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.database.DatabaseKonto;

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


        return root;
    }
}