package com.example.asus.ayojoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Food_Dessert extends AppCompatActivity {


    private DatabaseReference productref_dessert;
    private RecyclerView recyclerView_dessert;
    RecyclerView.LayoutManager layoutmanager_dessert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__dessert);

        productref_dessert = FirebaseDatabase.getInstance().getReference().child("Catering").child("Dessert") ;
        recyclerView_dessert = findViewById(R.id.recycler_food_dessert);
        layoutmanager_dessert = new LinearLayoutManager(this) ;
        recyclerView_dessert.setHasFixedSize(true);
        recyclerView_dessert.setLayoutManager(layoutmanager_dessert);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_starter =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_dessert,Products.class).build() ;

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_starter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_starter) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_starter, int position, @NonNull Products model_starter) {

                holder_starter.txtname.setText(model_starter.getTitle());
                holder_starter.txtdesc.setText(model_starter.getDescription());
                Picasso.get().load(model_starter.getImage()).into(holder_starter.imageView) ;


            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view_starter = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_items_layout,parent,false) ;
                ProductViewHolder holder_photo = new ProductViewHolder(view_starter) ;
                return holder_photo ;
            }
        } ;

        recyclerView_dessert.setAdapter(adapter_starter);
        adapter_starter.startListening();
    }
}