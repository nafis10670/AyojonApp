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

public class Ayoojon_Venue extends AppCompatActivity {

    private DatabaseReference productref_venue;
    private RecyclerView recyclerView_venue;
    RecyclerView.LayoutManager layoutmanager_venue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__venue);

        productref_venue = FirebaseDatabase.getInstance().getReference().child("Venue") ;
        recyclerView_venue = findViewById(R.id.recycler_menu_venue) ;
        layoutmanager_venue = new LinearLayoutManager(this) ;
        recyclerView_venue.setHasFixedSize(true);
        recyclerView_venue.setLayoutManager(layoutmanager_venue);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_car =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_venue, Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_venue = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_car) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_venue, int position, @NonNull final Products model) {

                holder_venue.txtname.setText(model.getTitle());
                holder_venue.txtdesc.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder_venue.imageView);


                holder_venue.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Ayoojon_Venue.this, Product_Details_Out.class);
                        intent.putExtra("pid",model.getPid()) ;
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view_venue = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_items_layout, parent, false);
                ProductViewHolder holder_car = new ProductViewHolder(view_venue);
                return holder_car;



            }
        };


        recyclerView_venue.setAdapter(adapter_venue);
        adapter_venue.startListening();
    }
}
