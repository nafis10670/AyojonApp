package com.example.asus.ayojoon;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class CreateOwn extends AppCompatActivity {

    Button buttonforcreate ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_own);
        getSupportActionBar().hide();
        buttonforcreate=(Button)findViewById(R.id.buttonforcreateevent) ;

        buttonforcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivityHistory() ;
            }
        });

    }

    public  void openActivityHistory ()
    {
        Intent intent = new Intent(this,HomePage.class) ;
        startActivity(intent);
    }




}
