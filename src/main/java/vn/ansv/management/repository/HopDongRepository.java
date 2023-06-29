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
    HopDongDTO findDetailTabHopDong(@Param("projectReportId") Long projectReportId,
            @Param("enabled") int enabled);

    // Thêm mới Hợp đồng
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO hop_dong (project_id) VALUES (:projectId)", nativeQuery = true)
    void addNewHopDong(@Param("projectId") Long projectId);

    /*
     * ---------------------------------
     * Cập nhật hop_dong theo mã dự án
     * ---------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE hop_dong AS hd "
            + "INNER JOIN project AS p ON p.id = hd.project_id "
            + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
            + "SET hd.created_by = :createdBy, hd.modified_by = :modifiedBy, hd.ngay_ky = :ngayKy, "
            + "hd.ngay_hieu_luc = :ngayHieuLuc, hd.ngay_ket_thuc = :ngayKetThuc, hd.note = :note "
            + "WHERE pr.id = :projectReportId", nativeQuery = true)
    void updateCustomerByReportId(@Param("projectReportId") Long projectReportId, @Param("createdBy") String createdBy,
            @Param("modifiedBy") String modifiedBy, @Param("ngayKy") String ngayKy,
            @Param("ngayHieuLuc") String ngayHieuLuc, @Param("ngayKetThuc") String ngayKetThuc,
            @Param("note") String note);
}
