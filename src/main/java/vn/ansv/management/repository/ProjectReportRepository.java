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
import vn.ansv.management.dto.Detail.SupportCptgDTO;
import vn.ansv.management.dto.Export.ExportChuyenDoiSoDTO;
import vn.ansv.management.dto.Export.ExportTrienKhaiDTO;
import vn.ansv.management.dto.Export.ExportVienThongDTO;
import vn.ansv.management.dto.Report.ListReport12DTO;
import vn.ansv.management.dto.Report.ListReport3DTO;
import vn.ansv.management.dto.Report.ShowDashboardDTO;
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
			@Param("project_type_id") int project_type_id, @Param("week") int week,
			@Param("year") int year);

	/*
	 * -------------------------------------------------------------------
	 * Danh sách báo cáo dự án (giai đoạn 1: Viễn thông / Chuyển đổi số)
	 * View: Dashboard
	 * -------------------------------------------------------------------
	 */
	@Query(nativeQuery = true)
	List<ProjectDashboardDTO> findAllDashboardProjectStep1(@Param("enabled") int enabled,
			@Param("project_type_id") Long project_type_id, @Param("week") int week,
			@Param("year") int year);

	/*
	 * -----------------------------------------------------------------------------
	 * Danh sách báo cáo dự án (giai đoạn 1: Viễn thông / Chuyển đổi số) theo người
	 * dùng
	 * View: Dashboard
	 * -----------------------------------------------------------------------------
	 */
	@Query(nativeQuery = true)
	List<ProjectDashboardDTO> findAllDashboardProjectStep1Limit(@Param("username") String username,
			@Param("enabled") int enabled, @Param("project_type_id") Long project_type_id,
			@Param("week") int week, @Param("year") int year);

	/*
	 * ---------------------------------------------------
	 * Danh sách báo cáo dự án (giai đoạn 2: Triển khai)
	 * View: Dashboard
	 * ---------------------------------------------------
	 */
	@Query(nativeQuery = true)
	List<ProjectDashboardDTO> findAllDashboardProjectStep2(@Param("enabled") int enabled,
			@Param("project_type_id") Long project_type_id, @Param("week") int week,
			@Param("year") int year);

	/*
	 * -------------------------------------------------------------------
	 * Danh sách báo cáo dự án (giai đoạn 2: Triển khai) theo người dùng
	 * View: Dashboard
	 * -------------------------------------------------------------------
	 */
	@Query(nativeQuery = true)
	List<ProjectDashboardDTO> findAllDashboardProjectStep2Limit(@Param("username") String username,
			@Param("enabled") int enabled, @Param("project_type_id") Long project_type_id,
			@Param("week") int week, @Param("year") int year);

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
			@Param("year") int year, @Param("maHopDong") String maHopDong,
			@Param("maKeToan") String maKeToan,
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
			@Param("id") Long id, @Param("jobName") String jobName,
			@Param("description") String description,
			@Param("phanTichSwoot") String phanTichSwoot, @Param("hinhThucDauTu") String hinhThucDauTu,
			@Param("phamViCungCap") String phamViCungCap,
			@Param("tongMucDauTuDuKien") String tongMucDauTuDuKien,
			@Param("mucDoKhaThi") int mucDoKhaThi);

	/*
	 * -------------------------------------------
	 * Chi tiết báo cáo dự án theo ID và Enabled
	 * View: Detail (tab Chi phí & thời gian)
	 * -------------------------------------------
	 */
	@Query(nativeQuery = true)
	ReportDetailTabCptgDTO findDetailTabChiPhiThoiGian(@Param("id") Long id, @Param("enabled") int enabled);

	@Query(nativeQuery = true)
	SupportCptgDTO findDetailMoreDAC(@Param("id") Long id);

	@Query(nativeQuery = true)
	SupportCptgDTO findDetailMorePAC(@Param("id") Long id);

	@Query(nativeQuery = true)
	SupportCptgDTO findDetailMoreFAC(@Param("id") Long id);

	/*
	 * ----------------------------------------
	 * Cập nhật chi tiết báo cáo dự án
	 * View: Detail (tab Chi phí & thời gian)
	 * ----------------------------------------
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE project_report AS pr SET "
			+ "pr.so_tien_giao_hang = :soTienGiaoHang, pr.hop_dong_giao_hang = :hopDongGiaoHang, "
			+ "pr.muc_tieu_giao_hang = :mucTieuGiaoHang, pr.thuc_te_giao_hang = :thucTeGiaoHang, "
			+ "pr.note_giao_hang = :noteGiaoHang, "
			+ "pr.so_tien_dac = :soTienDac, pr.hop_dong_dac = :hopDongDac, pr.muc_tieu_dac = :mucTieuDac, "
			+ "pr.thuc_te_dac = :thucTeDac, pr.note_dac = :noteDac, "
			+ "pr.so_tien_pac = :soTienPac, pr.hop_dong_pac = :hopDongPac, pr.muc_tieu_pac = :mucTieuPac, "
			+ "pr.thuc_te_pac = :thucTePac, pr.note_pac = :notePac, "
			+ "pr.so_tien_fac = :soTienFac, pr.hop_dong_fac = :hopDongFac, pr.muc_tieu_fac = :mucTieuFac, "
			+ "pr.thuc_te_fac = :thucTeFac, pr.note_fac = :noteFac, "
			+ "pr.tong_gia_tri_thuc_te = :tongGiaTriThucTe, pr.note_tong_gia_tri = :noteTongGiaTri, "
			+ "pr.so_tien_tam_ung = :soTienTamUng, pr.ke_hoach_tam_ung = :keHoachTamUng, pr.note_tam_ung = :noteTamUng "
			+ "WHERE pr.id = :id", nativeQuery = true)
	void updateDetailTabCptg(
			@Param("id") Long id, @Param("soTienGiaoHang") String soTienGiaoHang,
			@Param("hopDongGiaoHang") String hopDongGiaoHang, @Param("mucTieuGiaoHang") String mucTieuGiaoHang,
			@Param("thucTeGiaoHang") String thucTeGiaoHang, @Param("noteGiaoHang") String noteGiaoHang,
			@Param("soTienDac") String soTienDac, @Param("hopDongDac") String hopDongDac,
			@Param("mucTieuDac") String mucTieuDac, @Param("thucTeDac") String thucTeDac,
			@Param("noteDac") String noteDac, @Param("soTienPac") String soTienPac,
			@Param("hopDongPac") String hopDongPac, @Param("mucTieuPac") String mucTieuPac,
			@Param("thucTePac") String thucTePac, @Param("notePac") String notePac,
			@Param("soTienFac") String soTienFac, @Param("hopDongFac") String hopDongFac,
			@Param("mucTieuFac") String mucTieuFac, @Param("thucTeFac") String thucTeFac,
			@Param("noteFac") String noteFac, @Param("tongGiaTriThucTe") String tongGiaTriThucTe,
			@Param("noteTongGiaTri") String noteTongGiaTri, @Param("soTienTamUng") String soTienTamUng,
			@Param("keHoachTamUng") String keHoachTamUng, @Param("noteTamUng") String noteTamUng);

	// Find project_report_subdata by id
	@Query(value = "SELECT COUNT(prs.id) FROM project_report_subdata AS prs "
			+ "WHERE prs.project_report_id = :projectReportId", nativeQuery = true)
	Integer countReportSubdataByReportId(@Param("projectReportId") Long projectReportId);

	/*
	 * -----------------------------------------------------------
	 * Cập nhật chi tiết báo cáo dự án (Subdata - Thông tin phụ)
	 * View: Detail (tab Chi phí & thời gian)
	 * -----------------------------------------------------------
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE project_report_subdata AS prs SET "
			+ "prs.so_tien_dac_2 = :soTienDac2, prs.hop_dong_dac_2 = :hopDongDac2, prs.muc_tieu_dac_2 = :mucTieuDac2, "
			+ "prs.thuc_te_dac_2 = :thucTeDac2, prs.note_dac_2 = :noteDac2, "
			+ "prs.so_tien_dac_3 = :soTienDac3, prs.hop_dong_dac_3 = :hopDongDac3, prs.muc_tieu_dac_3 = :mucTieuDac3, "
			+ "prs.thuc_te_dac_3 = :thucTeDac3, prs.note_dac_3 = :noteDac3, "
			+ "prs.so_tien_dac_4 = :soTienDac4, prs.hop_dong_dac_4 = :hopDongDac4, prs.muc_tieu_dac_4 = :mucTieuDac4, "
			+ "prs.thuc_te_dac_4 = :thucTeDac4, prs.note_dac_4 = :noteDac4, "
			+ "prs.so_tien_dac_5 = :soTienDac5, prs.hop_dong_dac_5 = :hopDongDac5, prs.muc_tieu_dac_5 = :mucTieuDac5, "
			+ "prs.thuc_te_dac_5 = :thucTeDac5, prs.note_dac_5 = :noteDac5, "

			+ "prs.so_tien_pac_2 = :soTienPac2, prs.hop_dong_pac_2 = :hopDongPac2, prs.muc_tieu_pac_2 = :mucTieuPac2, "
			+ "prs.thuc_te_pac_2 = :thucTePac2, prs.note_pac_2 = :notePac2, "
			+ "prs.so_tien_pac_3 = :soTienPac3, prs.hop_dong_pac_3 = :hopDongPac3, prs.muc_tieu_pac_3 = :mucTieuPac3, "
			+ "prs.thuc_te_pac_3 = :thucTePac3, prs.note_pac_3 = :notePac3, "
			+ "prs.so_tien_pac_4 = :soTienPac4, prs.hop_dong_pac_4 = :hopDongPac4, prs.muc_tieu_pac_4 = :mucTieuPac4, "
			+ "prs.thuc_te_pac_4 = :thucTePac4, prs.note_pac_4 = :notePac4, "
			+ "prs.so_tien_pac_5 = :soTienPac5, prs.hop_dong_pac_5 = :hopDongPac5, prs.muc_tieu_pac_5 = :mucTieuPac5, "
			+ "prs.thuc_te_pac_5 = :thucTePac5, prs.note_pac_5 = :notePac5, "

			+ "prs.so_tien_fac_2 = :soTienFac2, prs.hop_dong_fac_2 = :hopDongFac2, prs.muc_tieu_fac_2 = :mucTieuFac2, "
			+ "prs.thuc_te_fac_2 = :thucTeFac2, prs.note_fac_2 = :noteFac2, "
			+ "prs.so_tien_fac_3 = :soTienFac3, prs.hop_dong_fac_3 = :hopDongFac3, prs.muc_tieu_fac_3 = :mucTieuFac3, "
			+ "prs.thuc_te_fac_3 = :thucTeFac3, prs.note_fac_3 = :noteFac3, "
			+ "prs.so_tien_fac_4 = :soTienFac4, prs.hop_dong_fac_4 = :hopDongFac4, prs.muc_tieu_fac_4 = :mucTieuFac4, "
			+ "prs.thuc_te_fac_4 = :thucTeFac4, prs.note_fac_4 = :noteFac4, "
			+ "prs.so_tien_fac_5 = :soTienFac5, prs.hop_dong_fac_5 = :hopDongFac5, prs.muc_tieu_fac_5 = :mucTieuFac5, "
			+ "prs.thuc_te_fac_5 = :thucTeFac5, prs.note_fac_5 = :noteFac5 "
			+ "WHERE prs.project_report_id = :projectReportId", nativeQuery = true)
	void updateDetailTabCptgSubdata(
			@Param("projectReportId") Long projectReportId,
			@Param("soTienDac2") String soTienDac2, @Param("hopDongDac2") String hopDongDac2,
			@Param("mucTieuDac2") String mucTieuDac2, @Param("thucTeDac2") String thucTeDac2,
			@Param("noteDac2") String noteDac2,
			@Param("soTienDac3") String soTienDac3, @Param("hopDongDac3") String hopDongDac3,
			@Param("mucTieuDac3") String mucTieuDac3, @Param("thucTeDac3") String thucTeDac3,
			@Param("noteDac3") String noteDac3,
			@Param("soTienDac4") String soTienDac4, @Param("hopDongDac4") String hopDongDac4,
			@Param("mucTieuDac4") String mucTieuDac4, @Param("thucTeDac4") String thucTeDac4,
			@Param("noteDac4") String noteDac4,
			@Param("soTienDac5") String soTienDac5, @Param("hopDongDac5") String hopDongDac5,
			@Param("mucTieuDac5") String mucTieuDac5, @Param("thucTeDac5") String thucTeDac5,
			@Param("noteDac5") String noteDac5,

			@Param("soTienPac2") String soTienPac2, @Param("hopDongPac2") String hopDongPac2,
			@Param("mucTieuPac2") String mucTieuPac2, @Param("thucTePac2") String thucTePac2,
			@Param("notePac2") String notePac2,
			@Param("soTienPac3") String soTienPac3, @Param("hopDongPac3") String hopDongPac3,
			@Param("mucTieuPac3") String mucTieuPac3, @Param("thucTePac3") String thucTePac3,
			@Param("notePac3") String notePac3,
			@Param("soTienPac4") String soTienPac4, @Param("hopDongPac4") String hopDongPac4,
			@Param("mucTieuPac4") String mucTieuPac4, @Param("thucTePac4") String thucTePac4,
			@Param("notePac4") String notePac4,
			@Param("soTienPac5") String soTienPac5, @Param("hopDongPac5") String hopDongPac5,
			@Param("mucTieuPac5") String mucTieuPac5, @Param("thucTePac5") String thucTePac5,
			@Param("notePac5") String notePac5,

			@Param("soTienFac2") String soTienFac2, @Param("hopDongFac2") String hopDongFac2,
			@Param("mucTieuFac2") String mucTieuFac2, @Param("thucTeFac2") String thucTeFac2,
			@Param("noteFac2") String noteFac2,
			@Param("soTienFac3") String soTienFac3, @Param("hopDongFac3") String hopDongFac3,
			@Param("mucTieuFac3") String mucTieuFac3, @Param("thucTeFac3") String thucTeFac3,
			@Param("noteFac3") String noteFac3,
			@Param("soTienFac4") String soTienFac4, @Param("hopDongFac4") String hopDongFac4,
			@Param("mucTieuFac4") String mucTieuFac4, @Param("thucTeFac4") String thucTeFac4,
			@Param("noteFac4") String noteFac4,
			@Param("soTienFac5") String soTienFac5, @Param("hopDongFac5") String hopDongFac5,
			@Param("mucTieuFac5") String mucTieuFac5, @Param("thucTeFac5") String thucTeFac5,
			@Param("noteFac5") String noteFac5);

	// project_report_subdata: add new
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO project_report_subdata ("
			+ "so_tien_dac_2, hop_dong_dac_2, muc_tieu_dac_2, thuc_te_dac_2, note_dac_2, "
			+ "so_tien_dac_3, hop_dong_dac_3, muc_tieu_dac_3, thuc_te_dac_3, note_dac_3, "
			+ "so_tien_dac_4, hop_dong_dac_4, muc_tieu_dac_4, thuc_te_dac_4, note_dac_4, "
			+ "so_tien_dac_5, hop_dong_dac_5, muc_tieu_dac_5, thuc_te_dac_5, note_dac_5, "

			+ "so_tien_pac_2, hop_dong_pac_2, muc_tieu_pac_2, thuc_te_pac_2, note_pac_2, "
			+ "so_tien_pac_3, hop_dong_pac_3, muc_tieu_pac_3, thuc_te_pac_3, note_pac_3, "
			+ "so_tien_pac_4, hop_dong_pac_4, muc_tieu_pac_4, thuc_te_pac_4, note_pac_4, "
			+ "so_tien_pac_5, hop_dong_pac_5, muc_tieu_pac_5, thuc_te_pac_5, note_pac_5, "

			+ "so_tien_fac_2, hop_dong_fac_2, muc_tieu_fac_2, thuc_te_fac_2, note_fac_2, "
			+ "so_tien_fac_3, hop_dong_fac_3, muc_tieu_fac_3, thuc_te_fac_3, note_fac_3, "
			+ "so_tien_fac_4, hop_dong_fac_4, muc_tieu_fac_4, thuc_te_fac_4, note_fac_4, "
			+ "so_tien_fac_5, hop_dong_fac_5, muc_tieu_fac_5, thuc_te_fac_5, note_fac_5, "
			+ ") VALUES ("
			+ ":soTienDac2, :hopDongDac2, :mucTieuDac2, :thucTeDac2, :noteDac2, "
			+ ":soTienDac3, :hopDongDac3, :mucTieuDac3, :thucTeDac3, :noteDac3, "
			+ ":soTienDac4, :hopDongDac4, :mucTieuDac4, :thucTeDac4, :noteDac4, "
			+ ":soTienDac5, :hopDongDac5, :mucTieuDac5, :thucTeDac5, :noteDac5, "

			+ ":soTienPac2, :hopDongPac2, :mucTieuPac2, :thucTePac2, :notePac2, "
			+ ":soTienPac3, :hopDongPac3, :mucTieuPac3, :thucTePac3, :notePac3, "
			+ ":soTienPac4, :hopDongPac4, :mucTieuPac4, :thucTePac4, :notePac4, "
			+ ":soTienPac5, :hopDongPac5, :mucTieuPac5, :thucTePac5, :notePac5, "

			+ ":soTienFac2, :hopDongFac2, :mucTieuFac2, :thucTeFac2, :noteFac2, "
			+ ":soTienFac3, :hopDongFac3, :mucTieuFac3, :thucTeFac3, :noteFac3, "
			+ ":soTienFac4, :hopDongFac4, :mucTieuFac4, :thucTeFac4, :noteFac4, "
			+ ":soTienFac5, :hopDongFac5, :mucTieuFac5, :thucTeFac5, :noteFac5, "
			+ ")", nativeQuery = true)
	void addNewReportSubdata(
			@Param("soTienDac2") String soTienDac2, @Param("hopDongDac2") String hopDongDac2,
			@Param("mucTieuDac2") String mucTieuDac2, @Param("thucTeDac2") String thucTeDac2,
			@Param("noteDac2") String noteDac2,
			@Param("soTienDac3") String soTienDac3, @Param("hopDongDac3") String hopDongDac3,
			@Param("mucTieuDac3") String mucTieuDac3, @Param("thucTeDac3") String thucTeDac3,
			@Param("noteDac3") String noteDac3,
			@Param("soTienDac4") String soTienDac4, @Param("hopDongDac4") String hopDongDac4,
			@Param("mucTieuDac4") String mucTieuDac4, @Param("thucTeDac4") String thucTeDac4,
			@Param("noteDac4") String noteDac4,
			@Param("soTienDac5") String soTienDac5, @Param("hopDongDac5") String hopDongDac5,
			@Param("mucTieuDac5") String mucTieuDac5, @Param("thucTeDac5") String thucTeDac5,
			@Param("noteDac5") String noteDac5,

			@Param("soTienPac2") String soTienPac2, @Param("hopDongPac2") String hopDongPac2,
			@Param("mucTieuPac2") String mucTieuPac2, @Param("thucTePac2") String thucTePac2,
			@Param("notePac2") String notePac2,
			@Param("soTienPac3") String soTienPac3, @Param("hopDongPac3") String hopDongPac3,
			@Param("mucTieuPac3") String mucTieuPac3, @Param("thucTePac3") String thucTePac3,
			@Param("notePac3") String notePac3,
			@Param("soTienPac4") String soTienPac4, @Param("hopDongPac4") String hopDongPac4,
			@Param("mucTieuPac4") String mucTieuPac4, @Param("thucTePac4") String thucTePac4,
			@Param("notePac4") String notePac4,
			@Param("soTienPac5") String soTienPac5, @Param("hopDongPac5") String hopDongPac5,
			@Param("mucTieuPac5") String mucTieuPac5, @Param("thucTePac5") String thucTePac5,
			@Param("notePac5") String notePac5,

			@Param("soTienFac2") String soTienFac2, @Param("hopDongFac2") String hopDongFac2,
			@Param("mucTieuFac2") String mucTieuFac2, @Param("thucTeFac2") String thucTeFac2,
			@Param("noteFac2") String noteFac2,
			@Param("soTienFac3") String soTienFac3, @Param("hopDongFac3") String hopDongFac3,
			@Param("mucTieuFac3") String mucTieuFac3, @Param("thucTeFac3") String thucTeFac3,
			@Param("noteFac3") String noteFac3,
			@Param("soTienFac4") String soTienFac4, @Param("hopDongFac4") String hopDongFac4,
			@Param("mucTieuFac4") String mucTieuFac4, @Param("thucTeFac4") String thucTeFac4,
			@Param("noteFac4") String noteFac4,
			@Param("soTienFac5") String soTienFac5, @Param("hopDongFac5") String hopDongFac5,
			@Param("mucTieuFac5") String mucTieuFac5, @Param("thucTeFac5") String thucTeFac5,
			@Param("noteFac5") String noteFac5);

	/*
	 * -------------------------------------------
	 * Chi tiết báo cáo dự án theo ID và Enabled
	 * View: Detail (tab Quá trình)
	 * -------------------------------------------
	 */
	@Query(nativeQuery = true)
	ReportDetailTabQuaTrinhDTO findDetailTabQuaTrinh(@Param("id") Long id, @Param("enabled") int enabled);

	/* Start: Danh sách dự án type 12 */
	// Danh sách KD viễn thông và KD chuyển đổi số (ALL)
	@Query(nativeQuery = true)
	List<ListReport12DTO> findAllReportType12(@Param("project_type_id") Long project_type_id,
			@Param("startPosition") int startPosition, @Param("pageSize") int pageSize);

	// Danh sách KD viễn thông và KD chuyển đổi số (by username)
	@Query(nativeQuery = true)
	List<ListReport12DTO> findAllReportType12Limit(@Param("username") String username,
			@Param("project_type_id") Long project_type_id, @Param("week") int week,
			@Param("year") int year, @Param("startPosition") int startPosition,
			@Param("pageSize") int pageSize);

	// Week
	@Query(nativeQuery = true)
	List<ListReport12DTO> findAllReportType12WeekYear(@Param("project_type_id") Long project_type_id,
			@Param("week") int week, @Param("year") int year, @Param("startPosition") int startPosition,
			@Param("pageSize") int pageSize);

	// CurrentDate
	@Query(nativeQuery = true)
	List<ListReport12DTO> findAllReportType12CurrentDate(@Param("project_type_id") Long project_type_id,
			@Param("startPosition") int startPosition, @Param("pageSize") int pageSize);

	/* End: Danh sách dự án type 12 */

	/* Start: Danh sách Triển khai */
	// Week
	@Query(nativeQuery = true)
	List<ListReport3DTO> findListReportType3WeekYear(@Param("project_type_id") Long project_type_id,
			@Param("week") int week, @Param("year") int year, @Param("startPosition") int startPosition,
			@Param("pageSize") int pageSize);

	// CurrentDate
	@Query(nativeQuery = true)
	List<ListReport3DTO> findListReportType3CurrentDate(@Param("project_type_id") Long project_type_id,
			@Param("startPosition") int startPosition, @Param("pageSize") int pageSize);

	// All
	@Query(nativeQuery = true)
	List<ListReport3DTO> findListReportType3All(@Param("project_type_id") Long project_type_id,
			@Param("startPosition") int startPosition, @Param("pageSize") int pageSize);
	/* End: Danh sách Triển khai */

	/* Danh sách Triển khai (by username) */
	@Query(nativeQuery = true)
	List<ListReport3DTO> findListReportType3WeekYearByUser(@Param("username") String username,
			@Param("project_type_id") Long project_type_id, @Param("week") int week,
			@Param("year") int year, @Param("startPosition") int startPosition,
			@Param("pageSize") int pageSize);

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
			@Param("id") Long id, @Param("generalIssue") String generalIssue,
			@Param("solution") String solution,
			@Param("keHoachTuanNay") String keHoachTuanNay, @Param("keHoachTuanSau") String keHoachTuanSau,
			@Param("ketQuaTuanTruoc") String ketQuaTuanTruoc, @Param("ketQuaTuanNay") String ketQuaTuanNay);

	// Kiểm tra tồn tại của report
	@Query(value = "SELECT COUNT(p.id) FROM project_report AS p WHERE p.id = :id", nativeQuery = true)
	Integer checkReportById(@Param("id") Long id);

	/*
	 * Xoá report
	 * View danh sách
	 */
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM project_report WHERE id = :id", nativeQuery = true)
	int deleteReportById(@Param("id") Long id);

	// Thêm mới báo cáo
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO project_report (uid, am_id, am_manager_id, pm_id, pm_manager_id, "
			+ "created_by, project_id, project_type_id, project_priority_id, project_status_id, "
			+ "week, year, ma_hop_dong, ma_ke_toan, currency_unit_id, job_name, description, enabled, "
			+ "muc_do_kha_thi, tong_muc_dau_tu_du_kien, hinh_thuc_dau_tu, pham_vi_cung_cap, "
			+ "phan_tich_swoot, so_tien_giao_hang, hop_dong_giao_hang, muc_tieu_giao_hang, "
			+ "thuc_te_giao_hang, note_giao_hang, so_tien_dac, hop_dong_dac, muc_tieu_dac, "
			+ "thuc_te_dac, note_dac, so_tien_pac, hop_dong_pac, muc_tieu_pac, thuc_te_pac, note_pac, "
			+ "so_tien_fac, hop_dong_fac, muc_tieu_fac, thuc_te_fac, note_fac, tong_gia_tri_thuc_te, "
			+ "note_tong_gia_tri, so_tien_tam_ung, ke_hoach_tam_ung, note_tam_ung, tien_do_chung, "
			+ "general_issue, solution, ke_hoach_tuan_nay, ke_hoach_tuan_sau, ket_qua_tuan_truoc, "
			+ "ket_qua_tuan_nay) "
			+ "VALUES (:uid, :amId, :amManagerId, :pmId, :pmManagerId, :createdBy, :projectId, "
			+ ":projectTypeId, :projectPriorityId, :projectStatusId, :week, :year, :maHopDong, "
			+ ":maKeToan, :currencyUnitId, :jobName, :description, :enabled, :mucDoKhaThi, "
			+ ":tongMucDauTuDuKien, :hinhThucDauTu, :phamViCungCap, :phanTichSwoot, :soTienGiaoHang, "
			+ ":hopDongGiaoHang, :mucTieuGiaoHang, :thucTeGiaoHang, :noteGiaoHang, :soTienDac, "
			+ ":hopDongDac, :mucTieuDac, :thucTeDac, :noteDac, :soTienPac, :hopDongPac, :mucTieuPac, "
			+ ":thucTePac, :notePac, :soTienFac, :hopDongFac, :mucTieuFac, :thucTeFac, :noteFac, "
			+ ":tongGiaTriThucTe, :noteTongGiaTri, :soTienTamUng, :keHoachTamUng, :noteTamUng, "
			+ ":tienDoChung, :generalIssue, :solution, :keHoachTuanNay, :keHoachTuanSau, "
			+ ":ketQuaTuanTruoc, :ketQuaTuanNay)", nativeQuery = true)
	void addNewReport(@Param("uid") String uid, @Param("amId") Long amId, @Param("amManagerId") Long amManagerId,
			@Param("pmId") Long pmId, @Param("pmManagerId") Long pmManagerId,
			@Param("createdBy") String createdBy,
			@Param("projectId") Long projectId, @Param("projectTypeId") Long projectTypeId,
			@Param("projectPriorityId") Long projectPriorityId,
			@Param("projectStatusId") Long projectStatusId,
			@Param("week") int week, @Param("year") int year, @Param("maHopDong") String maHopDong,
			@Param("maKeToan") String maKeToan, @Param("currencyUnitId") Long currencyUnitId,
			@Param("jobName") String jobName, @Param("description") String description,
			@Param("enabled") Integer enabled, @Param("mucDoKhaThi") Integer mucDoKhaThi,
			@Param("tongMucDauTuDuKien") String tongMucDauTuDuKien,
			@Param("hinhThucDauTu") String hinhThucDauTu,
			@Param("phamViCungCap") String phamViCungCap, @Param("phanTichSwoot") String phanTichSwoot,
			@Param("soTienGiaoHang") String soTienGiaoHang,
			@Param("hopDongGiaoHang") String hopDongGiaoHang,
			@Param("mucTieuGiaoHang") String mucTieuGiaoHang,
			@Param("thucTeGiaoHang") String thucTeGiaoHang,
			@Param("noteGiaoHang") String noteGiaoHang,
			@Param("soTienDac") String soTienDac, @Param("hopDongDac") String hopDongDac,
			@Param("mucTieuDac") String mucTieuDac, @Param("thucTeDac") String thucTeDac,
			@Param("noteDac") String noteDac,
			@Param("soTienPac") String soTienPac, @Param("hopDongPac") String hopDongPac,
			@Param("mucTieuPac") String mucTieuPac, @Param("thucTePac") String thucTePac,
			@Param("notePac") String notePac,
			@Param("soTienFac") String soTienFac, @Param("hopDongFac") String hopDongFac,
			@Param("mucTieuFac") String mucTieuFac, @Param("thucTeFac") String thucTeFac,
			@Param("noteFac") String noteFac,
			@Param("tongGiaTriThucTe") String tongGiaTriThucTe,
			@Param("noteTongGiaTri") String noteTongGiaTri,
			@Param("soTienTamUng") String soTienTamUng, @Param("keHoachTamUng") String keHoachTamUng,
			@Param("noteTamUng") String noteTamUng, @Param("tienDoChung") String tienDoChung,
			@Param("generalIssue") String generalIssue, @Param("solution") String solution,
			@Param("keHoachTuanNay") String keHoachTuanNay, @Param("keHoachTuanSau") String keHoachTuanSau,
			@Param("ketQuaTuanTruoc") String ketQuaTuanTruoc, @Param("ketQuaTuanNay") String ketQuaTuanNay);

	// Cập nhật báo cáo
	@Transactional
	@Modifying
	@Query(value = "UPDATE project_report AS pr SET uid = :uid, am_id = :amId, am_manager_id = :amManagerId, "
			+ "pm_id = :pmId, pm_manager_id = :pmManagerId, created_by = :createdBy, project_id = :projectId, "
			+ "project_type_id = :projectTypeId, project_priority_id = :projectPriorityId, "
			+ "project_status_id = :projectStatusId, week = :week, year = :year, ma_hop_dong = :maHopDong, "
			+ "ma_ke_toan = :maKeToan, currency_unit_id = :currencyUnitId, job_name = :jobName, "
			+ "description = :description, enabled = :enabled, muc_do_kha_thi = :mucDoKhaThi, "
			+ "tong_muc_dau_tu_du_kien = :tongMucDauTuDuKien, hinh_thuc_dau_tu = :hinhThucDauTu, "
			+ "pham_vi_cung_cap = :phamViCungCap, phan_tich_swoot = :phanTichSwoot, so_tien_dac = :soTienDac, "
			+ "hop_dong_dac = :hopDongDac, muc_tieu_dac = :mucTieuDac, thuc_te_dac = :thucTeDac, "
			+ "so_tien_pac = :soTienPac, hop_dong_pac = :hopDongPac, muc_tieu_pac = :mucTieuPac, "
			+ "thuc_te_pac = :thucTePac, so_tien_fac = :soTienFac, hop_dong_fac = :hopDongFac, "
			+ "muc_tieu_fac = :mucTieuFac, thuc_te_fac = :thucTeFac, tong_gia_tri_thuc_te = :tongGiaTriThucTe, "
			+ "so_tien_tam_ung = :soTienTamUng, ke_hoach_tam_ung = :keHoachTamUng, tien_do_chung = :tienDoChung, "
			+ "general_issue = :generalIssue, solution = :solution, ke_hoach_tuan_nay = :keHoachTuanNay, "
			+ "ke_hoach_tuan_sau = :keHoachTuanSau, ket_qua_tuan_truoc = :ketQuaTuanTruoc, "
			+ "ket_qua_tuan_nay = :ketQuaTuanNay "
			+ "WHERE id = :id", nativeQuery = true)
	void updateReport(@Param("id") Long id, @Param("uid") String uid, @Param("amId") Long amId,
			@Param("amManagerId") Long amManagerId, @Param("pmId") Long pmId,
			@Param("pmManagerId") Long pmManagerId,
			@Param("createdBy") String createdBy, @Param("projectId") Long projectId,
			@Param("projectTypeId") Long projectTypeId, @Param("projectPriorityId") Long projectPriorityId,
			@Param("projectStatusId") Long projectStatusId, @Param("week") int week,
			@Param("year") int year,
			@Param("maHopDong") String maHopDong, @Param("maKeToan") String maKeToan,
			@Param("currencyUnitId") Long currencyUnitId, @Param("jobName") String jobName,
			@Param("description") String description, @Param("enabled") Integer enabled,
			@Param("mucDoKhaThi") Integer mucDoKhaThi,
			@Param("tongMucDauTuDuKien") String tongMucDauTuDuKien,
			@Param("hinhThucDauTu") String hinhThucDauTu, @Param("phamViCungCap") String phamViCungCap,
			@Param("phanTichSwoot") String phanTichSwoot, @Param("soTienDac") String soTienDac,
			@Param("hopDongDac") String hopDongDac, @Param("mucTieuDac") String mucTieuDac,
			@Param("thucTeDac") String thucTeDac, @Param("soTienPac") String soTienPac,
			@Param("hopDongPac") String hopDongPac, @Param("mucTieuPac") String mucTieuPac,
			@Param("thucTePac") String thucTePac, @Param("soTienFac") String soTienFac,
			@Param("hopDongFac") String hopDongFac, @Param("mucTieuFac") String mucTieuFac,
			@Param("thucTeFac") String thucTeFac, @Param("tongGiaTriThucTe") String tongGiaTriThucTe,
			@Param("soTienTamUng") String soTienTamUng, @Param("keHoachTamUng") String keHoachTamUng,
			@Param("tienDoChung") String tienDoChung, @Param("generalIssue") String generalIssue,
			@Param("solution") String solution, @Param("keHoachTuanNay") String keHoachTuanNay,
			@Param("keHoachTuanSau") String keHoachTuanSau,
			@Param("ketQuaTuanTruoc") String ketQuaTuanTruoc,
			@Param("ketQuaTuanNay") String ketQuaTuanNay);

	/*
	 * Hiển thị model show
	 * view: Dashboard
	 */
	@Query(nativeQuery = true)
	List<ShowDashboardDTO> modalShowDashboard(@Param("enabled") int enabled, @Param("week") int week,
			@Param("year") int year, @Param("project_status_id") Long project_status_id,
			@Param("project_type_id") Long project_type_id);

	// Find report's id by jobName
	@Query(value = "SELECT pr.id FROM project_report AS pr WHERE pr.job_name = :jobName", nativeQuery = true)
	Long findIdByJobName(@Param("jobName") String jobName);

	// Tìm kiếm report's id theo jobName, week, year
	@Query(value = "SELECT pr.id FROM project_report AS pr "
			+ "WHERE pr.job_name = :jobName AND pr.week = :week AND pr.year = :year", nativeQuery = true)
	Long findIdByJobNameWeekYear(@Param("jobName") String jobName, @Param("week") Integer week,
			@Param("year") Integer year);

	@Query(nativeQuery = true)
	List<ExportVienThongDTO> findAllExportVienThong(
			@Param("type") int type, @Param("week") int week, @Param("year") int year);

	@Query(nativeQuery = true)
	List<ExportChuyenDoiSoDTO> findAllExportChuyenDoiSo(
			@Param("type") int type, @Param("week") int week, @Param("year") int year);

	@Query(nativeQuery = true)
	List<ExportTrienKhaiDTO> findAllExportTrienKhai(
			@Param("type") int type, @Param("week") int week, @Param("year") int year);

	// Kiểm tra tồn tại của report
	@Query(value = "SELECT COUNT(pr.id) FROM project_report AS pr "
			+ "WHERE pr.week = :week AND pr.year = :year AND pr.project_type_id = :type AND pr.enabled = :enabled "
			+ "AND pr.project_priority_id = :priority AND pr.project_status_id = :status", nativeQuery = true)
	Integer countReportForDashboardChart(@Param("week") int week, @Param("year") int year, @Param("type") Long type,
			@Param("enabled") int enabled, @Param("priority") Long priority, @Param("status") Long status);

	// Count all by type and week
	@Query(value = "SELECT COUNT(pr.id) AS totalReport FROM project_report AS pr "
			+ "WHERE pr.year = :year AND pr.week = :week AND pr.project_type_id = :type", nativeQuery = true)
	Integer countAllByTypeWeekYear(@Param("week") int week, @Param("year") int year, @Param("type") Long type);

	// Count all by type and current date
	@Query(value = "SELECT COUNT(pr.id) AS totalReport FROM project_report AS pr "
			+ "WHERE DATE(pr.created_date) = CURDATE() AND pr.project_type_id = :type", nativeQuery = true)
	Integer countAllByTypeCurrentDate(@Param("type") Long type);

	// Count all by type
	@Query(value = "SELECT COUNT(pr.id) AS totalReport FROM project_report AS pr "
			+ "WHERE pr.project_type_id = :type", nativeQuery = true)
	Integer countAllByType(@Param("type") Long type);
}
