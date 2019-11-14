package com.example.asus.ayojoon;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class NameHistory extends AppCompatActivity {

    Button buttonforhome ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_name_history);
        getSupportActionBar().hide();
        buttonforhome=(Button)findViewById(R.id.buttonforhistory) ;

        buttonforhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivityHomeboi(); ;
            }
        });


    }
    public  void openActivityHomeboi ()
    {
        Intent intent = new Intent(this,HomePage.class) ;
        startActivity(intent);
    }


}
