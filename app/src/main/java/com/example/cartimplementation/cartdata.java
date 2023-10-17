package com.example.cartimplementation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class cartdata extends AppCompatActivity {

RecyclerView recyclerView;
TextView rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartdata);

        recyclerView=findViewById(R.id.cartRecyclerView);
        rate=findViewById(R.id.totalAmount);
        getroomdata();

    }
    public void getroomdata(){
        AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "cart_db").allowMainThreadQueries().build();
        ProductDao productDao= db.ProductDao();

        recyclerView=findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        List<Product> products= ProductDao.deleteById();

        myadapter adapter= new myadapter(products, rate);

        recyclerView.setAdapter(adapter);


        int sum=0, i;
        for (i=0;i<products.size();i++){
            sum=sum+(products.get(i).getPrice()*products.get(i).getQuantity());
            rate.setText("Total Amount : PKR " + sum);
        }








    }
}