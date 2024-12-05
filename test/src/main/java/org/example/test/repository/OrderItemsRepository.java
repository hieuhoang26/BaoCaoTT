package org.example.test.repository;

import jakarta.transaction.Transactional;
import org.example.test.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer> {
    @Query("SELECT oi FROM OrderItem oi WHERE oi.orderByOrderId.id = :id")
    List<OrderItem> findByOrderByOrderId(Integer id);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.orderByOrderId.id = :orderId AND oi.productByProductId.id = :productId")
    OrderItem findByOrderByOrderIdAndProductByProductId(Integer orderId, Long productId);



    void deleteById(Long orderId );

}
