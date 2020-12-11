package com.wgabrechnung.manageme2.ui.projekt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.CORE_HELPER;
import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.adapter.KontoAdapter;
import com.wgabrechnung.manageme2.database.DatabaseKonto;
import com.wgabrechnung.manageme2.ui.konto.kontoumsatz;

import java.util.ArrayList;

public class DialogAddUmsatz extends Dialog implements android.view.View.OnClickListener {

    private final int ProjektID;
    private final KontoAdapter kontoAdapter;
    private final RecyclerView recyclerView;

    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     */
    public DialogAddUmsatz(@NonNull Context context, int projektID, KontoAdapter kontoAdapter, RecyclerView recyclerView) {
        super(context);
        this.ProjektID = projektID;
        this.kontoAdapter = kontoAdapter;
        this.recyclerView = recyclerView;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_umsatz);

        EditText eTbez = this.findViewById(R.id.editTextBEZ);
        EditText eTbetrag = this.findViewById(R.id.editTextBetrag);

        Button btnSave = this.findViewById(R.id.btnSaveUmsatz);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bez  = eTbez.getText().toString();
                String betrag  = eTbetrag.getText().toString();

                if(!bez.equals("") && !betrag.equals("")){

                    betrag = betrag.replace(",",".");
                    Double dblBetrag = Double.parseDouble(betrag);

                    DatabaseKonto db = new DatabaseKonto(getContext());
                    db.addUmsatzToProjekt(dblBetrag,bez,"manuell erstellt",bez, CORE_HELPER.getCurrDate(),"debit",ProjektID);

                    Toast.makeText(getContext(),"Umsatz wurde hinzugefügt", Toast.LENGTH_SHORT).show();

                    DatabaseKonto dbKont = new DatabaseKonto(getContext());
                    ArrayList<kontoumsatz> umsaetze = dbKont.getDataOfProjekt(ProjektID);
                    kontoAdapter.setList(umsaetze);
                    recyclerView.setAdapter(kontoAdapter);


                    dismiss();

                }else{
                    Toast.makeText(getContext(),"Bitte alle felder ausfüllen",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


    }


}
