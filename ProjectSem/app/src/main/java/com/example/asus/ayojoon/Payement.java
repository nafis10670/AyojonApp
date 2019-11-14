package com.example.asus.ayojoon;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Payement extends AppCompatActivity {
 Button buttonforpayment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payement);
    }

    public void Choosensocall (View view)
    {
        RadioGroup radioGroup = findViewById(R.id.radiog1) ;
        RadioGroup radioGroup1 = findViewById(R.id.radiog2) ;
        switch (radioGroup.getCheckedRadioButtonId())
        {
            case R.id.radioButton2:
                Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_LONG).show();
            case R.id.radioButton:
                Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_LONG).show();

        }
        switch (radioGroup1.getCheckedRadioButtonId())
        {
            case R.id.radioButton3:
                Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_LONG).show();
            case R.id.radioButton4:
                Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_LONG).show();

        }


        buttonforpayment=(Button)findViewById(R.id.buttonpay) ;

        buttonforpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivityCard(); ;
            }
        });


    }
    public  void openActivityCard ()
    {
        Intent intent = new Intent(this,CardPay.class) ;
        startActivity(intent);
    }


}
