package vn.ansv.management.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ansv.management.dto.Detail.ReportDetailTabHopDongDTO;
import vn.ansv.management.entity.HopDongEntity;

@Repository
public interface HopDongRepository extends JpaRepository<HopDongEntity, Long> {
    /*
     * -------------------------------------------
     * Chi tiết báo cáo dự án theo ID và Enabled
     * View: Detail (tab Hợp đồng)
     * -------------------------------------------
     */
    @Query(nativeQuery = true)
    ReportDetailTabHopDongDTO findDetailTabHopDong(@Param("id") Long id, @Param("enabled") int enabled);

    // Thêm mới hợp đồng
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO hop_dong (project_id) "
            + "VALUES (:projectId)", nativeQuery = true)
    void addNewHopDong(@Param("projectId") Long projectId);
}
