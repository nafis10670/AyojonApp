package com.example.asus.ayojoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;

public class Product_Details extends AppCompatActivity {

    Button buttonforadd;

    private ImageView productimagedet;
    private TextView prodpricedet, prodnamedet, proddescdet, progressbar;
    private static SeekBar seekbar;
    private String productID = " ";
    private String ChildName;
    static int progress_value ;
    private int insquantity ;
    private String insname ,insprice ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);
        productID = getIntent().getStringExtra("pid");
        prodpricedet = (TextView) findViewById(R.id.price);
        prodnamedet = (TextView) findViewById(R.id.product_name);
        proddescdet = (TextView) findViewById(R.id.product_name_details);
        productimagedet = (ImageView) findViewById(R.id.product_image_details);
        buttonforadd = (Button) findViewById(R.id.buttonforaddingtocart);

        Toast.makeText(getApplicationContext(), "Dhukse to pera", Toast.LENGTH_LONG).show();

        getProductDetailsPhoto(productID);
        seebbbar();


      final Products   insertproducts = new Products() ;
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("CartList") ;



        buttonforadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertproducts.setTitle(insname);
                insertproducts.setPid(productID);
                insertproducts.setPayment(insprice);
                insertproducts.setQuantity(insquantity);

                cartListRef.child(productID).setValue(insertproducts) ;



                Toast.makeText(getApplicationContext(), "Data Inserted"+insquantity, Toast.LENGTH_LONG).show();





            }
        });


    }


  public void getProductDetailsPhoto(final String productID) {

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
                    insname = products_details.getTitle() ;
                    insprice = products_details.getPayment() ;
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

       insquantity = seekbar.getProgress() ;


        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {


                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {




                        progress_value = progress ;
                        progressbar.setText("Quantity: "+progress_value+" / "+seekbar.getMax());


                        Toast.makeText(getApplicationContext(), "CHeck:"+progress_value, Toast.LENGTH_LONG).show();







                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {


                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                        insquantity = progress_value ;

                    }
                }

        );

    }

}
