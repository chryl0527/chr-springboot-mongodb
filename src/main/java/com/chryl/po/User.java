package com.chryl.po;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * mongoDb:可以改变库的结构:一开始没有pwd,后来有pwd
 * <p>
 * Created By Chr on 2019/6/26.
 */

public class User implements Serializable {
    private static final long serialVersionUID = -5215907957697355085L;

    @Id
    private String id;

    private String name;

    private String pwd;

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


}
