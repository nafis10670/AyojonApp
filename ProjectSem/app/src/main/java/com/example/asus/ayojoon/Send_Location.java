package com.example.asus.ayojoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Send_Location extends AppCompatActivity {

    Button buttonforlocation  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__location);


        buttonforlocation = (Button)findViewById(R.id.buttonforlocation) ;
        buttonforlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Send_Location.this, HomePage.class);

                startActivity(intent);
            }
        });


    }
}
