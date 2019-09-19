package com.chryl.controller;

import com.chryl.po.Many;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created By Chr on 2019/9/18.
 */
@RestController("/many")
public class MongoManyController {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * insert
     * save和insert的区别
     * 　　insert: 若新增数据的主键已经存在，则会抛 org.springframework.dao.DuplicateKeyException 异常提示主键重复，不保存当前数据。
     * <p>
     * 　　save: 若新增数据的主键已经存在，则会对当前已经存在的数据进行修改操作。
     * 批操作:
     * 　　insert: 可以一次性插入一整个列表，而不用进行遍历操作，效率相对较高
     * <p>
     * 　　save: 需要遍历列表，进行一个个的插入
     *
     * @return
     */
    @GetMapping("/1")
    public Object show1() {
        int i = 0;
        while (i < 50) {
            Many many = new Many(
                    UUID.randomUUID().toString().replaceAll("-", ""),
                    "myName-" + Math.ceil(Math.random() * 10),
                    23,
                    new Date(),
                    System.currentTimeMillis());
            mongoTemplate.insert(many);
//            mongoTemplate.save(many);
            i++;
        }
        return "suc";
    }

    /**
     * findOne
     *
     * @return
     */
    @GetMapping("/2")
    public Object show2() {
//        BasicDBObject condition = new BasicDBObject();//条件
//        condition.append("many_name", "myName-6.0");
        Query query = new Query(Criteria.where("many_name").is("myName-6.0"));
        Many obj = mongoTemplate.findOne(query, Many.class);
        return obj;
    }

    /**
     * findList
     * 多条件筛选查找
     *
     * @return
     */
    @GetMapping("/3")
    public Object show3() {
        //这里使用的正则表达式的方式
        //第二个参数Pattern.CASE_INSENSITIVE是对字符大小写不明感匹配
        Pattern pattern = Pattern.compile("", Pattern.CASE_INSENSITIVE);
        //用来构建条件
//        Criteria criteria = new Criteria();
//        criteria.and("many_millis").gt()
//        Example<Many> example= new Example<Many>();
//        criteria.regex(pattern);
        //用来封装所有条件的对象
        Query query = new Query();
        query.addCriteria(Criteria.where("many_millis").gte(1568776607173L).lte(1568776607190L));
        query.addCriteria(Criteria.where("many_name").is("myName-5.0"));
        List<Many> manies = mongoTemplate.find(query, Many.class);
        System.out.println(manies);
        return manies;

    }

    /**
     * 普通条件查询
     *
     * @return
     */
    @GetMapping("/4")
    public Object show4() {
        //这里使用的正则表达式的方式
        //第二个参数Pattern.CASE_INSENSITIVE是对字符大小写不明感匹配
//        Pattern pattern = Pattern.compile("" + "myName" + ".*", Pattern.CASE_INSENSITIVE);
        //用来构建条件
        Criteria criteria = new Criteria();
//        criteria.regex(pattern);
        //用来封装所有条件的对象
        Query query = new Query();
        query.addCriteria(Criteria.where("many_millis").gte(1568776607173L).lte(1568776607190L));

        List<Many> manies = mongoTemplate.find(query, Many.class);
        System.out.println(manies);
        return manies;

    }

    /**
     * 正则查询:Pattern
     * 1、完全匹配
     * Pattern pattern = Pattern.compile("^name$", Pattern.CASE_INSENSITIVE);
     * 2、右匹配
     * Pattern pattern = Pattern.compile("^.*name$", Pattern.CASE_INSENSITIVE);
     * 3、左匹配
     * Pattern pattern = Pattern.compile("^name.*$", Pattern.CASE_INSENSITIVE);
     * 4、模糊匹配
     * Pattern pattern = Pattern.compile("^.*name8.*$", Pattern.CASE_INSENSITIVE);
     */
    @GetMapping("/5")
    public void show5() {
        //这里使用的正则表达式的方式
        //第二个参数Pattern.CASE_INSENSITIVE是对字符大小写不明感匹配
        //第一个参数是  填正则
        Pattern pattern = Pattern.compile("^.*" + "myName-7.0" + ".*$", Pattern.CASE_INSENSITIVE);
        //用来构建条件
        Criteria criteria = new Criteria();
        //用来封装所有条件的对象
        Query query = new Query(criteria);
        query.addCriteria(criteria.and("many_name").regex(pattern));
//        query.addCriteria(Criteria.where("many_millis").gte(1568776607173L).lte(1568776607190L));

        List<Many> manies = mongoTemplate.find(query, Many.class);
        System.out.println(manies.size());
    }

    /**
     * 非正则查询和 show5一样
     */
    @GetMapping("/6")
    public void show6() {
        //用来构建条件
        Criteria criteria = new Criteria();
        //用来封装所有条件的对象
        Query query = new Query(criteria);
        query.addCriteria(Criteria.where("many_name").is("myName-7.0"));

        List<Many> manies = mongoTemplate.find(query, Many.class);
        System.out.println(manies.size());
    }

    /**
     * 查询记录数
     */
    @GetMapping("/7")
    public void show7() {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        query.addCriteria(Criteria.where("many_name").is("myName-7.0"));
        long count = mongoTemplate.count(query, Many.class);
        System.out.println(count);
    }

    /**
     *批量修改
     */
    @GetMapping("/7")
    public void show8() {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        query.addCriteria(Criteria.where("many_name").is("myName-8.0"));
        Update update = new Update();
        update.set("many_age", "28");
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Many.class);
        System.out.println(updateResult.getModifiedCount());
    }
}
