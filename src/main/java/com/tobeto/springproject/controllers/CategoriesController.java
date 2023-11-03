package com.tobeto.springproject.controllers;

import com.tobeto.springproject.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoriesController {
    List<Category> inMemoryList = new ArrayList<>();

    @GetMapping
    public List<Category> get(){
        return  inMemoryList;
    }
    @GetMapping("{id}")
    public int getById(@PathVariable int id){
        return  id;
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category){
        System.out.println("Category saved!");
        inMemoryList.add(category);

        return category;
    }

    @PostMapping("category-all")
    public List<Category> saveAllCategory(@RequestBody List<Category> categories){
        System.out.println("All categories saved!");
        inMemoryList.addAll(categories);
        return categories;
    }

    @PutMapping("update")
    public boolean updateCategory( @RequestBody Category category){
        for (int i = 0; i < inMemoryList.size(); i++){
            Category category1 = inMemoryList.get(i);
            if(category1.getId() == category.getId())
            {
                inMemoryList.set(i, category);
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
