package com.example.asus.ayojoon;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class CreateOwn extends AppCompatActivity {

    Button buttonforcreate ;
    public static String version = "P0" ;

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

        String newVersion = "P" + (Integer.parseInt(version.substring(1,version.length()))+1);//Stringincrement
        version=newVersion ;
        Toast.makeText(getApplicationContext(),version,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,HomePage.class) ;
        startActivity(intent);
    }




}
