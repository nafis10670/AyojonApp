package com.example.asus.ayojoon;

import android.content.Intent;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HomePage extends AppCompatActivity {

    GridLayout grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        grid = (GridLayout) findViewById(R.id.maingrid);



        setSingleEvent(grid);

    }



    private void setSingleEvent(GridLayout grid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < grid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) grid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI==0) {
                        Intent intent = new Intent(HomePage.this, Ayoojon_Venue.class);
                        startActivity(intent);
                    }
                    if (finalI==1) {
                        Intent intent = new Intent(HomePage.this, Ayoojon_Catering.class);
                        startActivity(intent);
                    }
                    if (finalI==2) {
                        Intent intent = new Intent(HomePage.this, Ayoojon_Deco.class);
                        startActivity(intent);
                    }
                    if (finalI==3) {
                        Intent intent = new Intent(HomePage.this, Ayoojon_Music.class);
                        startActivity(intent);
                    }
                    if (finalI==4) {
                        Intent intent = new Intent(HomePage.this, Ayoojon_Photo.class);
                        startActivity(intent);
                    }
                    if (finalI==5) {
                        Intent intent = new Intent(HomePage.this, Ayoojon_Car.class);
                        startActivity(intent);
                    }

                }
            });
        }

    }

}