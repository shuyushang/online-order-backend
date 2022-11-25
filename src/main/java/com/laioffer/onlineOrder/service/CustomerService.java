package com.laioffer.onlineOrder.service;

//controller调用service class 来处理请求

import com.laioffer.onlineOrder.dao.CustomerDao;
import com.laioffer.onlineOrder.entity.Cart;
import com.laioffer.onlineOrder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//也是component的一种
@Service
public class CustomerService {
    //Dependency
    private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        customer.setCart(cart);
        customer.setEnable(true);
        customerDao.signUp(customer);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }

}
