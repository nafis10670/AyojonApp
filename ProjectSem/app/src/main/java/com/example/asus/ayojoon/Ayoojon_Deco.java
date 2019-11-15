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

public class Ayoojon_Deco extends AppCompatActivity {

    private DatabaseReference productref_deco;
    private RecyclerView recyclerView_deco;
    RecyclerView.LayoutManager layoutmanager_deco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__deco);

        productref_deco = FirebaseDatabase.getInstance().getReference().child("Decoration") ;
        recyclerView_deco = findViewById(R.id.recycler_menu_deco) ;
        layoutmanager_deco = new LinearLayoutManager(this) ;
        recyclerView_deco.setHasFixedSize(true);
        recyclerView_deco.setLayoutManager(layoutmanager_deco);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_deco =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_deco, Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_car = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_deco) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_deco, int position, @NonNull Products model) {

                holder_deco.txtname.setText(model.getTitle());
                holder_deco.txtdesc.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder_deco.imageView);


            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view_deco = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_items_layout, parent, false);
                ProductViewHolder holder_deco = new ProductViewHolder(view_deco);
                return holder_deco;
            }
        };


        recyclerView_deco.setAdapter(adapter_car);
        adapter_car.startListening();
    }
}
