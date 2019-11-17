package com.example.asus.ayojoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.ayojoon.ViewHolder.CartViewHolder;
import com.example.asus.ayojoon.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Shopping_Cart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessBtn;
    private TextView txtTotal;
    private int totalprice=0 ;
    String check ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping__cart);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        NextProcessBtn = (Button) findViewById(R.id.next_process_button);
        txtTotal = (TextView) findViewById(R.id.total_price);

        CreateOwn create = new CreateOwn () ;
        check = create.version ;
        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shopping_Cart.this, DateTimePicker.class);
                intent.putExtra("Total Price",String.valueOf(totalprice)) ;
                startActivity(intent);
            }
        });










    }
    @Override
    protected void onStart() {
        super.onStart();


        final DatabaseReference Cart_showitem = FirebaseDatabase.getInstance().getReference("CartList");

        FirebaseRecyclerOptions<Cart> options_cart =
                new FirebaseRecyclerOptions.Builder<Cart>().setQuery(Cart_showitem.child(check),Cart.class).build() ;

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options_cart) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int position, @NonNull final Cart model) {

                cartViewHolder.txtproquantity.setText("Quantity: "+Integer.toString(model.getQuantity()));
                cartViewHolder.textproname.setText(model.getTitle());
                cartViewHolder.txtproprice.setText("Price: "+model.getPayment()+" Tk");

                int oneTypeProductPrice = ((Integer.valueOf(model.getPayment()))) *model.getQuantity() ;
                totalprice = totalprice +oneTypeProductPrice ;

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                        txtTotal.setText("Total Price: "+String.valueOf(totalprice));


                        } else {
                            Toast.makeText(getApplicationContext(), "HEHE HOBE NA", Toast.LENGTH_LONG).show();

                        }




                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                Cart_showitem.addListenerForSingleValueEvent(eventListener);





                cartViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[] = new CharSequence[]
                                {

                                        "Remove"
                                } ;

                        AlertDialog.Builder builder= new AlertDialog.Builder(Shopping_Cart.this) ;
                        builder.setTitle("Cart Options") ;

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {


                            if(i==0)
                            {
                                Cart_showitem.child(check).child(model.getPid()).removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful())
                                                {

                                                    totalprice = totalprice-((Integer.valueOf(model.getPayment()))) *model.getQuantity() ;
                                                    txtTotal.setText("Total Price: "+String.valueOf(totalprice));

                                                    Toast.makeText(getApplicationContext(), "Removed successfully", Toast.LENGTH_LONG).show();

                                                    if(totalprice==0)
                                                    {
                                                        NextProcessBtn.setClickable(false);
                                                        Toast.makeText(getApplicationContext(), "List Is Empty", Toast.LENGTH_LONG).show();
                                                    }
                                                    else
                                                    {
                                                        NextProcessBtn.setClickable(true);

                                                    }


                                                }

                                            }

                                        });

                            }

                            }
                        }) ;
builder.show() ;
                    }
                });


            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false) ;

            CartViewHolder holder = new CartViewHolder(view) ;
            return holder ;


            }
        } ;


       recyclerView.setAdapter(adapter);
        adapter.startListening();


    }



}
