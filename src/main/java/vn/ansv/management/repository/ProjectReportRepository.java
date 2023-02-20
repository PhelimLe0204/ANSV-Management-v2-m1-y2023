package vn.ansv.management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabDuThauDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabPhanLoaiDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabQuaTrinhDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabCptgDTO;
import vn.ansv.management.entity.ProjectReportEntity;

@Repository
public interface ProjectReportRepository extends JpaRepository<ProjectReportEntity, Long> {
    /* TEST */
    @Query(value = "SELECT pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
            + "(SELECT u.fullname FROM tbl_user AS u WHERE u.id = pr.am_id) AS picName, "
            + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
            + "FROM project_report AS pr "
            + "INNER JOIN project AS p ON pr.project_id = p.id "
            + "INNER JOIN customer AS c ON p.customer_id = c.id "
            + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
            + "WHERE pr.enabled =:enabled AND pr.project_type_id =:project_type_id AND pr.week =:week AND pr.year =:year "
            + "ORDER BY pr.job_name", nativeQuery = true)
    // Danh sách báo cáo dự án trên Dashboard
    List<ProjectDashboardDTO> findAllDashboardProjectType1(@Param("enabled") int enabled,
            @Param("project_type_id") int project_type_id, @Param("week") int week, @Param("year") int year);

    /*
     * -------------------------------------------------------------------
     * Danh sách báo cáo dự án (giai đoạn 1: Viễn thông / Chuyển đổi số)
     * View: Dashboard
     * -------------------------------------------------------------------
     */
    @Query(nativeQuery = true)
    List<ProjectDashboardDTO> findAllDashboardProjectStep1(@Param("enabled") int enabled,
            @Param("project_type_id") Long project_type_id, @Param("week") int week, @Param("year") int year);

    /*
     * ---------------------------------------------------
     * Danh sách báo cáo dự án (giai đoạn 2: Triển khai)
     * View: Dashboard
     * ---------------------------------------------------
     */
    @Query(nativeQuery = true)
    List<ProjectDashboardDTO> findAllDashboardProjectStep2(@Param("enabled") int enabled,
            @Param("project_type_id") Long project_type_id, @Param("week") int week, @Param("year") int year);

    /*
     * -------------------------------------------
     * Chi tiết báo cáo dự án theo ID và Enabled
     * View: Detail (tab Phân loại)
     * -------------------------------------------
     */
    @Query(nativeQuery = true)
    ReportDetailTabPhanLoaiDTO findDetailTabPhanLoai(@Param("id") Long id, @Param("enabled") int enabled);

    /*
     * ---------------------------------
     * Cập nhật chi tiết báo cáo dự án
     * View: Detail (tab Phân loại)
     * ---------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE project_report AS pr SET pr.project_id = :projectId, pr.project_type_id = :typeId, "
            + "pr.project_priority_id = :priorityId, pr.project_status_id = :statusId, pr.week = :week, "
            + "pr.year = :year, pr.ma_hop_dong = :maHopDong, pr.ma_ke_toan = :maKeToan, pr.currency_unit_id = :currencyUnitId "
            + "WHERE pr.id = :id", nativeQuery = true)
    void updateDetailTabPhanLoai(
            @Param("id") Long id, @Param("projectId") Long projectId, @Param("typeId") Long typeId,
            @Param("priorityId") Long priorityId, @Param("statusId") Long statusId, @Param("week") int week,
            @Param("year") int year, @Param("maHopDong") String maHopDong, @Param("maKeToan") String maKeToan,
            @Param("currencyUnitId") Long currencyUnitId);

    /*
     * -------------------------------------------
     * Chi tiết báo cáo dự án theo ID và Enabled
     * View: Detail (tab Dự thầu)
     * -------------------------------------------
     */
    @Query(nativeQuery = true)
    ReportDetailTabDuThauDTO findDetailTabDuThau(@Param("id") Long id, @Param("enabled") int enabled);

