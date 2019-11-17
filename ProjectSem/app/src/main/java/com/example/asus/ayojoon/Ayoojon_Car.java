package com.example.asus.ayojoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Ayoojon_Car extends AppCompatActivity {

    private DatabaseReference productref_car;
    private RecyclerView recyclerView_car;
    private SearchView searchView;
    RecyclerView.LayoutManager layoutmanager_car;
    ArrayList<Products> list;
    FirebaseRecyclerOptions<Products> options_car;
    FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__car);

        productref_car = FirebaseDatabase.getInstance().getReference().child("Car") ;
        recyclerView_car = findViewById(R.id.recycler_menu_car) ;
        layoutmanager_car = new LinearLayoutManager(this) ;
        recyclerView_car.setHasFixedSize(true);
        recyclerView_car.setLayoutManager(layoutmanager_car);
        searchView = findViewById(R.id.search_car);

        productref_car.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    list = new ArrayList<>();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        list.add(ds.getValue(Products.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        options_car =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_car, Products.class).build();

        adapter_car = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_car) {
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

        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str){


//        System.out.println("SEARCH STRING **************"+str);
//        ArrayList<Products> myList = new ArrayList<>();
//        for(Products object: list ){
//            for(DataSnapshot ds : dataSnapshot.getChildren()){
//                list.add(ds.getValue(Products.class));
//            }
//        }


        options_car =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_car.orderByChild("title").startAt(str).endAt(str + "\uf8ff"), Products.class).build();
        adapter_car = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_car) {
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
