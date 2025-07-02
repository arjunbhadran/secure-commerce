package com.ecommerce.project.service;

import com.ecommerce.project.model.Order;
import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
}
