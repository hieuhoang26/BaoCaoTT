package org.example.test.repository;

import org.example.test.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer> {
    @Query("SELECT pd FROM ProductDetail pd WHERE pd.productByProductId.id = :productId")
    List<ProductDetail> findByProductId(@Param("productId") Integer productId);
}
