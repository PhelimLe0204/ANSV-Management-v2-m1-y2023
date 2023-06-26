package vn.ansv.management.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.BaoLanhBhEntity;

@Repository
public interface BaoLanhBhRepository extends JpaRepository<BaoLanhBhEntity, Long> {
    /* Thông tin bổ sung: Chi tiết báo cáo tab hợp đồng */
    @Query(value = "SELECT blbh.id, blbh.ngay_phat_hanh AS ngayPhatHanh, blbh.ngay_het_han AS ngayHetHan, "
            + "blbh.note FROM bao_lanh_bh AS blbh "
            + "INNER JOIN project AS p ON blbh.project_id = p.id "
            + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
            + "WHERE pr.id = :projectReportId AND pr.enabled = :enabled "
            + "ORDER BY blbh.modified_at DESC LIMIT 1", nativeQuery = true)
    SupportBaoLanhHopDongDTO findDetailTabHopDong(@Param("projectReportId") Long projectReportId,
            @Param("enabled") int enabled);

    // Thêm mới Bảo lãnh bảo hành
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO bao_lanh_bh (project_id) VALUES (:projectId)", nativeQuery = true)
    void addNewBLBH(@Param("projectId") Long projectId);
}
