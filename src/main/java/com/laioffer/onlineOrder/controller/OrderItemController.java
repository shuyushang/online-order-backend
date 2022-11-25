package com.laioffer.onlineOrder.controller;

import com.laioffer.onlineOrder.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @RequestMapping(value = "/order/{menuId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addMenuItemToCart(@PathVariable("menuId") int menuId){
        orderItemService.saveOrderItem(menuId);
    }
}
