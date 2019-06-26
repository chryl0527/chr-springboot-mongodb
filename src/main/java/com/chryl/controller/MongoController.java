package com.chryl.controller;

import com.chryl.po.User;
import com.chryl.repo.BaseMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created By Chr on 2019/6/26.
 */
@RestController
public class MongoController {
    @Autowired
    private BaseMongoRepository baseMongoRepository;


    @GetMapping("/save")
    public String show1() {
        User user = new User(null, "百科大全");
        baseMongoRepository.save(user);
        return "true";
    }

    @GetMapping("/get")
    public List<User> getUserList() {
        List<User> userList = baseMongoRepository.findAll();
        return userList;
    }

    @GetMapping("/del")
    public String del() {
        baseMongoRepository.deleteById("111");
        return "true";
    }
    @GetMapping("/update")
    public String update(){
        User user= new User("5d1329224bd0471da08760b1","redis全解");
        baseMongoRepository.save(user);
        return "success";
    }

}
