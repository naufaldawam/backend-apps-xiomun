package co.id.xiaomun.serviceappsxiaomun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.id.xiaomun.serviceappsxiaomun.entity.menu.MenuItem;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {

      @Query("SELECT m FROM MenuItem m " +
                  "WHERE (:categoryName IS NULL OR m.category.name = :categoryName) " +
                  "AND (:keyword IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')))")
      List<MenuItem> findByCategoryNameAndNameContainingIgnoreCase(@Param("categoryName") String categoryName,@Param("keyword") String keyword);

      List<MenuItem> findByCategoryId(Long categoryId);

}
