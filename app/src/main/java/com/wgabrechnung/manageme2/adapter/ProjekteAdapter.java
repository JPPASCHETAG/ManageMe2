package com.wgabrechnung.manageme2.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.database.DatabaseKonto;
import com.wgabrechnung.manageme2.ui.dialogs.DialogEditProjekt;
import com.wgabrechnung.manageme2.ui.dialogs.DialogSortUmsatz;
import com.wgabrechnung.manageme2.ui.projekt.projektFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ProjekteAdapter extends RecyclerView.Adapter<ProjeketeViewHolder> {


    private static ArrayList<String[]> arrayList;
    private final FragmentManager fragmentManager;
    private final Context context;
    private final RecyclerView recyclerView;

    public static void setArrayList(ArrayList<String[]> list){
        arrayList = list;
    }


    public ProjekteAdapter(ArrayList<String[]> liste, FragmentManager fragmentManager, Context con, RecyclerView recView) {
        this.arrayList = liste;
        this.fragmentManager = fragmentManager;
        this.context = con;
        this.recyclerView = recView;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.projekete_list_item;
    }

    @NonNull
    @Override
    public ProjeketeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ProjeketeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProjeketeViewHolder holder, int position) {

        String[] projekt = arrayList.get(position);

        DatabaseKonto dbKonto = new DatabaseKonto(context);
        String betrag = dbKonto.getProjektBetrag(projekt[3]);
        betrag +="€";

        String name = arrayList.get(position)[0];
        //String betrag = arrayList.get(position)[1];
        String img = arrayList.get(position)[2];


        holder.getViewBetrag().setText(betrag);
        holder.getViewName().setText(name);
        holder.getViewImg().setImageResource(getResId(img,R.drawable.class));

        int ProjektId = Integer.parseInt(projekt[3]);

        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.right_enter, R.anim.right_exit);
                projektFragment newFragment = projektFragment.newInstance(projekt[0],ProjektId);
                ft.replace(R.id.nav_host_fragment, newFragment, "detailFragment").addToBackStack( "ProjektFragment" );
                // Start the animated transition.
                ft.commit();

            }
        });

        holder.getCardView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                DialogEditProjekt dialog = new DialogEditProjekt(context,ProjektId,recyclerView,fragmentManager);
                dialog.show();

                return false;
            }
        });


    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
