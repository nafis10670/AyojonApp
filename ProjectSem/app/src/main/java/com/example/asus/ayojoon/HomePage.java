package com.example.asus.ayojoon;

import android.content.Intent;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePage extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    GridLayout grid;
    FloatingActionButton georgiewilfloat ;
    public String check ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        grid = (GridLayout) findViewById(R.id.maingrid);
        georgiewilfloat = (FloatingActionButton) findViewById(R.id.floating) ;



        georgiewilfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Shopping_Cart.class);

                startActivity(intent);
            }
        });



        setSingleEvent(grid);

    }
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();

            moveTaskToBack(true) ;
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1) ;



        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
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



                        Intent intent = new Intent(HomePage.this,Catering.class);
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