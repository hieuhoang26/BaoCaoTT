package org.example.test.repository;

import org.example.test.model.Hdn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HdnRepository extends JpaRepository<Hdn,Integer> {

}
