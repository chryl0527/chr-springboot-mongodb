package com.chryl.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * mongoDb:可以改变库的结构:一开始没有pwd,后来有pwd
 * <p>
 * Created By Chr on 2019/6/26.
 */

@Document(collection = "User")//标注在实体类上，类似于hibernate的entity注解，标明由mongo来维护该表
//@CompoundIndexes({//复合索引，加复合索引后通过复合索引字段查询将大大提高速度。
//        @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
//})//写法如上，lastName和age将作为复合索引，数字参数指定索引的方向，1为正序，-1为倒序。方向对单键索引和随机存不要紧，但如果你要执行分组和排序操作的时候，它就非常重要了。
public class User implements Serializable {
    private static final long serialVersionUID = -5215907957697355085L;

    /**
     * 声明该字段需要加索引，加索引后以该字段为条件检索将大大提高速度。
     * 唯一索引的话是@Indexed(unique = true)。
     */
    @Id
    @Indexed
    private String id;

    @Field("fName")//给映射存储到 mongodb 的字段取别名
    private String name;

    private String pwd;

    @Transient//被该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性。
    private String cs;


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User() {
    }

    public User(String id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", cs='" + cs + '\'' +
                '}';
    }
}
