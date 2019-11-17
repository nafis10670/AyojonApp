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
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Ayoojon_Deco extends AppCompatActivity {

    private DatabaseReference productref_deco;
    private RecyclerView recyclerView_deco;
    private SearchView searchView_deco;
    RecyclerView.LayoutManager layoutmanager_deco;
    ArrayList<Products> list;
    FirebaseRecyclerOptions<Products> options_deco;
    FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_deco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__deco);

        productref_deco = FirebaseDatabase.getInstance().getReference().child("Decoration") ;
        recyclerView_deco = findViewById(R.id.recycler_menu_deco) ;
        layoutmanager_deco = new LinearLayoutManager(this) ;
        recyclerView_deco.setHasFixedSize(true);
        recyclerView_deco.setLayoutManager(layoutmanager_deco);
        searchView_deco = findViewById(R.id.search_deco);

        productref_deco.addValueEventListener(new ValueEventListener() {
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
        options_deco =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_deco, Products.class).build();

        adapter_deco = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_deco) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_car, int position, @NonNull final Products model) {

                holder_car.txtname.setText(model.getTitle());
                holder_car.txtdesc.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder_car.imageView);


                holder_car.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Ayoojon_Deco.this, Product_Details_Out.class);
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


        recyclerView_deco.setAdapter(adapter_deco);
        adapter_deco.startListening();

        if(searchView_deco != null){
            searchView_deco.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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


        options_deco =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_deco.orderByChild("title").startAt(str).endAt(str + "\uf8ff"), Products.class).build();
        adapter_deco = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_deco) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_car, int position, @NonNull final Products model) {

                holder_car.txtname.setText(model.getTitle());
                holder_car.txtdesc.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder_car.imageView);


                holder_car.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Ayoojon_Deco.this, Product_Details_Out.class);
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

        recyclerView_deco.setAdapter(adapter_deco);
        adapter_deco.startListening();

    }
}