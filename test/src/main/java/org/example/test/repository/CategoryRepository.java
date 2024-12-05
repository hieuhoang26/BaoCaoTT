package org.example.test.repository;

import org.example.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("select c from Category c where c.parentId = 0 ")
    List<Category> findAllParentCategory();
    List<Category> findCategoriesByParentId(Integer parentId);
}
