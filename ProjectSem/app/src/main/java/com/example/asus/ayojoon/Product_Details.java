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

    private ImageView productimage;
    private TextView prodprice, prodname, proddesc, progressbar;
    private static SeekBar seekbar;
    private String productID = " ";
    private String ChildName ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);
        productID = getIntent().getStringExtra("pid");
        prodprice = (TextView) findViewById(R.id.price);
        prodname = (TextView) findViewById(R.id.product_name);
        proddesc = (TextView) findViewById(R.id.product_name_details);
        productimage = (ImageView) findViewById(R.id.product_image_details);
        Toast.makeText(getApplicationContext(), "Dhukse to pera", Toast.LENGTH_LONG).show();

        getProductDetailsPhoto(productID);
        seebbbar();


    }

    private void getProductDetailsPhoto(final String productID) {



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

        }
        else if (productID.startsWith("M"))
        {
            ChildName = "Dessert" ;

        }



        DatabaseReference ProductsPhotoRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference somethingref = ProductsPhotoRef.child(ChildName).child(productID);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    Products products_photo = dataSnapshot.getValue(Products.class);
                    prodname.setText(products_photo.getTitle());
                    prodprice.setText("Price: "+products_photo.getPayment()+" TK");
                    proddesc.setText(products_photo.getDescription());
                    Picasso.get().load(products_photo.getImage()).into(productimage);

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
