package com.example.asus.ayojoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.ayojoon.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Ayoojon_Photo extends AppCompatActivity {


    private DatabaseReference productref_photo;
    private RecyclerView recyclerView_photo;
    RecyclerView.LayoutManager layoutmanager_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__photo);

        productref_photo = FirebaseDatabase.getInstance().getReference().child("Photography") ;
        recyclerView_photo = findViewById(R.id.recycler_menu) ;
        layoutmanager_photo = new LinearLayoutManager(this) ;
        recyclerView_photo.setHasFixedSize(true);
        recyclerView_photo.setLayoutManager(layoutmanager_photo);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_photo =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_photo,Products.class).build() ;

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_photo = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_photo) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_photo, int position, @NonNull final Products model) {

                holder_photo.txtname.setText(model.getTitle());
                holder_photo.txtdesc.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder_photo.imageView) ;

                holder_photo.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Ayoojon_Photo.this, Product_Details_Out.class);
                        intent.putExtra("pid",model.getPid()) ;
                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view_photo = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_items_layout,parent,false) ;
                ProductViewHolder holder_photo = new ProductViewHolder(view_photo) ;
                return holder_photo ;
            }
        } ;

        recyclerView_photo.setAdapter(adapter_photo);
        adapter_photo.startListening();
    }
}
