package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.Layout.LayoutMenuCategoryDTO;
import vn.ansv.management.entity.MenuCategoryEntity;

public interface MenuCategoryRepository extends JpaRepository<MenuCategoryEntity, Long> {
    @Query(nativeQuery = true)
    List<LayoutMenuCategoryDTO> findAllLayout(); // Phân loại menu trên layout (giao diện)
}
