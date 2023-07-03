package vn.ansv.management.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.BaoLanhTuEntity;

@Repository
public interface BaoLanhTuRepository extends JpaRepository<BaoLanhTuEntity, Long> {
    /* Thông tin bổ sung: Chi tiết báo cáo tab hợp đồng */
    @Query(nativeQuery = true)
    SupportBaoLanhHopDongDTO findDetailTabHopDong(@Param("projectReportId") Long projectReportId,
            @Param("enabled") int enabled);

    // Thêm mới Bảo lãnh tạm ứng
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO bao_lanh_tu (project_id) VALUES (:projectId)", nativeQuery = true)
    void addNewBLTU(@Param("projectId") Long projectId);
}
