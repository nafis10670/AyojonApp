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

public class Ayoojon_Car extends AppCompatActivity {

    private DatabaseReference productref_car;
    private RecyclerView recyclerView_car;
    RecyclerView.LayoutManager layoutmanager_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__car);

        productref_car = FirebaseDatabase.getInstance().getReference().child("Car") ;
        recyclerView_car = findViewById(R.id.recycler_menu_car) ;
        layoutmanager_car = new LinearLayoutManager(this) ;
        recyclerView_car.setHasFixedSize(true);
        recyclerView_car.setLayoutManager(layoutmanager_car);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_car =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_car, Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_car = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_car) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_car, int position, @NonNull final Products model) {

                holder_car.txtname.setText(model.getTitle());
                holder_car.txtdesc.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder_car.imageView);


                holder_car.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Ayoojon_Car.this, Product_Details_Out.class);
                        intent.putExtra("pid",model.getPid()) ;
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view_car = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_items_layout, parent, false);
                ProductViewHolder holder_car = new ProductViewHolder(view_car);
                return holder_car;



            }
        };


        recyclerView_car.setAdapter(adapter_car);
        adapter_car.startListening();
    }
}
