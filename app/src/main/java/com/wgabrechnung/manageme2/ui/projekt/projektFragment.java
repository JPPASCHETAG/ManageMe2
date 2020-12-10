package com.wgabrechnung.manageme2.ui.projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.adapter.KontoAdapter;
import com.wgabrechnung.manageme2.adapter.ProjekteAdapter;
import com.wgabrechnung.manageme2.database.DatabaseKonto;
import com.wgabrechnung.manageme2.database.DatabaseProjekte;
import com.wgabrechnung.manageme2.ui.konto.kontoumsatz;
import com.wgabrechnung.manageme2.ui.projekte.GalleryViewModel;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class projektFragment extends Fragment {

    private ProjektViewModel projektViewModel;
    private RecyclerView recyclerView;
    private TextView TextViewBetragMonth, TextViewBetragGesamt;

    private static String Projektname;
    private static int projektID;

    public static projektFragment newInstance(String strProjektName, int projektid) {
        Projektname = strProjektName;
        projektID = projektid;
        return new projektFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Projektname);


        projektViewModel = new ViewModelProvider(this).get(ProjektViewModel.class);
        View root = inflater.inflate(R.layout.projekt_fragment, container, false);

        //zugeordnete Umsätze holen und ausgeben
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        DatabaseKonto db = new DatabaseKonto(getContext());
        ArrayList<kontoumsatz> projekt = db.getDataOfProjekt(projektID);
        recyclerView.setAdapter(new KontoAdapter(projekt));

        //aktuellen Monat ausgeben
        TextViewBetragMonth = root.findViewById(R.id.textViewMonth);
        String monthBetrag = db.getBetragMonth(projektID);
        TextViewBetragMonth.setText(monthBetrag + "€");

        //Gesamten Betrag setzen
        TextViewBetragGesamt = root.findViewById(R.id.textViewGesamt);
        String GesamtBetrag = db.getBetragGesamt(projektID);
        TextViewBetragGesamt.setText(GesamtBetrag + "€");

        return root;
    }

}