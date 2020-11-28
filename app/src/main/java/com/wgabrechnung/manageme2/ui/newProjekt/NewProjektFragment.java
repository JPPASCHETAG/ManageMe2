package com.wgabrechnung.manageme2.ui.newProjekt;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.database.DatabaseProjekte;
import com.wgabrechnung.manageme2.ui.projekte.GalleryFragment;
import com.wgabrechnung.manageme2.ui.slideshow.SlideshowFragment;

public class NewProjektFragment extends Fragment {

    private NewProjektViewModel mViewModel;

    Button btnPrevImg;
    Button btnNextImg;
    TextView ViewBez;
    ImageView imgView;
    EditText inputBez;
    EditText inputBetrag;
    Button btnSave;
    TextView ViewIntImg;

    public static NewProjektFragment newInstance() {
        return new NewProjektFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.new_projekt_fragment, container, false);

        btnPrevImg = root.findViewById(R.id.btnPrev);
        btnNextImg = root.findViewById(R.id.btnNext);
        ViewBez = root.findViewById(R.id.viewBez);
        imgView = root.findViewById(R.id.projektImg);
        inputBez = root.findViewById(R.id.inputBez);
        inputBetrag = root.findViewById(R.id.inputBetrag);
        btnSave = root.findViewById(R.id.btnSave);
        ViewIntImg  = root.findViewById(R.id.ViewIntImg);

        //werte für den Imagepicker setzen
        setImage(1);
        ViewIntImg.setText("1");

        //damit sich der Text oben ändert
        inputBez.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ViewBez.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Button für Imagepicker
        btnPrevImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int img = Integer.parseInt(ViewIntImg.getText().toString());
                img = img-1;
                if(img == 0){
                    img = 15;
                }
                setImage(img);
                ViewIntImg.setText(String.valueOf(img));
            }
        });

        //Button für Imagepicker
        btnNextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ViewIntImg  = root.findViewById(R.id.ViewIntImg);
                int img = Integer.parseInt(ViewIntImg.getText().toString());
                img = img+1;
                if(img == 15){
                    img = 1;
                }
                setImage(img);
                ViewIntImg.setText(String.valueOf(img));
            }
        });



        //Projekt speichern
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseProjekte db = new DatabaseProjekte(root.getContext());

                String strBetrag = inputBetrag.getText().toString();
                double betrag;
                if(strBetrag.equals("")){
                    betrag = 0.00;
                }else{
                    betrag = Double.parseDouble(strBetrag);
                }
                String bez = inputBez.getText().toString();
                int imgID = Integer.parseInt(ViewIntImg.getText().toString());
                String img = getImageId(imgID);

                db.addDataset(betrag,bez,img);

                Toast.makeText(root.getContext(),"Projekt erfolgreich angelegt!", Toast.LENGTH_LONG).show();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.right_enter, R.anim.right_exit);
                GalleryFragment newFragment = GalleryFragment.newInstance();
                ft.replace(R.id.nav_host_fragment, newFragment, "ProjekteFragment");
                // Start the animated transition.
                ft.commit();

            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewProjektViewModel.class);
        // TODO: Use the ViewModel
    }

    /**
     * ändert das Bild gemäß der übergeben nummer
     * @param number int
     */
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