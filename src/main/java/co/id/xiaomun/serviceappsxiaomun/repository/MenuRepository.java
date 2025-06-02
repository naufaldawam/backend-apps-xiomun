package co.id.xiaomun.serviceappsxiaomun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.id.xiaomun.serviceappsxiaomun.entity.menu.MenuItem;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {

      @Query(value = """
                      SELECT m.* FROM menu_item m
                      JOIN menu_category c ON m.category_id = c.id
                      WHERE (:category IS NULL OR c.name = :category)
                      AND (:keyword IS NULL OR LOWER(m.name) LIKE CONCAT('%', LOWER(:keyword), '%'))
                  """, nativeQuery = true)
      List<MenuItem> findByCategoryNameAndNameContainingIgnoreCase(@Param("category") String categoryName,
                  @Param("keyword") String keyword);

      List<MenuItem> findByCategoryId(Long categoryId);

}
