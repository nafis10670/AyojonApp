package com.example.asus.ayojoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class Catering extends AppCompatActivity {

    GridLayout gridvenue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering);





        gridvenue = (GridLayout) findViewById(R.id.maingridforvenue);


        setSingleEvent(gridvenue);



    }


    private void setSingleEvent(GridLayout gridvenue) {
        //Loop all child item of Main Grid
        for (int i = 0; i < gridvenue.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) gridvenue.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI==0) {
                        Intent intent = new Intent(Catering.this, Send_Location.class);
                        startActivity(intent);
                    }
                    if (finalI==1) {
                        Intent intent = new Intent(Catering.this, Ayoojon_Venue.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }


}
