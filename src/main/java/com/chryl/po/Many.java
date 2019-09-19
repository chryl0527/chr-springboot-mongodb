package com.chryl.po;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By Chr on 2019/9/18.
 */
@Document(collection = "Many")
public class Many implements Serializable {
    private static final long serialVersionUID = -9099600938517268738L;

    @Id
    @Indexed(name = "many_id")
    private String manyId;

    @Field("many_name")
    private String manyName;

    @Field("many_age")
    private Integer manyAge;

    @Field("many_date")
    private Date manyDate;

    @Field("many_millis")
    private Long manyMillis;

    public Many() {
    }

    public Many(String manyId, String manyName, Integer manyAge, Date manyDate, Long manyMillis) {
        this.manyId = manyId;
        this.manyName = manyName;
        this.manyAge = manyAge;
        this.manyDate = manyDate;
        this.manyMillis = manyMillis;
    }

    public String getManyId() {
        return manyId;
    }

    public void setManyId(String manyId) {
        this.manyId = manyId;
    }

    public String getManyName() {
        return manyName;
    }

    public void setManyName(String manyName) {
        this.manyName = manyName;
    }

    public Integer getManyAge() {
        return manyAge;
    }

    public void setManyAge(Integer manyAge) {
        this.manyAge = manyAge;
    }

    public Date getManyDate() {
        return manyDate;
    }

    public void setManyDate(Date manyDate) {
        this.manyDate = manyDate;
    }

    public Long getManyMillis() {
        return manyMillis;
    }

    public void setManyMillis(Long manyMillis) {
        this.manyMillis = manyMillis;
    }

    @Override
    public String toString() {
        return "Many{" +
                "manyId='" + manyId + '\'' +
                ", manyName='" + manyName + '\'' +
                ", manyAge=" + manyAge +
                ", manyDate=" + manyDate +
                ", manyMillis=" + manyMillis +
                '}';
    }
}
