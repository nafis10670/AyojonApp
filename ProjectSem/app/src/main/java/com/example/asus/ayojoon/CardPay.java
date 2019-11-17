package com.example.asus.ayojoon;

import android.app.Dialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CardPay extends AppCompatActivity {
   public static String version = "P1";
    Button canc , confi ;
    Button makepayment ;
    Dialog mydialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_pay);

    makepayment = (Button)findViewById(R.id.buttonforcard) ;
    makepayment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        showmydialog();
        }
    });


    }

    public void showmydialog ()
    {



        mydialog= new Dialog(CardPay.this) ;
        mydialog.setContentView(R.layout.showalertbox);
        mydialog.setTitle("Confirm");
        canc=(Button)mydialog.findViewById(R.id.can) ;
        confi=(Button)mydialog.findViewById(R.id.con) ;
        canc.setEnabled(true);
        confi.setEnabled(true);
        mydialog.setCanceledOnTouchOutside(false);
        mydialog.setCancelable(false) ;


        confi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                mydialog.cancel();
                Toast.makeText(getApplicationContext(), "Payment Completed", Toast.LENGTH_LONG).show();




            }
        });
        canc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.cancel();
            }
        });




        mydialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(CardPay.this, CreateOwn.class);

        startActivity(intent);


    }
}
