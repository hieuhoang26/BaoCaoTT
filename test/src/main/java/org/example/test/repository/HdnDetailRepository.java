package org.example.test.repository;

import org.example.test.model.HdnDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HdnDetailRepository extends JpaRepository<HdnDetail,Integer> {
}
