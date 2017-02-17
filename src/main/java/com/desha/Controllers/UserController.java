package com.desha.Controllers;

import com.desha.Beans.User;
import com.desha.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/getOne/{id}")
    public User getById(@PathVariable long id) {
        return repository.findOne(id);
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public User exists(@RequestBody User sentUser) {
        User user = repository.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        System.out.println(user);

        List<User> listSvihUsera = repository.findAll();
        for (User temp : listSvihUsera) {
            System.out.println(temp.getEmail());
            System.out.println(temp.getPassword());
            System.out.println(temp.getUser_id());
        }
        return user;
    }

    @RequestMapping(value = "/existsEmail", method = RequestMethod.PUT)
    public boolean existsEmail(@RequestBody String email) {
        User user = repository.findByEmail(email);
        if (user == null)
            return false;
        else
            return true;
    }


    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody User newUser) {
        repository.save(newUser);
    }


}
