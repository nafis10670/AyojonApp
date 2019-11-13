package com.example.asus.ayojoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign extends AppCompatActivity implements  View.OnClickListener {

    private Button buttonsign;
    EditText userEdit,passEdit ;
    DatabaseHelper databaseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign);
        getSupportActionBar().hide();


        databaseHelper = new DatabaseHelper(this) ;
        userEdit=(EditText)findViewById(R.id.editTextuser) ;
        passEdit=(EditText)findViewById(R.id.editTextpas) ;
        buttonsign=(Button)findViewById(R.id.buttonsign) ;



        buttonsign.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String username = userEdit.getText().toString() ;
        String password= passEdit.getText().toString() ;

        if (v.getId()==R.id.buttonsign)
        {
            Boolean result = databaseHelper.findPassword(username,password) ;
            if(result==true)
            {
                Intent intent = new Intent(this,CreateOwn.class) ;
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Username and Password didn't match ",Toast.LENGTH_LONG).show();

            }

        }

    }


}