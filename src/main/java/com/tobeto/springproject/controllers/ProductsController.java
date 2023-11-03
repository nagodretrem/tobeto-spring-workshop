package com.tobeto.springproject.controllers;

import com.tobeto.springproject.models.Person;
import com.tobeto.springproject.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductsController {
    List<Product> inMemoryList = new ArrayList<>();

    @GetMapping
    public List<Product> get(){
        return  inMemoryList;
    }
    @GetMapping("{id}")
    public int getById(@PathVariable int id){
        return  id;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        System.out.println("Product saved!");
        inMemoryList.add(product);

        return product;
    }

    @PostMapping("product-all")
    public List<Product> saveAllProduct(@RequestBody List<Product> product){
        System.out.println("All persons saved!");
        inMemoryList.addAll(product);
        return product;
    }

    @PutMapping("update")
    public boolean updateProduct( @RequestBody Product product){
        for (int i = 0; i < inMemoryList.size(); i++){
            Product product1 = inMemoryList.get(i);
            if(product1.getId() == product.getId())
            {

                inMemoryList.set(i, product);
                return true;
            }
        }
        return false;

    }

    @DeleteMapping("/delete/{id}")
    public boolean  deleteProduct(@PathVariable int id){
        boolean remove = inMemoryList.remove(inMemoryList
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow());
        System.out.println(inMemoryList);

        return remove;
    }

}
