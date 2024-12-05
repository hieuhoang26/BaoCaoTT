package org.example.test.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.test.dto.CreateOrder;
import org.example.test.dto.CreateOrderItem;
import org.example.test.dto.UpdateOrder;
import org.example.test.dto.UpdateOrderItem;
import org.example.test.model.Order;
import org.example.test.model.OrderItem;
import org.example.test.model.Product;
import org.example.test.repository.OrderItemsRepository;
import org.example.test.repository.OrderRepository;
import org.example.test.repository.ProductRepository;
import org.example.test.util.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;
    final OrderItemsRepository orderItemsRepository;
    final ProductRepository productRepository;

    public UpdateOrder getOrder(Integer userId, OrderStatus status) {
        Order order = orderRepository.findOrderByUserByUserId_IdAndStatus(userId, status);

        if (order == null) {
            throw new RuntimeException("Order not found for user ID: " + userId + " and status: " + status);
        }
        return UpdateOrder.builder()
                .status(order.getStatus())
                .subTotal(order.getSubTotal())
                .shipping(order.getShipping())
                .total(order.getTotal())
                .discount(order.getDiscount())
                .grandTotal(order.getGrandTotal())
                .mobile(order.getMobile())
                .address(order.getAddress())
                .content(order.getContent())
                .items(order.getOrderItemsById().stream()
                        .map(orderItem -> new UpdateOrderItem(
                                Math.toIntExact(orderItem.getId()),
                                Math.toIntExact(orderItem.getProductByProductId().getId()),
                                orderItem.getQuantity()))
                        .collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public void createOrder(Integer userId, CreateOrder createOrderDto) {

        // check use have order
        Order order = null;
        if (orderRepository.existsByUserByUserId_Id(userId)) {
            order = orderRepository.findOrderByUserByUserId_Id(userId);
        } else {
            order = new Order();
            order.setCreatedAt(LocalDate.now());
        }

        if (createOrderDto.getStatus() != null) {
            order.setStatus(createOrderDto.getStatus());
        }

        if (createOrderDto.getSubTotal() != null) {
            order.setSubTotal(createOrderDto.getSubTotal());
        }

        if (createOrderDto.getShipping() != null) {
            order.setShipping(createOrderDto.getShipping());
        }

        if (createOrderDto.getTotal() != null) {
            order.setTotal(createOrderDto.getTotal());
        }

        if (createOrderDto.getDiscount() != null) {
            order.setDiscount(createOrderDto.getDiscount());
        }

        if (createOrderDto.getGrandTotal() != null) {
            order.setGrandTotal(createOrderDto.getGrandTotal());
        }

        if (createOrderDto.getMobile() != null) {
            order.setMobile(createOrderDto.getMobile());
        }

        if (createOrderDto.getAddress() != null) {
            order.setAddress(createOrderDto.getAddress());
        }
        order.setUpdatedAt(LocalDate.now());

        if (createOrderDto.getContent() != null) {
            order.setContent(createOrderDto.getContent());
        }

        orderRepository.save(order);
        for (CreateOrderItem itemDto : createOrderDto.getItems()) {
            // Check if the product already exists in the order
            OrderItem existingOrderItem = orderItemsRepository.findByOrderByOrderIdAndProductByProductId(Math.toIntExact(order.getId()), Long.valueOf(itemDto.getProductId()));

            if (existingOrderItem != null) {
                // If the product exists in the order, update the quantity
                existingOrderItem.setQuantity((short) (existingOrderItem.getQuantity() + itemDto.getQuantity()));
                orderItemsRepository.save(existingOrderItem);
            } else {
                // If the product does not exist in the order, create a new order item
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(itemDto.getQuantity());

                Product product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                orderItem.setProductByProductId(product);
                orderItem.setOrderByOrderId(order);

                orderItemsRepository.save(orderItem);
            }
        }


    }

    @Transactional
    public void deleteOrderItem(Long orderId) {
        orderItemsRepository.deleteById(orderId);
    }

    @Transactional
    public void updateOrder(Integer userId, UpdateOrder updateOrderDto) {

        Order order = orderRepository.findOrderByUserByUserId_Id(userId);


        // Update fields if provided in the DTO
        if (updateOrderDto.getStatus() != null) {
            order.setStatus(updateOrderDto.getStatus());
        }

        if (updateOrderDto.getSubTotal() != null) {
            order.setSubTotal(updateOrderDto.getSubTotal());
        }

        if (updateOrderDto.getShipping() != null) {
            order.setShipping(updateOrderDto.getShipping());
        }

        if (updateOrderDto.getTotal() != null) {
            order.setTotal(updateOrderDto.getTotal());
        }

        if (updateOrderDto.getDiscount() != null) {
            order.setDiscount(updateOrderDto.getDiscount());
        }

        if (updateOrderDto.getGrandTotal() != null) {
            order.setGrandTotal(updateOrderDto.getGrandTotal());
        }

        if (updateOrderDto.getMobile() != null) {
            order.setMobile(updateOrderDto.getMobile());
        }

        if (updateOrderDto.getAddress() != null) {
            order.setAddress(updateOrderDto.getAddress());
        }

        if (updateOrderDto.getContent() != null) {
            order.setContent(updateOrderDto.getContent());
        }

        // Update the order's updatedAt timestamp
        order.setUpdatedAt(LocalDate.now());

        // Save the updated order
        orderRepository.save(order);


        for (UpdateOrderItem itemDto : updateOrderDto.getItems()) {
            if (itemDto.getOrderItemId() != null) {

                OrderItem existingOrderItem = orderItemsRepository.findById(itemDto.getOrderItemId())
                        .orElseThrow(() -> new RuntimeException("OrderItem not found"));

                existingOrderItem.setQuantity(itemDto.getQuantity());

                Product product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                existingOrderItem.setProductByProductId(product);
                existingOrderItem.setOrderByOrderId(order);
                orderItemsRepository.save(existingOrderItem);

//                updatedItemIds.add(itemDto.getOrderItemId());
            } else {
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(itemDto.getQuantity());

                Product product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                orderItem.setProductByProductId(product);
                orderItem.setOrderByOrderId(order);

                orderItemsRepository.save(orderItem);
            }
        }
//        for (OrderItem existingItem : existingOrderItems) {
//            if (!updatedItemIds.contains(existingItem.getId())) {
//                orderItemsRepository.delete(existingItem);
//            }
//        }
    }

    public void updateOrderStatus(Integer userId, OrderStatus status) {
        Order order = orderRepository.findOrderByUserByUserId_Id(userId);
        if (status != null) {
            order.setStatus(status);
        }
        orderRepository.save(order);
    }


}
