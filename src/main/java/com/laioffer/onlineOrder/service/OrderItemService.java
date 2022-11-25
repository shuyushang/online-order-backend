package com.laioffer.onlineOrder.service;

import com.laioffer.onlineOrder.dao.OrderItemDao;
import com.laioffer.onlineOrder.dao.OrderItemDao;
import com.laioffer.onlineOrder.entity.Customer;
import com.laioffer.onlineOrder.entity.MenuItem;
import com.laioffer.onlineOrder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderItemDao orderItemDao;

    public void saveOrderItem(int menuId) {
        final OrderItem orderItem = new OrderItem();
        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);

        //根据谁登陆的找到用户
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);
        orderItem.setPrice(menuItem.getPrice());
        orderItemDao.save(orderItem);
    }
}

