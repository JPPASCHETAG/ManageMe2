package com.wgabrechnung.manageme2.ui.konto;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;

import com.wgabrechnung.manageme2.R;
import com.wgabrechnung.manageme2.database.DatabaseProjekte;

import java.util.List;

public class DialogSortUmsatz extends Dialog implements android.view.View.OnClickListener {

    public Button yes;
    private final List<String[]> projektList;

    private TextView textView;
    private ImageView imageView;
    private int currProjekt = 0;


    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     *
     */
    public DialogSortUmsatz(@NonNull Context context) {
        super(context);
        DatabaseProjekte db = new DatabaseProjekte(getContext());
        projektList = db.getAllData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_sort_umsatz);
        yes = (Button) findViewById(R.id.btn_yes);
        yes.setOnClickListener(this);

        textView = findViewById(R.id.text_dialog);
        imageView = findViewById(R.id.dialog_projekt_image);

    }


    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    if(x2 > x1){

                        currProjekt++;
                        if(currProjekt >= projektList.size()){
                            currProjekt = 0;
                        }

                        String[] projekt = projektList.get(currProjekt);

                        textView.setText(projekt[0]);
                        imageView.setImageResource(getImageID(projekt[2]));

                    }else{
                        currProjekt--;
                        if(currProjekt < 0){
                            currProjekt = projektList.size();
                            currProjekt--;
                        }

                        String[] projekt = projektList.get(currProjekt);

                        textView.setText(projekt[0]);
                        imageView.setImageResource(getImageID(projekt[2]));
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        System.out.println(v.getId());

        if (v.getId() == R.id.btn_yes) {
            System.out.println("save");
        }

    }


    public int getImageID(String bild){
        switch (bild) {
            case "undraw_at_home_octe":
                return R.drawable.undraw_at_home_octe;
            case "undraw_bike_ride_7xit":
                return R.drawable.undraw_bike_ride_7xit;
            case "undraw_birthday_girl_n46w":
                return R.drawable.undraw_birthday_girl_n46w;
            case "undraw_breakfast_psiw":
                return R.drawable.undraw_breakfast_psiw;
            case "undraw_camera_re_cnp4":
                return R.drawable.undraw_camera_re_cnp4;
            case "undraw_camping_noc8":
                return R.drawable.undraw_camping_noc8;
            case "undraw_gone_shopping_vwmc":
                return R.drawable.undraw_gone_shopping_vwmc;
            case "undraw_nature_m5ll":
                return R.drawable.undraw_nature_m5ll;
            case "undraw_outdoor_party_oqh3":
                return R.drawable.undraw_outdoor_party_oqh3;
            case "undraw_personal_training_0dqn":
                return R.drawable.undraw_personal_training_0dqn;
            case "undraw_refreshing_beverage_td3r":
                return R.drawable.undraw_refreshing_beverage_td3r;
            case "undraw_savings_re_eq4w":
                return R.drawable.undraw_savings_re_eq4w;
            case "undraw_shopping_app_flsj":
                return R.drawable.undraw_shopping_app_flsj;
            case "undraw_to_do_list_re_9nt7":
                return R.drawable.undraw_to_do_list_re_9nt7;
            case "undraw_with_love_ajy1":
                return R.drawable.undraw_with_love_ajy1;
            default:
                return R.drawable.undraw_at_home_octe;
        }
    }




}


