package vn.ansv.management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ansv.management.dto.Detail.DanhSachGiaHanDTO;
import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.BaoLanhThhdEntity;

@Repository
public interface BaoLanhThhdRepository extends JpaRepository<BaoLanhThhdEntity, Long> {
    /* Danh sách gia hạn */
    @Query(nativeQuery = true)
    List<DanhSachGiaHanDTO> getListGiaHan(@Param("projectReportId") Long projectReportId,
            @Param("enabled") Integer enabled);

    // Count bao_lanh_thhd by id
    @Query(value = "SELECT COUNT(blthhd.id) FROM bao_lanh_thhd AS blthhd WHERE blthhd.id = :id", nativeQuery = true)
    Integer countById(@Param("id") Long id);

    /* Thông tin bổ sung: Chi tiết báo cáo tab hợp đồng */
    @Query(nativeQuery = true)
    SupportBaoLanhHopDongDTO findDetailTabHopDongByReport(@Param("projectReportId") Long projectReportId,
            @Param("enabled") int enabled);

    @Query(nativeQuery = true)
    SupportBaoLanhHopDongDTO findDetailTabHopDongById(@Param("id") Long id);

    // Thêm mới Bảo lãnh thực hiện hợp đồng
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO bao_lanh_thhd (project_id) VALUES (:projectId)", nativeQuery = true)
    void addNewBLTHHD(@Param("projectId") Long projectId);

    /*
     * -----------------------------------------
     * Cập nhật bao_lanh_thhd theo mã bảo lãnh
     * -----------------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE bao_lanh_thhd AS blthhd "
            + "SET blthhd.modified_by = :modifiedBy, blthhd.ngay_phat_hanh = :ngayPhatHanh, "
            + "blthhd.ngay_het_han = :ngayHetHan, blthhd.note = :note "
            + "WHERE blthhd.id = :id", nativeQuery = true)
    void updateDataBaoLanhById(@Param("id") Long id, @Param("modifiedBy") String modifiedBy,
            @Param("ngayPhatHanh") String ngayPhatHanh, @Param("ngayHetHan") String ngayHetHan,
            @Param("note") String note);
}
