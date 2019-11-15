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

public class Food_Starter extends AppCompatActivity {


    private DatabaseReference productref_starter;
    private RecyclerView recyclerView_starter;
    RecyclerView.LayoutManager layoutmanager_starter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__starter);

        productref_starter = FirebaseDatabase.getInstance().getReference().child("Catering").child("Starter") ;
        recyclerView_starter = findViewById(R.id.recycler_food_starter) ;
        layoutmanager_starter = new LinearLayoutManager(this) ;
        recyclerView_starter.setHasFixedSize(true);
        recyclerView_starter.setLayoutManager(layoutmanager_starter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_starter =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_starter,Products.class).build() ;

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_starter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_starter) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_starter, int position, @NonNull final Products model_starter) {

                holder_starter.txtname.setText(model_starter.getTitle());
                holder_starter.txtdesc.setText(model_starter.getDescription());
                Picasso.get().load(model_starter.getImage()).into(holder_starter.imageView) ;
                holder_starter.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Food_Starter.this, Product_Details.class);
                        intent.putExtra("pid",model_starter.getPid()) ;
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view_starter = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_items_layout,parent,false) ;
                ProductViewHolder holder_photo = new ProductViewHolder(view_starter) ;
                return holder_photo ;
            }
        } ;

        recyclerView_starter.setAdapter(adapter_starter);
        adapter_starter.startListening();
    }
}