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

public class Ayoojon_Photo extends AppCompatActivity {


    private DatabaseReference productref ;
    private RecyclerView recyclerView ;
    RecyclerView.LayoutManager layoutmanager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__photo);

        productref = FirebaseDatabase.getInstance().getReference().child("Photography") ;
        recyclerView = findViewById(R.id.recycler_menu) ;
        layoutmanager = new LinearLayoutManager(this) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutmanager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products>options =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref,Products.class).build() ;

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model) {

                holder.txtname.setText(model.getTitle());
                holder.txtdesc.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder.imageView) ;


            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_items_layout,parent,false) ;
                ProductViewHolder holder = new ProductViewHolder(view) ;
                return holder ;
            }
        } ;

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
