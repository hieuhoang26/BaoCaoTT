package org.example.test.repository;

import org.example.test.model.Order;
import org.example.test.util.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderRepository extends JpaRepository<Order,Integer> {
    boolean existsByUserByUserId_Id(Integer id);

    Order findOrderByUserByUserId_Id(Integer id);

    @Query("SELECT o FROM Order o WHERE o.userByUserId.id = :userId AND o.status = :status")
    Order findOrderByUserByUserId_IdAndStatus(@Param("userId") Integer userId, @Param("status") OrderStatus orderStatus);
}


