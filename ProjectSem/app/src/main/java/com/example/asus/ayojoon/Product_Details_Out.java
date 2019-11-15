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

public class Product_Details_Out extends AppCompatActivity {

    private ImageView productimage;
    private TextView prodprice, prodname, proddesc, progressbar;
    private String productID = " ";
    private String ChildName ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details__out);
        productID = getIntent().getStringExtra("pid");
        prodprice = (TextView) findViewById(R.id.price_out);
        prodname = (TextView) findViewById(R.id.product_name_out);
        proddesc = (TextView) findViewById(R.id.product_name_details_out);
        productimage = (ImageView) findViewById(R.id.product_image_details_out);
        Toast.makeText(getApplicationContext(), "Dhukse to pera", Toast.LENGTH_LONG).show();

        getProductDetailsPhoto_out(productID);


    }

    private void getProductDetailsPhoto_out(final String productID) {

        if(productID.startsWith("P"))
        {
            ChildName = "Photography" ;
        }
        else if (productID.startsWith("C"))
        {
            ChildName = "Car" ;

        }
        else if (productID.startsWith("D"))
        {
            ChildName = "Decoration" ;

        }
        else if (productID.startsWith("E"))
        {
            ChildName = "Entertainment" ;

        }
        else if (productID.startsWith("V"))
        {
            ChildName = "Venue" ;

        }

        DatabaseReference ProductsPhotoRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference somethingref = ProductsPhotoRef.child(ChildName).child(productID);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Dhukse mama ", Toast.LENGTH_LONG).show();

                    Products products_photo = dataSnapshot.getValue(Products.class);
                    prodname.setText(products_photo.getTitle());
                    prodprice.setText("Price: "+products_photo.getPayment()+" TK");
                    proddesc.setText(products_photo.getDescription());
                    Picasso.get().load(products_photo.getImage()).into(productimage);

                } else {
                    Toast.makeText(getApplicationContext(), "Dhuke nai", Toast.LENGTH_LONG).show();

                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        somethingref.addListenerForSingleValueEvent(eventListener);

    }




}
