package com.chryl;

import com.chryl.controller.MongoController;
import com.chryl.controller.MongoController2;
import com.chryl.controller.MongoManyController;
import com.chryl.po.Many;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChrSpringbootMongodbApplicationTests {

    @Autowired
    private MongoController mongoController;
    @Autowired
    private MongoController2 mongoController2;
    @Autowired
    private MongoManyController mongoManyController;

    @Test
    public void contextLoads() {
        Object o = mongoController.show17();
//        String s = mongoController2.show1();
        System.out.println(o);
    }

    @Test
    public void contextLoads1() {
//        Object o = mongoManyController.show1();
        Object o = mongoManyController.show2();
        System.out.println(o);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads2() {
        Query query = new Query();
        query.addCriteria(Criteria.where("many_millis").gte(1568776607173L).lte(1568776607190L));
        query.addCriteria(Criteria.where("many_name").is("myName-5.0"));
        List<Many> manies = mongoTemplate.find(query, Many.class);
        for (Many m : manies) {
            System.out.println(m);
        }
    }


    @Test
    public void contextLoads3() {
        //这里使用的正则表达式的方式
        //第二个参数Pattern.CASE_INSENSITIVE是对字符大小写不明感匹配
        Pattern pattern = Pattern.compile("^.*" + "7.0" + ".*$", Pattern.CASE_INSENSITIVE);
        //用来构建条件
        Criteria criteria = new Criteria();
        //用来封装所有条件的对象
        Query query = new Query(criteria);
        query.addCriteria(criteria.and("many_name").regex(pattern));

        List<Many> manies = mongoTemplate.find(query, Many.class);
        System.out.println(manies.size());

    }

    @Test
    public void contextLoads4() {
        //用来构建条件
        Criteria criteria = new Criteria();
        //用来封装所有条件的对象
        Query query = new Query(criteria);
        query.addCriteria(Criteria.where("many_name").is("myName-7.0"));
        List<Many> manies = mongoTemplate.find(query, Many.class);
        System.out.println(manies.size() + "");
    }

    @Test
    public void contextLoads5() {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        query.addCriteria(Criteria.where("many_name").is("myName-7.0"));
        long count = mongoTemplate.count(query, Many.class);
        System.out.println(count + "");
    }

    @Test
    public void contextLoads6() {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        query.addCriteria(Criteria.where("many_name").is("myName-8.0"));
        Update update = new Update();
        update.set("many_age", "28");
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Many.class);
        System.out.println(updateResult.getModifiedCount());


    }

}
