package com.wgabrechnung.manageme2.ui.konto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wgabrechnung.manageme2.CORE_HELPER;
import com.wgabrechnung.manageme2.HTTP_REQUEST;
import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.ViewAnimation;
import com.wgabrechnung.manageme2.adapter.KontoAdapter;
import com.wgabrechnung.manageme2.database.DatabaseKonto;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private boolean isRotate = false;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        DatabaseKonto dbKont = new DatabaseKonto(root.getContext());
        ArrayList<kontoumsatz> umsaetze = dbKont.getKontoListAdaptder();

        KontoAdapter kontoAdapter = new KontoAdapter(umsaetze);
        recyclerView.setAdapter(kontoAdapter);

        //Die kleinen fabs werden ausgeblendet
        FloatingActionButton kontenRundruf = root.findViewById(R.id.kontenRundruf);
        FloatingActionButton autoSort = root.findViewById(R.id.autoSort);

        ViewAnimation.init(kontenRundruf);
        ViewAnimation.init(autoSort);

        //Hier wir der ActionButton aufgebaut
        final FloatingActionButton actionButton = root.findViewById(R.id.floatingActionButtonKonto);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isRotate = ViewAnimation.rotateFab(v,!isRotate,actionButton);
                if(isRotate){
                    ViewAnimation.showIn(kontenRundruf);
                    ViewAnimation.showIn(autoSort);
                }else{
                    ViewAnimation.showOut(kontenRundruf);
                    ViewAnimation.showOut(autoSort);
                }
            }
        });

        kontenRundruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setCancelable(true);
                builder.setTitle("Kontenrundruf starten");
                builder.setMessage("Möchten Sie den Kontenrundruf starten und alle aktuellen Umsätze in die App importieren?");
                builder.setPositiveButton("starten", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(root.getContext(),"Kontenrundruf gestartet",Toast.LENGTH_SHORT).show();

                            DatabaseKonto db = new DatabaseKonto(root.getContext());
                            String lastRundruf = db.getLastRundruf();

                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                            String bankingNutzer = sharedPreferences.getString("ONLINE_BANKING_NUTZER", "");
                            String bankingPWD = sharedPreferences.getString("ONLINE_BANKING_PWD", "");

                            if(!bankingNutzer.equals("") && !bankingPWD.equals("")){


                                HashMap<String,String> URLparam = new HashMap<String,String>();
                                URLparam.put("MODE","2");
                                URLparam.put("LAST_RUNDRUF",lastRundruf);
                                URLparam.put("ONLINE_BANKING_NUTZER",bankingNutzer);
                                URLparam.put("ONLINE_BANKING_PWD",bankingPWD);
                                URLparam.put("USER_KENNUNG",CORE_HELPER.getUSER_KENNUNG(getContext()));
                                String strURL = CORE_HELPER.CREATE_URL(URLparam);

                                HTTP_REQUEST http_request = new HTTP_REQUEST(root.getContext(),2);
                                http_request.setCustomVarsKontorundruf(kontoAdapter,recyclerView);
                                http_request.execute(strURL);

                            }else{
                                Toast.makeText(root.getContext(),"Keine HBCI Daten hinterlegt",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                builder.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(root.getContext(),"Kontenrundruf abgebrochen",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        autoSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogSortUmsatz dialog = new DialogSortUmsatz(root.getContext(),kontoAdapter,recyclerView);
                dialog.show();

            }
        });

        return root;
    }
}