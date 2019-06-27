package com.chryl.controller;

import com.chryl.po.Student;
import com.chryl.po.User;
import com.chryl.repo.BaseMongoRepository;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @GetMapping("/show00")
    public String show00() {

        return "suc";
    }

    //mongoTemplate @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    /**
     * upsert 顾名思义 update+insert 如果根据条件没有对应的数据,则执行插入
     *
     * @return
     */
    @GetMapping("/show9")
    public String show9() {
        Query query = new Query(new Criteria("_id").is("sd-0006"));
        Update update = new Update();
        update.set("s_name", "chr0561");
        //updateMulti 如果根据查询条件找到对应的多条记录是，全部更新
        //updateFirst 更改符合条件的第一个
        mongoTemplate.updateMulti(query, update, Student.class);
        return "suc";
    }

    /**
     * 查找后删除:可以指定collection
     *
     * @return
     */
    @GetMapping("/show8")
    public String show8() {//为什么主键不是 : s_id,而是_id
        Query query = new Query(new Criteria("_id").is("sd-0007"));
        mongoTemplate.findAndRemove(query, Student.class);
        return "suc";
    }

    /**
     * 一次插入多天数据
     *
     * @return
     */
    @GetMapping("/show7")
    public String show7() {

        List<Student> users = new ArrayList<>();
        Student student1 = new Student("sd-0006", "math", 23);
        Student student3 = new Student("sd-0007", "yellow", 15);
        Student student2 = new Student("sd-0008", "pink", 19);

        users.add(student1);
        users.add(student2);
        users.add(student3);//集合中可以放多个
        mongoTemplate.insertAll(users);
        return "suc";
    }

    /**
     * 查询然后更改:findAndModify
     *
     * @return
     */
    @GetMapping("/show6")
    public String show6() {
        Criteria criteria = Criteria.where("s_name").is("nancy");
        Query q = new Query(criteria);
        Update update = new Update();
        update.set("s_name", "skx");
        update.set("s_age", "24");
        mongoTemplate.findAndModify(q, update, Student.class);
        return "suc";
    }

    /**
     * 插入一条
     * save 到固定的collection
     * 或者 po类加上 @Document(collection = "Student")
     */
    @GetMapping("/show5")
    public String show5() {
        Student student1 = new Student("sd-0001", "nancy", 13);
        Student student3 = new Student("sd-0003", "na", 11);
        Student student2 = new Student("sd-0002", "ncy", 14);
//        mongoTemplate.save(student1, "chryl");
//        mongoTemplate.save(student2, "chryl");
//        mongoTemplate.save(student3, "chryl");
        mongoTemplate.save(student1);
        mongoTemplate.save(student2);
        mongoTemplate.save(student3);
        return "suc";
    }

    /**
     * 和show3 一样,模糊查询
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

    /**
     * 条件件查询
     *
     * @return
     */
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

    //查询指定collection全部的
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

    /**
     * 保存 多个
     *
     * @return
     */
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

    /**
     * 查询多个
     *
     * @return
     */
    @GetMapping("/get")
    public List<User> getUserList() {
        List<User> userList = baseMongoRepository.findAll();
        return userList;
    }

    //删除
    @GetMapping("/del")
    public String del() {
//        baseMongoRepository.deleteById("111");
        baseMongoRepository.deleteAll();
        return "true";
    }

    //更新
    @GetMapping("/update")
    public String update() {
        User user = new User("5d1329224bd0471da08760b1", "redis全解", null);
        baseMongoRepository.save(user);
        return "success";
    }

}
