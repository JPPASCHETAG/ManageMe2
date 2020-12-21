package com.wgabrechnung.manageme2.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.adapter.ProjekteAdapter;
import com.wgabrechnung.manageme2.database.DatabaseKonto;
import com.wgabrechnung.manageme2.database.DatabaseProjekte;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DialogEditProjekt extends Dialog {

    private final int ProjektID;
    public ImageView imgView;
    public TextView imgID;
    public EditText editHeader;
    public RecyclerView recyclerView;
    public FragmentManager fragmentManager;

    public DialogEditProjekt(@NonNull Context context, int projektID, RecyclerView recView,FragmentManager manager) {
        super(context);
        this.ProjektID = projektID;
        this.recyclerView = recView;
        this.fragmentManager = manager;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_editprojekt_layout);

        //Projekt Daten holen
        DatabaseProjekte db = new DatabaseProjekte(getContext());
        ArrayList<String[]> ListProjekt = db.getProjektData(ProjektID);
        String[] projekt = ListProjekt.get(0);

        //Felder setzen
        TextView header = this.findViewById(R.id.projektEditViewBez);
        header.setText(projekt[0]);

        editHeader = this.findViewById(R.id.projektEditEditBez);
        editHeader.setText(projekt[0]);

        imgID = this.findViewById(R.id.EditProjektViewIntImg);
        imgID.setText(getImageIdByName(projekt[2]));

        this.imgView = this.findViewById(R.id.projektEditImg);
        imgView.setImageResource(getResId(projekt[2],R.drawable.class));


        Button btnSave = this.findViewById(R.id.EditProjektBtnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bez = editHeader.getText().toString();
                String img = getImageId(Integer.parseInt(imgID.getText().toString()));

                DatabaseProjekte db = new DatabaseProjekte(getContext());
                db.updateProjekt(bez,img,ProjektID);

                Toast.makeText(getContext(),"Projekt gespeichert!",Toast.LENGTH_SHORT).show();
                dismiss();

                ArrayList<String[]> projekte = db.getAllData();
                recyclerView.setAdapter(new ProjekteAdapter(projekte,fragmentManager,getContext(),recyclerView));

            }
        });

        Button btnDelete = this.findViewById(R.id.EditProjektBtnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseProjekte db = new DatabaseProjekte(getContext());
                db.deleteProjekt(ProjektID);
                DatabaseKonto konto = new DatabaseKonto(getContext());
                konto.deleteProjekt(ProjektID);

                Toast.makeText(getContext(),"Projekt gelöscht!",Toast.LENGTH_SHORT).show();
                dismiss();

                ArrayList<String[]> projekte = db.getAllData();
                recyclerView.setAdapter(new ProjekteAdapter(projekte,fragmentManager,getContext(),recyclerView));

            }
        });

        //Button für Imagepicker
        Button btnPrevImg = this.findViewById(R.id.EditProjektBtnPrev);
        btnPrevImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int img = Integer.parseInt(imgID.getText().toString());
                img = img-1;
                if(img == 0){
                    img = 15;
                }
                setImage(img);
                imgID.setText(String.valueOf(img));
            }
        });

        //Button für Imagepicker
        Button btnNextImg = this.findViewById(R.id.EditProjektBtnNext);
        btnNextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int img = Integer.parseInt(imgID.getText().toString());
                img = img+1;
                if(img == 15){
                    img = 1;
                }
                setImage(img);
                imgID.setText(String.valueOf(img));
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

    public void setImage(int number){
        switch (number) {
            case 1:
                imgView.setImageResource(R.drawable.undraw_at_home_octe);
                break;
            case 2:
                imgView.setImageResource(R.drawable.undraw_bike_ride_7xit);
                break;
            case 3:
                imgView.setImageResource(R.drawable.undraw_birthday_girl_n46w);
                break;
            case 4:
                imgView.setImageResource(R.drawable.undraw_breakfast_psiw);
                break;
            case 5:
                imgView.setImageResource(R.drawable.undraw_camera_re_cnp4);
                break;
            case 6:
                imgView.setImageResource(R.drawable.undraw_camping_noc8);
                break;
            case 7:
                imgView.setImageResource(R.drawable.undraw_gone_shopping_vwmc);
                break;
            case 8:
                imgView.setImageResource(R.drawable.undraw_nature_m5ll);
                break;
            case 9:
                imgView.setImageResource(R.drawable.undraw_outdoor_party_oqh3);
                break;
            case 10:
                imgView.setImageResource(R.drawable.undraw_personal_training_0dqn);
                break;
            case 11:
                imgView.setImageResource(R.drawable.undraw_refreshing_beverage_td3r);
                break;
            case 12:
                imgView.setImageResource(R.drawable.undraw_savings_re_eq4w);
                break;
            case 13:
                imgView.setImageResource(R.drawable.undraw_shopping_app_flsj);
                break;
            case 14:
                imgView.setImageResource(R.drawable.undraw_to_do_list_re_9nt7);
                break;
            case 15:
                imgView.setImageResource(R.drawable.undraw_with_love_ajy1);
                break;
        }
    }

    public String getImageIdByName(String img){
        String strReturn = "";
        switch (img) {
            case "undraw_at_home_octe":
                strReturn =  "1";
                break;
            case  "undraw_bike_ride_7xit":
                strReturn =  "2";
                break;
            case  "undraw_birthday_girl_n46w":
                strReturn =  "3";
                break;
            case  "undraw_breakfast_psiw":
                strReturn =  "4";
                break;
            case  "undraw_camera_re_cnp4":
                strReturn =  "5";
                break;
            case  "undraw_camping_noc8":
                strReturn =  "6";
                break;
            case  "undraw_gone_shopping_vwmc":
                strReturn =  "7";
                break;
            case  "undraw_nature_m5ll":
                strReturn =  "8";
                break;
            case  "undraw_outdoor_party_oqh3":
                strReturn =  "9";
                break;
            case "undraw_personal_training_0dqn":
                strReturn =  "10";
                break;
            case "undraw_refreshing_beverage_td3r":
                strReturn =  "11";
                break;
            case "undraw_savings_re_eq4w":
                strReturn =  "12";
                break;
            case "undraw_shopping_app_flsj":
                strReturn =  "13";
                break;
            case "undraw_to_do_list_re_9nt7":
                strReturn =  "14";
                break;
            case "undraw_with_love_ajy1":
                strReturn =  "15";
                break;
        }
        return strReturn;
    }

    public String getImageId(int number){
        String strReturn = "";
        switch (number) {
            case 1:
                strReturn =  "undraw_at_home_octe";
                break;
            case 2:
                strReturn =  "undraw_bike_ride_7xit";
                break;
            case 3:
                strReturn =  "undraw_birthday_girl_n46w";
                break;
            case 4:
                strReturn =  "undraw_breakfast_psiw";
                break;
            case 5:
                strReturn =  "undraw_camera_re_cnp4";
                break;
            case 6:
                strReturn =  "undraw_camping_noc8";
                break;
            case 7:
                strReturn =  "undraw_gone_shopping_vwmc";
                break;
            case 8:
                strReturn =  "undraw_nature_m5ll";
                break;
            case 9:
                strReturn =  "undraw_outdoor_party_oqh3";
                break;
            case 10:
                strReturn =  "undraw_personal_training_0dqn";
                break;
            case 11:
                strReturn =  "undraw_refreshing_beverage_td3r";
                break;
            case 12:
                strReturn =  "undraw_savings_re_eq4w";
                break;
            case 13:
                strReturn =  "undraw_shopping_app_flsj";
                break;
            case 14:
                strReturn =  "undraw_to_do_list_re_9nt7";
                break;
            case 15:
                strReturn =  "undraw_with_love_ajy1";
                break;
        }
        return strReturn;
    }

}
