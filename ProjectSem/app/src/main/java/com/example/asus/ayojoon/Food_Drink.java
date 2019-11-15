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

public class Food_Drink extends AppCompatActivity {


    private DatabaseReference productref_drink;
    private RecyclerView recyclerView_drink;
    RecyclerView.LayoutManager layoutmanager_drink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__drink);

        productref_drink = FirebaseDatabase.getInstance().getReference().child("Catering").child("Drinks") ;
        recyclerView_drink = findViewById(R.id.recycler_food_drink) ;
        layoutmanager_drink = new LinearLayoutManager(this) ;
        recyclerView_drink.setHasFixedSize(true);
        recyclerView_drink.setLayoutManager(layoutmanager_drink);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_starter =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_drink,Products.class).build() ;

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

        recyclerView_drink.setAdapter(adapter_starter);
        adapter_starter.startListening();
    }
}