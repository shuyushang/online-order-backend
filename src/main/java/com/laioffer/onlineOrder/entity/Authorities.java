package com.laioffer.onlineOrder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {
    //序列化接口Serializable，因为数据要存入硬盘中，序列化转成字节流来存储
    private static final long serialVersionUID = 8734140534986494039L;
    //版本号 用来进行version tracking

    @Id
    private String email;
    private String authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
