package com.chryl.po;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created By Chr on 2019/6/26.
 */

public class User implements Serializable {
    private static final long serialVersionUID = -5215907957697355085L;

    @Id
    private String id;

    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
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
