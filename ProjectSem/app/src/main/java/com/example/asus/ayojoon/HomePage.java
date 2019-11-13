package com.example.asus.ayojoon;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.Toolbar;

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

                    Intent intent = new Intent(HomePage.this, Payement.class);
                    startActivity(intent);

                }
            });
        }

    }

}