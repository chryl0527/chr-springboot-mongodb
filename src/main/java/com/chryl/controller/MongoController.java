package com.chryl.controller;

import com.chryl.po.User;
import com.chryl.repo.BaseMongoRepository;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 和show3 一样
     *
     * @return
     */
    @GetMapping("/show4")
    public Object show4() {
        Criteria criteria = Criteria.where("name").is("redis全解");
        Query query = new Query(criteria);
        List<User> di = mongoTemplate.find(query, User.class);
        return di;
    }

    @GetMapping("/show3")
    public Object show3() {
        //用来封装所有条件的对象
        Query query = new Query();
        //用来构建条件
        Criteria criteria = new Criteria();
        //查询 字段 name  为  redis全解
        criteria.and("name").is("redis全解");
        query.addCriteria(criteria);
        List<User> di = mongoTemplate.find(query, User.class);
        return di;
    }

    @GetMapping("/show2")
    public Object show2() {
        List<User> di = mongoTemplate.findAll(User.class, "db_chryl");
        return di;
    }

    //springBoot DATA mongodb   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @GetMapping("/save")
    public String save() {
        User user = new User(null, "百科大全", "2099");
        baseMongoRepository.save(user);
        return "true";
    }

    @GetMapping("/saveAll")
    public String saveAll() {
        User user1 = new User("001", "spring大全", "2011");
        User user2 = new User("002", "ajax大全", "2014");
        User user3 = new User("003", "jquery大全", "2013");
        User user4 = new User("004", "redis大全", "2019");
        User user5 = new User("005", "mongo大全", "2016");
        User[] users = {user1, user2, user3, user4, user5};

        Iterable<User> user = () -> {
            return new ArrayIterator<>(users);
        };
        baseMongoRepository.saveAll(user);
        return "true";
    }

    @GetMapping("/get")
    public List<User> getUserList() {
        List<User> userList = baseMongoRepository.findAll();
        return userList;
    }

    @GetMapping("/del")
    public String del() {
//        baseMongoRepository.deleteById("111");
        baseMongoRepository.deleteAll();
        return "true";
    }

    @GetMapping("/update")
    public String update() {
        User user = new User("5d1329224bd0471da08760b1", "redis全解", null);
        baseMongoRepository.save(user);
        return "success";
    }

}
