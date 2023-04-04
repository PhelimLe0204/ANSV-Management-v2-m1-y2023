package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.Layout.LayoutMenuDTO;
import vn.ansv.management.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    /*
     * ---------------------------------------------------------
     * Truy vấn menu (level 1) trên layout theo phân loại menu
     * ---------------------------------------------------------
     */
    @Query(nativeQuery = true)
    List<LayoutMenuDTO> findAllLayoutLevel1ByRole(Long menu_category_id, int level, String roleName);

    /*
     * -------------------------------------------------------
     * Truy vấn menu (level 2) trên layout theo menu level 1
     * -------------------------------------------------------
     */
    @Query(nativeQuery = true)
    List<LayoutMenuDTO> findAllLayoutLevel2ByRole(Long previous, int level, String roleName);
}
