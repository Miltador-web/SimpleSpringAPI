package com.example.PersonalProject.controllers;

import com.example.PersonalProject.data.Users;
import com.example.PersonalProject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class Controllers {


    @Autowired
    private  UsersRepository repository;

    @GetMapping
    public List<Users> listar(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Users listareach(@PathVariable Long id){
        return repository.findById(id).get();

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Users add(@RequestBody Users usuario){
        return repository.save(usuario);
    }


    @PutMapping(value = "/{id}")
    public Optional<Users> update(@PathVariable Long id, @RequestBody Users usuario){
        return repository.findById(id)
                .map(Users -> {
                    Users.setName(usuario.getName());
                    return repository.save(Users);
                });
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id){
        repository.deleteById(id);
        return "User "+id+" was deleted with success";
    }

}
