package com.chryl;

import com.chryl.controller.MongoController;
import com.chryl.controller.MongoManyController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChrSpringbootMongodbApplicationTests {

    @Autowired
    private MongoController mongoController;
    @Autowired
    private MongoManyController mongoManyController;

    //    @Test
//    public void contextLoads() {
//        Object o = mongoController.show17();
//        System.out.println(o);
//    }
    @Test
    public void contextLoads1() {
        Object o = mongoManyController.show1();
        System.out.println(o);
    }

}
