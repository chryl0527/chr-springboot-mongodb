package com.chryl.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * mongoDb:可以改变库的结构:一开始没有pwd,后来有pwd
 * <p>
 * Created By Chr on 2019/6/26.
 */
@Document(collection = "Student")
public class Student implements Serializable {
    private static final long serialVersionUID = -5215907957697355085L;

    @Id
    @Indexed
    @Field("s_id")//给映射存储到 mongodb 的字段取别名
    private String sId;
    @Field("s_name")//给映射存储到 mongodb 的字段取别名
    private String sName;
    @Field("s_age")
    private Integer sAge;
    @Field("s_birthday")
    private long sBirthday;

    public Student() {
    }

    public Student(String sId, String sName, Integer sAge, long sBirthday) {
        this.sId = sId;
        this.sName = sName;
        this.sAge = sAge;
        this.sBirthday = sBirthday;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getsAge() {
        return sAge;
    }

    public void setsAge(Integer sAge) {
        this.sAge = sAge;
    }

    public long getsBirthday() {
        return sBirthday;
    }

    public void setsBirthday(long sBirthday) {
        this.sBirthday = sBirthday;
    }
}