    /*
     * ---------------------------------
     * Cập nhật chi tiết báo cáo dự án
     * View: Detail (tab Dự thầu)
     * ---------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE project_report AS pr SET pr.job_name = :jobName, pr.description = :description, "
            + "pr.phan_tich_swoot = :phanTichSwoot, pr.hinh_thuc_dau_tu = :hinhThucDauTu, pr.pham_vi_cung_cap = :phamViCungCap, "
            + "pr.tong_muc_dau_tu_du_kien = :tongMucDauTuDuKien, pr.muc_do_kha_thi = :mucDoKhaThi "
            + "WHERE pr.id = :id", nativeQuery = true)
    void updateDetailTabDuThau(
            @Param("id") Long id, @Param("jobName") String jobName, @Param("description") String description,
            @Param("phanTichSwoot") String phanTichSwoot, @Param("hinhThucDauTu") String hinhThucDauTu,
            @Param("phamViCungCap") String phamViCungCap, @Param("tongMucDauTuDuKien") String tongMucDauTuDuKien,
            @Param("mucDoKhaThi") int mucDoKhaThi);

    /*
     * -------------------------------------------
     * Chi tiết báo cáo dự án theo ID và Enabled
     * View: Detail (tab Chi phí & thời gian)
     * -------------------------------------------
     */
    @Query(nativeQuery = true)
    ReportDetailTabCptgDTO findDetailTabChiPhiThoiGian(@Param("id") Long id, @Param("enabled") int enabled);

    /*
     * ----------------------------------------
     * Cập nhật chi tiết báo cáo dự án
     * View: Detail (tab Chi phí & thời gian)
     * ----------------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE project_report AS pr SET "
            + "pr.so_tien_dac = :soTienDac, pr.hop_dong_dac = :hopDongDac, pr.muc_tieu_dac = :mucTieuDac, pr.thuc_te_dac = :thucTeDac, "
            + "pr.so_tien_pac = :soTienPac, pr.hop_dong_pac = :hopDongPac, pr.muc_tieu_pac = :mucTieuPac, pr.thuc_te_pac = :thucTePac, "
            + "pr.so_tien_fac = :soTienFac, pr.hop_dong_fac = :hopDongFac, pr.muc_tieu_fac = :mucTieuFac, pr.thuc_te_fac = :thucTeFac, "
            + "pr.tong_gia_tri_thuc_te = :tongGiaTriThucTe, pr.so_tien_tam_ung = :soTienTamUng, pr.ke_hoach_tam_ung = :keHoachTamUng "
            + "WHERE pr.id = :id", nativeQuery = true)
    void updateDetailTabCptg(
            @Param("id") Long id,
            @Param("soTienDac") String soTienDac, @Param("hopDongDac") String hopDongDac,
            @Param("mucTieuDac") String mucTieuDac, @Param("thucTeDac") String thucTeDac,
            @Param("soTienPac") String soTienPac, @Param("hopDongPac") String hopDongPac,
            @Param("mucTieuPac") String mucTieuPac, @Param("thucTePac") String thucTePac,
            @Param("soTienFac") String soTienFac, @Param("hopDongFac") String hopDongFac,
            @Param("mucTieuFac") String mucTieuFac, @Param("thucTeFac") String thucTeFac,
            @Param("tongGiaTriThucTe") String tongGiaTriThucTe, @Param("soTienTamUng") String soTienTamUng,
            @Param("keHoachTamUng") String keHoachTamUng);

    /*
     * -------------------------------------------
     * Chi tiết báo cáo dự án theo ID và Enabled
     * View: Detail (tab Quá trình)
     * -------------------------------------------
     */
    @Query(nativeQuery = true)
    ReportDetailTabQuaTrinhDTO findDetailTabQuaTrinh(@Param("id") Long id, @Param("enabled") int enabled);

    /*
     * ----------------------------------------
     * Cập nhật chi tiết báo cáo dự án
     * View: Detail (tab Quá trình)
     * ----------------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE project_report AS pr SET "
            + "pr.general_issue = :generalIssue, pr.solution = :solution, pr.ke_hoach_tuan_nay = :keHoachTuanNay, "
            + "pr.ke_hoach_tuan_sau = :keHoachTuanSau, pr.ket_qua_tuan_truoc = :ketQuaTuanTruoc, "
            + "pr.ket_qua_tuan_nay = :ketQuaTuanNay "
            + "WHERE pr.id = :id", nativeQuery = true)
    void updateDetailTabQuaTrinh(
            @Param("id") Long id, @Param("generalIssue") String generalIssue, @Param("solution") String solution,
            @Param("keHoachTuanNay") String keHoachTuanNay, @Param("keHoachTuanSau") String keHoachTuanSau,
            @Param("ketQuaTuanTruoc") String ketQuaTuanTruoc, @Param("ketQuaTuanNay") String ketQuaTuanNay);
}
