package com.example.asus.ayojoon;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private  Button button,button2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        button = (Button) findViewById(R.id.button) ;
        button2=(Button) findViewById(R.id.buttonnene) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReg();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivitySign() ;
            }
        });
    }


    public  void openActivitySign ()
    {
        Intent intent = new Intent(this,Sign.class) ;
        startActivity(intent);
    }

    public void openReg()
    {
        Intent intent = new Intent(this,Register.class) ;
        startActivity(intent);

    }

}