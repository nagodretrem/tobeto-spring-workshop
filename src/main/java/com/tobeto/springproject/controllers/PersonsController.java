package com.tobeto.springproject.controllers;

import com.tobeto.springproject.models.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonsController {
    List<Person> inMemoryList = new ArrayList<>();

    @GetMapping
    public List<Person> get(){
        return  inMemoryList;
    }
    @GetMapping("{id}")
    public int getById(@PathVariable int id){
        return  id;
    }

    @GetMapping("getById")
    public int getById2(@RequestParam int id){
        return  id;
    }
    @PostMapping
    public Person savePerson(@RequestBody Person person){
        System.out.println("Person saved!");
        inMemoryList.add(person);

        return person;
    }

    @PostMapping("person-all")
    public List<Person> saveAllPerson(@RequestBody List<Person> persons){
        System.out.println("All persons saved!");
        inMemoryList.addAll(persons);
        return persons;
    }

    @PutMapping("update")
    public boolean updatePerson( @RequestBody Person person){
        for (int i = 0; i < inMemoryList.size(); i++){
            Person person1 = inMemoryList.get(i);
            if(person1.getId() == person.getId())
            {
                inMemoryList.set(i, person);
                return true;
            }
        }
        return false;

    }


    @DeleteMapping("/delete/{id}")
    public boolean  deletePerson(@PathVariable int id){
        boolean remove = inMemoryList.remove(inMemoryList
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow());
        System.out.println(inMemoryList);

        return remove;
    }
}
