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
    // Count bao_lanh_bh by id
    @Query(value = "SELECT COUNT(bltu.id) FROM bao_lanh_tu AS bltu WHERE bltu.id = :id", nativeQuery = true)
    Integer countById(@Param("id") Long id);

    /* Thông tin bổ sung: Chi tiết báo cáo tab hợp đồng */
    @Query(nativeQuery = true)
    SupportBaoLanhHopDongDTO findDetailTabHopDongByReport(@Param("projectReportId") Long projectReportId,
            @Param("enabled") int enabled);

    @Query(nativeQuery = true)
    SupportBaoLanhHopDongDTO findDetailTabHopDongById(@Param("id") Long id);

    // Thêm mới Bảo lãnh tạm ứng
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO bao_lanh_tu (project_id, modified_by) VALUES (:projectId, 'System')", nativeQuery = true)
    void addNewBLTU(@Param("projectId") Long projectId);

    /*
     * -----------------------------------------
     * Cập nhật bao_lanh_tu theo mã bảo lãnh
     * -----------------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE bao_lanh_tu AS bltu "
            + "SET bltu.modified_by = :modifiedBy, bltu.ngay_phat_hanh = :ngayPhatHanh, "
            + "bltu.ngay_het_han = :ngayHetHan, bltu.note = :note "
            + "WHERE bltu.id = :id", nativeQuery = true)
    void updateDataBaoLanhById(@Param("id") Long id, @Param("modifiedBy") String modifiedBy,
            @Param("ngayPhatHanh") String ngayPhatHanh, @Param("ngayHetHan") String ngayHetHan,
            @Param("note") String note);
}
