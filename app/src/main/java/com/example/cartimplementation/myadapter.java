package com.example.cartimplementation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {
    List<Product> products;
    TextView rateview;
    public myadapter(List<Product> products, TextView rateview) {
        this.products = products;
        this.rateview = rateview;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);

        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.recid.setText(String.valueOf(products.get(position).getId()));
        holder.recname.setText(products.get(position).getPname());
        holder.recprice.setText(String.valueOf(products.get(position).getPrice()));
        holder.recq.setText(String.valueOf(products.get(position).getQuantity()));

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase appDatabase = Room.databaseBuilder(holder.recid.getContext(), AppDatabase.class, "cart_db").allowMainThreadQueries().build();
                ProductDao productDao = appDatabase.ProductDao();

                productDao.deleteById(products.get(position).getId());
                products.remove(position);
                notifyItemRemoved(position);
                updateprice();




            }
        });


    }

    public void updateprice() {
        int sum=0,i;
        for (i=0;i<products.size();i++){
            sum=sum+(products.get(i).getPrice()*products.get(i).getQuantity());
            rateview.setText("Total Amount : PKR " + sum);

        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class myviewholder extends RecyclerView.ViewHolder{

        TextView recid,recname,recprice,recq;
        Button del;

        public myviewholder( @NonNull View itemView) {
            super(itemView);
            recid = itemView.findViewById(R.id.rec_id);
            recname = itemView.findViewById(R.id.recname);
            recprice = itemView.findViewById(R.id.recprice);
            recq = itemView.findViewById(R.id.recq);
            del = itemView.findViewById(R.id.del_btn);

        }
    }

}


