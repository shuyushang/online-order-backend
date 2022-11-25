package com.laioffer.onlineOrder.entity;

//entity分类，便于管理
//基本的object，真实世界里转换成object

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled; //是否active用户

    @OneToOne(cascade = CascadeType.ALL) //CascadeType删除关系；当删除customer时，shoppingcart一起被全部删除
    @JoinColumn(unique = true) //join column时，都是unique的 1：1 relationship
    private Cart cart;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
