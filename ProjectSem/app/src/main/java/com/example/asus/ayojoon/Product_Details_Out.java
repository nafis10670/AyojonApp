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

public class Product_Details_Out extends AppCompatActivity {

    Button buttonforaddout ;
    private ImageView productimage;
    private TextView prodprice, prodname, proddesc, progressbar,statusTextview;
    private String productID = " ";
    private String ChildName ;
    private int insquantity ;
    private String insname ,insprice ;
    private String version ;
    private long maxid = 0 ;
    private String stat = "available" ;
String check ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details__out);
        productID = getIntent().getStringExtra("pid");
        prodprice = (TextView) findViewById(R.id.price_out);
        statusTextview = (TextView) findViewById(R.id.statusview);
        prodname = (TextView) findViewById(R.id.product_name_out);
        proddesc = (TextView) findViewById(R.id.product_name_details_out);
        productimage = (ImageView) findViewById(R.id.product_image_details_out);
        buttonforaddout = (Button) findViewById(R.id.buttonforaddingtocart_out) ;
        getProductDetailsPhoto_out(productID);


        CreateOwn create = new CreateOwn () ;
        check = create.version ;

        final Products   insertproducts = new Products() ;
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("CartList") ;
        final DatabaseReference anotherref = cartListRef.child(check) ;






        buttonforaddout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertproducts.setPid(productID);
                insertproducts.setPayment(insprice);
                insertproducts.setQuantity(insquantity);
                insertproducts.setTitle(insname);

                anotherref.child(productID).setValue(insertproducts) ;

                Toast.makeText(getApplicationContext(), "Added To List", Toast.LENGTH_LONG).show();


            }
        });




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



                 //Toast.makeText(getApplicationContext(), "Dhuke nai", Toast.LENGTH_LONG).show();
                    Products products_photo = dataSnapshot.getValue(Products.class);


                  if (productID.startsWith("V"))
                    {
                        stat = products_photo.getStatus() ;
                        if(stat.startsWith("a"))
                        {
                            buttonforaddout.setEnabled(true);
                        }
                        else
                        {
                            buttonforaddout.setEnabled(false);
                        }

                    }
                  else
                  {
                      buttonforaddout.setEnabled(true);
                  }


                    insquantity = 1 ;
                    insname = products_photo.getTitle() ;
                    insprice = products_photo.getPayment() ;
                    prodname.setText(products_photo.getTitle());
                    prodprice.setText("Price: "+products_photo.getPayment()+" TK");
                    statusTextview.setText("Status: "+stat);

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
