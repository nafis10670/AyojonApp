package com.example.asus.ayojoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class Ayoojon_Catering extends AppCompatActivity {
    GridLayout grid_catering;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__catering);
        grid_catering = (GridLayout) findViewById(R.id.maingrid_food);



        setSingleEventfood(grid_catering);


    }

    private void setSingleEventfood(GridLayout grid_catering) {
        //Loop all child item of Main Grid
        for (int i = 0; i < grid_catering.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) grid_catering.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI==0) {
                        Intent intent = new Intent(Ayoojon_Catering.this, Food_Starter.class);
                        startActivity(intent);
                    }
                   else if (finalI==1) {
                        Intent intent = new Intent(Ayoojon_Catering.this, Food_Entree.class);
                        startActivity(intent);
                    }
                   else if (finalI==2) {
                        Intent intent = new Intent(Ayoojon_Catering.this, Food_Dessert.class);
                        startActivity(intent);
                    }
                 else  if (finalI==3) {
                        Intent intent = new Intent(Ayoojon_Catering.this, Food_Drink.class);
                        startActivity(intent);
                    }


                }
            });
        }

    }
}
