package com.example.asus.ayojoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Product_Details extends AppCompatActivity {

    private ImageView productimagedet;
    private TextView prodpricedet, prodnamedet, proddescdet, progressbar;
    private static SeekBar seekbar;
    private String productID = " ";
    private String ChildName ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);
        productID = getIntent().getStringExtra("pid");
        prodpricedet = (TextView) findViewById(R.id.price);
        prodnamedet = (TextView) findViewById(R.id.product_name);
        proddescdet = (TextView) findViewById(R.id.product_name_details);
        productimagedet = (ImageView) findViewById(R.id.product_image_details);
        Toast.makeText(getApplicationContext(), "Dhukse to pera", Toast.LENGTH_LONG).show();

        getProductDetailsPhoto(productID);
        seebbbar();


    }

    private void getProductDetailsPhoto(final String productID) {

        Toast.makeText(getApplicationContext(), productID, Toast.LENGTH_LONG).show();



        if(productID.startsWith("S"))
        {
            ChildName = "Starter" ;
        }
        else if (productID.startsWith("J"))
        {
            ChildName = "Drinks" ;

        }
        else if (productID.startsWith("T"))
        {
            ChildName = "Entree" ;
            Toast.makeText(getApplicationContext(), "ETAI SCIENCE", Toast.LENGTH_LONG).show();

        }
        else if (productID.startsWith("M"))
        {
            ChildName = "Dessert" ;

        }



        DatabaseReference ProductsPhotoRefdetails = FirebaseDatabase.getInstance().getReference("Catering");
        DatabaseReference naire = ProductsPhotoRefdetails.child(ChildName) ;
        DatabaseReference somethingref = naire.child(productID);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    Products products_details = dataSnapshot.getValue(Products.class);
                    prodnamedet.setText(products_details.getTitle());
                    prodpricedet.setText("Price: "+products_details.getPayment()+" TK");
                    proddescdet.setText(products_details.getDescription());
                    Picasso.get().load(products_details.getImage()).into(productimagedet);

                } else {
                    Toast.makeText(getApplicationContext(), "WRONGWRONGWRONG", Toast.LENGTH_LONG).show();

                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        };

        somethingref.addListenerForSingleValueEvent(eventListener);

    }


    public void seebbbar ()
    {
        seekbar = (SeekBar) findViewById(R.id.seekBarlayout) ;
        progressbar= (TextView)findViewById(R.id.seekbar_details) ;

        progressbar.setText("Quantity: "+seekbar.getProgress()+" / "+seekbar.getMax());
        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                     int progress_value ;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        progress_value = progress ;
                        progressbar.setText("Quantity: "+progress_value+" / "+seekbar.getMax());

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }

        );

    }

}
