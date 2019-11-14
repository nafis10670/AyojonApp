package com.example.asus.ayojoon;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText nameEdit,userEdit,genderEdit,phoneEdit,passEdit ;
    private Button buttonreg ;
    Userinfo userinfo ;
    DatabaseHelper databaseHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        databaseHelper = new DatabaseHelper(this) ;
        SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase() ;

        nameEdit=(EditText)findViewById(R.id.nameeditText4) ;
        userEdit=(EditText)findViewById(R.id.usereditText8) ;

        genderEdit=(EditText)findViewById(R.id.gendereditText9) ;

        phoneEdit=(EditText)findViewById(R.id.phoneeditText5) ;

        passEdit=(EditText)findViewById(R.id.passeditText6) ;
        buttonreg=(Button) findViewById(R.id.buttonregister) ;



        userinfo = new Userinfo() ;

        buttonreg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String Name = nameEdit.getText().toString() ;
        String User = userEdit.getText().toString() ;
        String Gender = genderEdit.getText().toString() ;
        String Phonenumber = phoneEdit.getText().toString() ;
        String Password = passEdit.getText().toString() ;

        userinfo.setName(Name);
        userinfo.setUser(User);
        userinfo.setGender(Gender);
        userinfo.setPhone(Phonenumber);
        userinfo.setPassword(Password);

        long rowId= databaseHelper.insertData(userinfo) ;


        if (rowId>-1)
        {
            Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();
            openActivitySign();
        }

        else
        {
            Toast.makeText(getApplicationContext(),"Not Registered",Toast.LENGTH_LONG).show();

        }



    }

    public  void openActivitySign ()
    {
        Intent intent = new Intent(this,Sign.class) ;
        startActivity(intent);
    }


}