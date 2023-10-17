package com.example.cartimplementation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText t1, t2, t3, t4;
    TextView label;
    Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.p_id);
        t2 = findViewById(R.id.p_name);
        t3 = findViewById(R.id.p_price);
        t4 = findViewById(R.id.p_quantity);

        btn1 = findViewById(R.id.save_btn);
        btn2 = findViewById(R.id.cart_btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cart_db").allowMainThreadQueries().build();
                ProductDao productDao = appDatabase.ProductDao();
                Boolean check =productDao.is_exist(Integer.parseInt(t1.getText().toString()));
                if (check==false){
                    productDao.insertrecord(new Product(
                            Integer.parseInt(t1.getText().toString()),
                            t2.getText().toString(), Integer.parseInt(t3.getText().toString())
                            ,Integer.parseInt(t4.getText().toString())));
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    label.setText("Inserted Successfully");

                }
                else
                {
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    label.setText("Record Exist already!");
                }
            }
        });


    }
}