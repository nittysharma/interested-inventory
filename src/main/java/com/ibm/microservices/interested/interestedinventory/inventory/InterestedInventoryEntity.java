package com.ibm.microservices.interested.interestedinventory.inventory;

import com.mysql.cj.x.protobuf.MysqlxSession;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="interested_inventory")
public class InterestedInventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skuId;
    private String userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    private Date addDate;
}
