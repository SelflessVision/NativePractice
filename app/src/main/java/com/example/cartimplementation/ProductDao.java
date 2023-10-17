package com.example.cartimplementation;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertrecord(Product product);

    @Query("SELECT EXISTS(SELECT * FROM product WHERE pid= :id)")
    Boolean is_exist(int id);


    @Query("SELECT * FROM Product")`
    List<Product> getallproduct();



    @Query("DELETE FROM Product WHERE pid = :id")
    void deleteById(int id);


}
