package vn.ansv.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
