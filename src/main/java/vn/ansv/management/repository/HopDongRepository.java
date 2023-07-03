package vn.ansv.management.repository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.Detail.HopDongDTO;
import vn.ansv.management.entity.HopDongEntity;

@Repository
public interface HopDongRepository extends JpaRepository<HopDongEntity, Long> {
    /* Thông tin bổ sung: Chi tiết báo cáo tab hợp đồng */
    @Query(nativeQuery = true)
    HopDongDTO findDetailTabHopDongByReport(@Param("projectReportId") Long projectReportId,
            @Param("enabled") int enabled);

    @Query(nativeQuery = true)
    HopDongDTO findDetailTabHopDongById(@Param("id") Long id);

    // Thêm mới Hợp đồng
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO hop_dong (project_id) VALUES (:projectId)", nativeQuery = true)
    void addNewHopDong(@Param("projectId") Long projectId);

    // Count hop_dong by id
    @Query(value = "SELECT COUNT(hd.id) FROM hop_dong AS hd "
            + "INNER JOIN project AS p ON p.id = hd.project_id "
            + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
            + "WHERE hd.id = :id AND pr.id = :reportId", nativeQuery = true)
    Integer countByIdAndReport(@Param("id") Long id, @Param("reportId") Long reportId);

    /*
     * ------------------------------------
     * Cập nhật hop_dong theo mã hợp đồng
     * ------------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE hop_dong AS hd "
            + "INNER JOIN project AS p ON p.id = hd.project_id "
            + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
            + "SET hd.created_by = :createdBy, hd.modified_by = :modifiedBy, hd.ngay_ky = :ngayKy, "
            + "hd.ngay_hieu_luc = :ngayHieuLuc, hd.ngay_ket_thuc = :ngayKetThuc, hd.note = :note "
            + "WHERE hd.id = :id", nativeQuery = true)
    void updateDataHopDongById(@Param("id") Long id, @Param("createdBy") String createdBy,
            @Param("modifiedBy") String modifiedBy, @Param("ngayKy") String ngayKy,
            @Param("ngayHieuLuc") String ngayHieuLuc, @Param("ngayKetThuc") String ngayKetThuc,
            @Param("note") String note);
}
