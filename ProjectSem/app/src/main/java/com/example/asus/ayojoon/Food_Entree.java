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

public class Food_Entree extends AppCompatActivity {


    private DatabaseReference productref_entree;
    private RecyclerView recyclerView_entree;
    RecyclerView.LayoutManager layoutmanager_entree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__entree);

        productref_entree = FirebaseDatabase.getInstance().getReference().child("Catering").child("Entree") ;
        recyclerView_entree = findViewById(R.id.recycler_food_entree) ;
        layoutmanager_entree = new LinearLayoutManager(this) ;
        recyclerView_entree.setHasFixedSize(true);
        recyclerView_entree.setLayoutManager(layoutmanager_entree);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_starter =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_entree,Products.class).build() ;

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

        recyclerView_entree.setAdapter(adapter_starter);
        adapter_starter.startListening();
    }
}