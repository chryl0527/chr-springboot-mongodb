package com.chryl.controller;

import com.chryl.po.Many;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

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

}
