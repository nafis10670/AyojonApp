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

public class Ayoojon_Music extends AppCompatActivity {


    private DatabaseReference productref_music;
    private RecyclerView recyclerView_music;
    RecyclerView.LayoutManager layoutmanager_music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayoojon__music);

        productref_music = FirebaseDatabase.getInstance().getReference().child("Sound") ;
        recyclerView_music = findViewById(R.id.recycler_menu_music) ;
        layoutmanager_music = new LinearLayoutManager(this) ;
        recyclerView_music.setHasFixedSize(true);
        recyclerView_music.setLayoutManager(layoutmanager_music);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options_music =
                new FirebaseRecyclerOptions.Builder<Products>().setQuery(productref_music,Products.class).build() ;

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter_music = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options_music) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder_music, int position, @NonNull Products model) {

                holder_music.txtname.setText(model.getTitle());
                holder_music.txtdesc.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder_music.imageView) ;


            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view_music = LayoutInflater.from(parent.getContext()).inflate(R.layout.photography_items_layout,parent,false) ;
                ProductViewHolder holder_music = new ProductViewHolder(view_music) ;
                return holder_music ;
            }
        } ;

        recyclerView_music.setAdapter(adapter_music);
        adapter_music.startListening();
    }
}
