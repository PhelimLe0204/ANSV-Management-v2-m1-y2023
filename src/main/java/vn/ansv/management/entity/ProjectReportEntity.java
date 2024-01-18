package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

/* ===== ProjectReportRepository.findAllExportVienThong() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllExportVienThong", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, pr.description, "
        + "pr.hinh_thuc_dau_tu AS hinhThucDauTu, pr.tong_muc_dau_tu_du_kien AS tongMucDauTuDuKien, "
        + "pr.muc_do_kha_thi AS mucDoKhaThi, pr.phan_tich_swoot AS phanTichSwoot, "
        + "pr.general_issue AS generalIssue, pr.solution, pp.priority_name AS priorityName, "
        + "ps.status_name AS statusName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS amName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_manager_id) AS amManagerName, "
        + "pr.ket_qua_tuan_truoc AS ketQuaTuanTruoc, pr.ket_qua_tuan_nay AS ketQuaTuanNay, "
        + "pr.ke_hoach_tuan_nay AS keHoachTuanNay, pr.ke_hoach_tuan_sau AS keHoachTuanSau "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.project_type_id = :type AND pr.week = :week AND pr.year = :year "
        + "ORDER BY pr.id", resultSetMapping = "Mapping.ExportVienThongDTO")

/* ===== Set mapping: ExportVienThongDTO ===== */
@SqlResultSetMapping(name = "Mapping.ExportVienThongDTO", classes = @ConstructorResult(targetClass = ExportVienThongDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "description", type = String.class),
        @ColumnResult(name = "hinhThucDauTu", type = String.class),
        @ColumnResult(name = "tongMucDauTuDuKien", type = String.class),
        @ColumnResult(name = "mucDoKhaThi", type = Integer.class),
        @ColumnResult(name = "phanTichSwoot", type = String.class),
        @ColumnResult(name = "generalIssue", type = String.class),
        @ColumnResult(name = "solution", type = String.class),
        @ColumnResult(name = "priorityName", type = String.class),
        @ColumnResult(name = "statusName", type = String.class),
        @ColumnResult(name = "amName", type = String.class),
        @ColumnResult(name = "amManagerName", type = String.class),
        @ColumnResult(name = "ketQuaTuanTruoc", type = String.class),
        @ColumnResult(name = "ketQuaTuanNay", type = String.class),
        @ColumnResult(name = "keHoachTuanNay", type = String.class),
        @ColumnResult(name = "keHoachTuanSau", type = String.class)
}))

/* ===== ProjectReportRepository.findAllExportChuyenDoiSo() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllExportChuyenDoiSo", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, pr.description, "
        + "pr.hinh_thuc_dau_tu AS hinhThucDauTu, pr.tong_muc_dau_tu_du_kien AS tongMucDauTuDuKien, "
        + "pr.muc_do_kha_thi AS mucDoKhaThi, pr.phan_tich_swoot AS phanTichSwoot, "
        + "pr.general_issue AS generalIssue, pr.solution, pp.priority_name AS priorityName, "
        + "ps.status_name AS statusName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS amName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_manager_id) AS amManagerName, "
        + "pr.ket_qua_tuan_truoc AS ketQuaTuanTruoc, pr.ket_qua_tuan_nay AS ketQuaTuanNay, "
        + "pr.ke_hoach_tuan_nay AS keHoachTuanNay, pr.ke_hoach_tuan_sau AS keHoachTuanSau "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.project_type_id = :type AND pr.week = :week AND pr.year = :year "
        + "ORDER BY pr.id", resultSetMapping = "Mapping.ExportChuyenDoiSoDTO")

/* ===== Set mapping: ExportChuyenDoiSoDTO ===== */
@SqlResultSetMapping(name = "Mapping.ExportChuyenDoiSoDTO", classes = @ConstructorResult(targetClass = ExportChuyenDoiSoDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "description", type = String.class),
        @ColumnResult(name = "hinhThucDauTu", type = String.class),
        @ColumnResult(name = "tongMucDauTuDuKien", type = String.class),
        @ColumnResult(name = "mucDoKhaThi", type = Integer.class),
        @ColumnResult(name = "phanTichSwoot", type = String.class),
        @ColumnResult(name = "generalIssue", type = String.class),
        @ColumnResult(name = "solution", type = String.class),
        @ColumnResult(name = "priorityName", type = String.class),
        @ColumnResult(name = "statusName", type = String.class),
        @ColumnResult(name = "amName", type = String.class),
        @ColumnResult(name = "amManagerName", type = String.class),
        @ColumnResult(name = "ketQuaTuanTruoc", type = String.class),
        @ColumnResult(name = "ketQuaTuanNay", type = String.class),
        @ColumnResult(name = "keHoachTuanNay", type = String.class),
        @ColumnResult(name = "keHoachTuanSau", type = String.class)
}))

/* ===== ProjectReportRepository.findAllExportTrienKhai() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllExportTrienKhai", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, pr.ma_hop_dong AS maHopDong, pr.ma_ke_toan AS maKeToan, "
        + "c.customer_name AS customerName, pr.tong_gia_tri_thuc_te AS tongGiaTriThucTe, "
        + "pr.so_tien_dac AS soTienDac, pr.hop_dong_dac AS hopDongDac, pr.muc_tieu_dac AS mucTieuDac, "
        + "pr.thuc_te_dac AS thucTeDac, pr.so_tien_pac AS soTienPac, pr.hop_dong_pac AS hopDongPac, "
        + "pr.muc_tieu_pac AS mucTieuPac ,pr.thuc_te_pac AS thucTePac, pr.so_tien_fac AS soTienFac, "
        + "pr.hop_dong_fac AS hopDongFac, pr.muc_tieu_fac AS mucTieuFac, pr.thuc_te_fac AS thucTeFac, "
        + "pr.tien_do_chung AS tienDoChung, pr.general_issue AS generalIssue, pr.solution, "
        + "pp.priority_name AS priorityName, ps.status_name AS statusName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS amName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS pmName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_manager_id) AS pmManagerName, "
        + "pr.ket_qua_tuan_truoc AS ketQuaTuanTruoc, pr.ket_qua_tuan_nay AS ketQuaTuanNay, "
        + "pr.ke_hoach_tuan_nay AS keHoachTuanNay, pr.ke_hoach_tuan_sau AS keHoachTuanSau "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.project_type_id = :type AND pr.week = :week AND pr.year = :year "
        + "ORDER BY pr.id", resultSetMapping = "Mapping.ExportTrienKhaiDTO")

/* ===== ProjectReportRepository.findExportTrienKhaiByPM() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findExportTrienKhaiByPM", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, pr.ma_hop_dong AS maHopDong, pr.ma_ke_toan AS maKeToan, "
        + "c.customer_name AS customerName, pr.tong_gia_tri_thuc_te AS tongGiaTriThucTe, "
        + "pr.so_tien_dac AS soTienDac, pr.hop_dong_dac AS hopDongDac, pr.muc_tieu_dac AS mucTieuDac, "
        + "pr.thuc_te_dac AS thucTeDac, pr.so_tien_pac AS soTienPac, pr.hop_dong_pac AS hopDongPac, "
        + "pr.muc_tieu_pac AS mucTieuPac ,pr.thuc_te_pac AS thucTePac, pr.so_tien_fac AS soTienFac, "
        + "pr.hop_dong_fac AS hopDongFac, pr.muc_tieu_fac AS mucTieuFac, pr.thuc_te_fac AS thucTeFac, "
        + "pr.tien_do_chung AS tienDoChung, pr.general_issue AS generalIssue, pr.solution, "
        + "pp.priority_name AS priorityName, ps.status_name AS statusName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS amName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS pmName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_manager_id) AS pmManagerName, "
        + "pr.ket_qua_tuan_truoc AS ketQuaTuanTruoc, pr.ket_qua_tuan_nay AS ketQuaTuanNay, "
        + "pr.ke_hoach_tuan_nay AS keHoachTuanNay, pr.ke_hoach_tuan_sau AS keHoachTuanSau "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN user AS u ON p.pm_id = u.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.project_type_id = :type AND pr.week = :week AND pr.year = :year AND pr.pm_id = :userId "
        + "ORDER BY pr.id", resultSetMapping = "Mapping.ExportTrienKhaiDTO")

/* ===== Set mapping: ExportTrienKhaiDTO ===== */
@SqlResultSetMapping(name = "Mapping.ExportTrienKhaiDTO", classes = @ConstructorResult(targetClass = ExportTrienKhaiDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "maHopDong", type = String.class),
        @ColumnResult(name = "maKeToan", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "tongGiaTriThucTe", type = String.class),
        @ColumnResult(name = "soTienDac", type = String.class),
        @ColumnResult(name = "hopDongDac", type = String.class),
        @ColumnResult(name = "mucTieuDac", type = String.class),
        @ColumnResult(name = "thucTeDac", type = String.class),
        @ColumnResult(name = "soTienPac", type = String.class),
        @ColumnResult(name = "hopDongPac", type = String.class),
        @ColumnResult(name = "mucTieuPac", type = String.class),
        @ColumnResult(name = "thucTePac", type = String.class),
        @ColumnResult(name = "soTienFac", type = String.class),
        @ColumnResult(name = "hopDongFac", type = String.class),
        @ColumnResult(name = "mucTieuFac", type = String.class),
        @ColumnResult(name = "thucTeFac", type = String.class),
        @ColumnResult(name = "tienDoChung", type = String.class),
        @ColumnResult(name = "generalIssue", type = String.class),
        @ColumnResult(name = "solution", type = String.class),
        @ColumnResult(name = "priorityName", type = String.class),
        @ColumnResult(name = "statusName", type = String.class),
        @ColumnResult(name = "amName", type = String.class),
        @ColumnResult(name = "pmName", type = String.class),
        @ColumnResult(name = "pmManagerName", type = String.class),
        @ColumnResult(name = "ketQuaTuanTruoc", type = String.class),
        @ColumnResult(name = "ketQuaTuanNay", type = String.class),
        @ColumnResult(name = "keHoachTuanNay", type = String.class),
        @ColumnResult(name = "keHoachTuanSau", type = String.class)
}))

/* ===== ProjectReportRepository.findAllDashboardProjectStep1() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllDashboardProjectStep1", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS picName, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.enabled = :enabled AND pr.project_type_id = :project_type_id AND pr.week = :week AND pr.year = :year "
        + "ORDER BY pr.job_name", resultSetMapping = "Mapping.ProjectDashboardDTO")

/* ===== ProjectReportRepository.findAllDashboardProjectStep1Limit() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllDashboardProjectStep1Limit", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS picName, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN user AS u ON pr.am_id = u.id "
        + "WHERE pr.enabled = :enabled AND pr.project_type_id = :project_type_id "
        + "AND pr.week = :week AND pr.year = :year AND u.username = :username "
        + "ORDER BY pr.job_name", resultSetMapping = "Mapping.ProjectDashboardDTO")

/* ===== ProjectReportRepository.findAllDashboardProjectStep2() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllDashboardProjectStep2", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS picName, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.enabled = :enabled AND pr.project_type_id = :project_type_id AND pr.week = :week AND pr.year = :year "
        + "ORDER BY pr.job_name", resultSetMapping = "Mapping.ProjectDashboardDTO")

/* ===== ProjectReportRepository.findAllDashboardProjectStep2Limit() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllDashboardProjectStep2Limit", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS picName, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN user AS u ON pr.pm_id = u.id "
        + "WHERE pr.enabled = :enabled AND pr.project_type_id = :project_type_id "
        + "AND pr.week = :week AND pr.year = :year AND u.username = :username "
        + "ORDER BY pr.job_name", resultSetMapping = "Mapping.ProjectDashboardDTO")

/* ===== ProjectReportRepository.findDetailTabPhanLoai() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findDetailTabPhanLoai", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, p.id AS projectId, p.project_name AS projectName, "
        + "c.customer_name AS customerName, pt.id AS typeId, pt.display AS typeDisplay, "
        + "pp.id AS priorityId, pp.display AS priorityDisplay, ps.id AS statusId, "
        + "ps.color AS statusColor, ps.display AS statusDisplay, pr.week, pr.year, "
        + "pr.ma_hop_dong AS maHopDong, pr.ma_ke_toan AS maKeToan, pr.currency_unit_id AS currencyUnitId, "
        + "cu.currency_unit AS currencyUnitDisplay "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_type AS pt ON pr.project_type_id = pt.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN currency_unit AS cu ON pr.currency_unit_id = cu.id "
        + "WHERE pr.id = :id AND pr.enabled = :enabled", resultSetMapping = "Mapping.ReportDetailTabPhanLoaiDTO")

/* ===== ProjectReportRepository.findDetailTabDuThau() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findDetailTabDuThau", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, pr.description, pr.phan_tich_swoot AS phanTichSwoot, "
        + "c.id AS customerId, c.avatar AS customerAvatar, c.customer_name AS customerName, "
        + "pr.hinh_thuc_dau_tu AS hinhThucDauTu, pr.pham_vi_cung_cap AS phamViCungCap, "
        + "pr.tong_muc_dau_tu_du_kien AS tongMucDauTuDuKien, pr.muc_do_kha_thi AS mucDoKhaThi "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "WHERE pr.id = :id AND pr.enabled = :enabled", resultSetMapping = "Mapping.ReportDetailTabDuThauDTO")

/* ===== ProjectReportRepository.findDetailTabChiPhiThoiGian() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findDetailTabChiPhiThoiGian", query = "SELECT "
        + "pr.id, "
        + "pr.so_tien_giao_hang AS soTienGiaoHang, pr.hop_dong_giao_hang AS hopDongGiaoHang, "
        + "pr.muc_tieu_giao_hang AS mucTieuGiaoHang, pr.thuc_te_giao_hang AS thucTeGiaoHang, "
        + "pr.note_giao_hang AS noteGiaoHang, "
        + "pr.so_tien_dac AS soTienDac, pr.hop_dong_dac AS hopDongDac, pr.muc_tieu_dac AS mucTieuDac, "
        + "pr.thuc_te_dac AS thucTeDac, pr.note_dac AS noteDac, "
        + "pr.so_tien_pac AS soTienPac, pr.hop_dong_pac AS hopDongPac, pr.muc_tieu_pac AS mucTieuPac, "
        + "pr.thuc_te_pac AS thucTePac, pr.note_pac AS notePac, "
        + "pr.so_tien_fac AS soTienFac, pr.hop_dong_fac AS hopDongFac, pr.muc_tieu_fac AS mucTieuFac, "
        + "pr.thuc_te_fac AS thucTeFac, pr.note_fac AS noteFac, "
        + "pr.tong_gia_tri_thuc_te AS tongGiaTriThucTe, pr.note_tong_gia_tri AS noteTongGiaTri, "
        + "pr.so_tien_tam_ung AS soTienTamUng, pr.ke_hoach_tam_ung AS keHoachTamUng, "
        + "pr.note_tam_ung AS noteTamUng "
        + "FROM project_report AS pr "
        + "WHERE pr.id = :id AND pr.enabled = :enabled", resultSetMapping = "Mapping.ReportDetailTabCptgDTO")

/* ===== ProjectReportRepository.findDetailMoreDAC() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findDetailMoreDAC", query = "SELECT "
        + "pr.id, "
        + "pr.so_tien_dac AS soTien, pr.hop_dong_dac AS hopDong, pr.muc_tieu_dac AS mucTieu, "
        + "pr.thuc_te_dac AS thucTe, pr.note_dac AS note, "
        + "prs.so_tien_dac_2 AS soTien2, prs.hop_dong_dac_2 AS hopDong2, prs.muc_tieu_dac_2 AS mucTieu2, "
        + "prs.thuc_te_dac_2 AS thucTe2, prs.note_dac_2 AS note2, "
        + "prs.so_tien_dac_3 AS soTien3, prs.hop_dong_dac_3 AS hopDong3, prs.muc_tieu_dac_3 AS mucTieu3, "
        + "prs.thuc_te_dac_3 AS thucTe3, prs.note_dac_3 AS note3, "
        + "prs.so_tien_dac_4 AS soTien4, prs.hop_dong_dac_4 AS hopDong4, prs.muc_tieu_dac_4 AS mucTieu4, "
        + "prs.thuc_te_dac_4 AS thucTe4, prs.note_dac_4 AS note4, "
        + "prs.so_tien_dac_5 AS soTien5, prs.hop_dong_dac_5 AS hopDong5, prs.muc_tieu_dac_5 AS mucTieu5, "
        + "prs.thuc_te_dac_5 AS thucTe5, prs.note_dac_5 AS note5 "
        + "FROM project_report AS pr "
        + "INNER JOIN project_report_subdata AS prs on pr.id = prs.project_report_id "
        + "WHERE pr.id = :id", resultSetMapping = "Mapping.SupportCptgDTO")

/* ===== ProjectReportRepository.findDetailMorePAC() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findDetailMorePAC", query = "SELECT "
        + "pr.id, "
        + "pr.so_tien_pac AS soTien, pr.hop_dong_pac AS hopDong, pr.muc_tieu_pac AS mucTieu, "
        + "pr.thuc_te_pac AS thucTe, pr.note_pac AS note, "
        + "prs.so_tien_pac_2 AS soTien2, prs.hop_dong_pac_2 AS hopDong2, prs.muc_tieu_pac_2 AS mucTieu2, "
        + "prs.thuc_te_pac_2 AS thucTe2, prs.note_pac_2 AS note2, "
        + "prs.so_tien_pac_3 AS soTien3, prs.hop_dong_pac_3 AS hopDong3, prs.muc_tieu_pac_3 AS mucTieu3, "
        + "prs.thuc_te_pac_3 AS thucTe3, prs.note_pac_3 AS note3, "
        + "prs.so_tien_pac_4 AS soTien4, prs.hop_dong_pac_4 AS hopDong4, prs.muc_tieu_pac_4 AS mucTieu4, "
        + "prs.thuc_te_pac_4 AS thucTe4, prs.note_pac_4 AS note4, "
        + "prs.so_tien_pac_5 AS soTien5, prs.hop_dong_pac_5 AS hopDong5, prs.muc_tieu_pac_5 AS mucTieu5, "
        + "prs.thuc_te_pac_5 AS thucTe5, prs.note_pac_5 AS note5 "
        + "FROM project_report AS pr "
        + "INNER JOIN project_report_subdata AS prs on pr.id = prs.project_report_id "
        + "WHERE pr.id = :id", resultSetMapping = "Mapping.SupportCptgDTO")

/* ===== ProjectReportRepository.findDetailMoreFAC() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findDetailMoreFAC", query = "SELECT "
        + "pr.id, "
        + "pr.so_tien_fac AS soTien, pr.hop_dong_fac AS hopDong, pr.muc_tieu_fac AS mucTieu, "
        + "pr.thuc_te_fac AS thucTe, pr.note_fac AS note, "
        + "prs.so_tien_fac_2 AS soTien2, prs.hop_dong_fac_2 AS hopDong2, prs.muc_tieu_fac_2 AS mucTieu2, "
        + "prs.thuc_te_fac_2 AS thucTe2, prs.note_fac_2 AS note2, "
        + "prs.so_tien_fac_3 AS soTien3, prs.hop_dong_fac_3 AS hopDong3, prs.muc_tieu_fac_3 AS mucTieu3, "
        + "prs.thuc_te_fac_3 AS thucTe3, prs.note_fac_3 AS note3, "
        + "prs.so_tien_fac_4 AS soTien4, prs.hop_dong_fac_4 AS hopDong4, prs.muc_tieu_fac_4 AS mucTieu4, "
        + "prs.thuc_te_fac_4 AS thucTe4, prs.note_fac_4 AS note4, "
        + "prs.so_tien_fac_5 AS soTien5, prs.hop_dong_fac_5 AS hopDong5, prs.muc_tieu_fac_5 AS mucTieu5, "
        + "prs.thuc_te_fac_5 AS thucTe5, prs.note_fac_5 AS note5 "
        + "FROM project_report AS pr "
        + "INNER JOIN project_report_subdata AS prs on pr.id = prs.project_report_id "
        + "WHERE pr.id = :id", resultSetMapping = "Mapping.SupportCptgDTO")

/* ===== ProjectReportRepository.findDetailTabQuaTrinh() ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findDetailTabQuaTrinh", query = "SELECT "
        + "pr.id, pr.general_issue AS generalIssue, pr.solution, pr.ke_hoach_tuan_nay AS keHoachTuanNay, "
        + "pr.ke_hoach_tuan_sau AS keHoachTuanSau, pr.ket_qua_tuan_truoc AS ketQuaTuanTruoc, "
        + "pr.ket_qua_tuan_nay AS ketQuaTuanNay "
        + "FROM project_report AS pr "
        + "WHERE pr.id = :id AND pr.enabled = :enabled", resultSetMapping = "Mapping.ReportDetailTabQuaTrinhDTO")

/* ===== ProjectReportRepository.findAllReportType12 ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllReportType12", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS picName, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn, "
        + "pr.muc_do_kha_thi AS mucDoKhaThi, pr.tong_muc_dau_tu_du_kien AS tongMucDauTuDuKien, pr.week, pr.year "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.project_type_id = :project_type_id "
        + "ORDER BY pr.year, pr.week DESC "
        + "LIMIT :pageSize OFFSET :startPosition", resultSetMapping = "Mapping.ListReport12DTO")

/* ===== ProjectReportRepository.findAllReportType12WeekYear ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllReportType12WeekYear", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS picName, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn, "
        + "pr.muc_do_kha_thi AS mucDoKhaThi, pr.tong_muc_dau_tu_du_kien AS tongMucDauTuDuKien, pr.week, pr.year "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.year = :year AND pr.week = :week AND pr.project_type_id = :project_type_id "
        + "ORDER BY pr.job_name "
        + "LIMIT :pageSize OFFSET :startPosition", resultSetMapping = "Mapping.ListReport12DTO")

/* ===== ProjectReportRepository.findAllReportType12CurrentDate ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllReportType12CurrentDate", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS picName, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn, "
        + "pr.muc_do_kha_thi AS mucDoKhaThi, pr.tong_muc_dau_tu_du_kien AS tongMucDauTuDuKien, pr.week, pr.year "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.project_type_id = :project_type_id AND DATE(pr.created_date) = CURDATE() "
        + "ORDER BY pr.year, pr.week DESC "
        + "LIMIT :pageSize OFFSET :startPosition", resultSetMapping = "Mapping.ListReport12DTO")

/* ===== ProjectReportRepository.findAllReportType12Limit ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findAllReportType12Limit", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS picName, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn, "
        + "pr.muc_do_kha_thi AS mucDoKhaThi, pr.tong_muc_dau_tu_du_kien AS tongMucDauTuDuKien, pr.week, pr.year "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN user AS u ON pr.am_id = u.id "
        + "WHERE pr.year = :year AND pr.week = :week AND pr.project_type_id = :project_type_id AND u.username = :username "
        + "ORDER BY pr.job_name"
        + "LIMIT :pageSize OFFSET :startPosition", resultSetMapping = "Mapping.ListReport12DTO")

/* ===== ProjectReportRepository.findListReportType3WeekYear ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findListReportType3WeekYear", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, pr.tong_gia_tri_thuc_te AS tongGiaTriThucTe, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS picName, pr.week, pr.year, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.year = :year AND pr.week = :week AND pr.project_type_id = :project_type_id "
        + "ORDER BY pr.job_name "
        + "LIMIT :pageSize OFFSET :startPosition", resultSetMapping = "Mapping.ListReport3DTO")

/* ===== ProjectReportRepository.findListReportType3CurrentDate ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findListReportType3CurrentDate", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, pr.tong_gia_tri_thuc_te AS tongGiaTriThucTe, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS picName, pr.week, pr.year, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.project_type_id = :project_type_id AND DATE(pr.created_date) = CURDATE() "
        + "ORDER BY pr.year, pr.week DESC "
        + "LIMIT :pageSize OFFSET :startPosition", resultSetMapping = "Mapping.ListReport3DTO")

/* ===== ProjectReportRepository.findListReportType3All ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findListReportType3All", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, pr.tong_gia_tri_thuc_te AS tongGiaTriThucTe, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS picName, pr.week, pr.year, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "WHERE pr.project_type_id = :project_type_id "
        + "ORDER BY pr.year, pr.week DESC "
        + "LIMIT :pageSize OFFSET :startPosition", resultSetMapping = "Mapping.ListReport3DTO")

/* ===== ProjectReportRepository.findListReportType3WeekYearByUser ===== */
@NamedNativeQuery(name = "ProjectReportEntity.findListReportType3WeekYearByUser", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, pr.tong_gia_tri_thuc_te AS tongGiaTriThucTe, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS picName, pr.week, pr.year, "
        + "ps.display AS statusDisplay, ps.color AS statusColor, pr.general_issue AS tinhTrangDuAn "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN user AS u ON pr.pm_id = u.id "
        + "WHERE pr.year = :year AND pr.week = :week AND pr.project_type_id = :project_type_id AND u.username = :username "
        + "ORDER BY pr.job_name "
        + "LIMIT :pageSize OFFSET :startPosition", resultSetMapping = "Mapping.ListReport3DTO")

/* ===== ShowDashboardDTO.modalShowDashboard ===== */
@NamedNativeQuery(name = "ProjectReportEntity.modalShowDashboard", query = "SELECT "
        + "pr.id, pr.job_name AS jobName, c.customer_name AS customerName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.am_id) AS amName, "
        + "(SELECT u.fullname FROM user AS u WHERE u.id = pr.pm_id) AS pmName, "
        + "cu.currency_unit AS currencyUnit, pr.general_issue AS generalIssue, "
        + "pr.ke_hoach_tuan_nay AS keHoachTuanNay, pr.ke_hoach_tuan_sau AS keHoachTuanSau, "
        + "pr.ke_hoach_tuan_nay AS ketQuaTuanNay, pr.solution, "
        + "pr.so_tien_giao_hang AS soTienGiaoHang, pr.hop_dong_giao_hang AS hopDongGiaoHang, "
        + "pr.muc_tieu_giao_hang AS mucTieuGiaoHang, pr.thuc_te_giao_hang AS thucTeGiaoHang, "
        + "pr.note_giao_hang AS noteGiaoHang, "
        + "pr.so_tien_dac AS soTienDac, pr.hop_dong_dac AS hopDongDac, pr.muc_tieu_dac AS mucTieuDac, "
        + "pr.thuc_te_dac AS thucTeDac, pr.note_dac AS noteDac, "
        + "pr.so_tien_pac AS soTienPac, pr.hop_dong_pac AS hopDongPac, pr.muc_tieu_pac AS mucTieuPac, "
        + "pr.thuc_te_pac AS thucTePac, pr.note_pac AS notePac, "
        + "pr.so_tien_fac AS soTienFac, pr.hop_dong_fac AS hopDongFac, pr.muc_tieu_fac AS mucTieuFac, "
        + "pr.thuc_te_fac AS thucTeFac, pr.note_fac AS noteFac, "
        + "pr.tong_gia_tri_thuc_te AS tongGiaTriThucTe, pr.note_tong_gia_tri AS noteTongGiaTri, "
        + "pr.so_tien_tam_ung AS soTienTamUng, pr.ke_hoach_tam_ung AS keHoachTamUng, "
        + "pr.note_tam_ung AS noteTamUng "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN currency_unit AS cu ON pr.currency_unit_id = cu.id "
        + "WHERE pr.enabled = :enabled AND pr.week = :week AND pr.year = :year "
        + "AND pr.project_status_id = :project_status_id AND pr.project_type_id = :project_type_id", resultSetMapping = "Mapping.ShowDashboardDTO")

/* ===== Set mapping: ShowDashboardDTO ===== */
@SqlResultSetMapping(name = "Mapping.ShowDashboardDTO", classes = @ConstructorResult(targetClass = ShowDashboardDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "amName", type = String.class),
        @ColumnResult(name = "pmName", type = String.class),
        @ColumnResult(name = "currencyUnit", type = String.class),
        @ColumnResult(name = "generalIssue", type = String.class),
        @ColumnResult(name = "keHoachTuanNay", type = String.class),
        @ColumnResult(name = "keHoachTuanSau", type = String.class),
        @ColumnResult(name = "ketQuaTuanNay", type = String.class),
        @ColumnResult(name = "solution", type = String.class),

        @ColumnResult(name = "soTienGiaoHang", type = String.class),
        @ColumnResult(name = "hopDongGiaoHang", type = String.class),
        @ColumnResult(name = "mucTieuGiaoHang", type = String.class),
        @ColumnResult(name = "thucTeGiaoHang", type = String.class),
        @ColumnResult(name = "noteGiaoHang", type = String.class),

        @ColumnResult(name = "soTienDac", type = String.class),
        @ColumnResult(name = "hopDongDac", type = String.class),
        @ColumnResult(name = "mucTieuDac", type = String.class),
        @ColumnResult(name = "thucTeDac", type = String.class),
        @ColumnResult(name = "noteDac", type = String.class),

        @ColumnResult(name = "soTienPac", type = String.class),
        @ColumnResult(name = "hopDongPac", type = String.class),
        @ColumnResult(name = "mucTieuPac", type = String.class),
        @ColumnResult(name = "thucTePac", type = String.class),
        @ColumnResult(name = "notePac", type = String.class),

        @ColumnResult(name = "soTienFac", type = String.class),
        @ColumnResult(name = "hopDongFac", type = String.class),
        @ColumnResult(name = "mucTieuFac", type = String.class),
        @ColumnResult(name = "thucTeFac", type = String.class),
        @ColumnResult(name = "noteFac", type = String.class),

        @ColumnResult(name = "tongGiaTriThucTe", type = String.class),
        @ColumnResult(name = "noteTongGiaTri", type = String.class),
        @ColumnResult(name = "soTienTamUng", type = String.class),
        @ColumnResult(name = "keHoachTamUng", type = String.class),
        @ColumnResult(name = "noteTamUng", type = String.class)
}))

/* ===== Set mapping: ListReport12DTO ===== */
@SqlResultSetMapping(name = "Mapping.ListReport12DTO", classes = @ConstructorResult(targetClass = ListReport12DTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "picName", type = String.class),
        @ColumnResult(name = "statusDisplay", type = String.class),
        @ColumnResult(name = "statusColor", type = String.class),
        @ColumnResult(name = "tinhTrangDuAn", type = String.class),
        @ColumnResult(name = "mucDoKhaThi", type = Integer.class),
        @ColumnResult(name = "tongMucDauTuDuKien", type = String.class),
        @ColumnResult(name = "week", type = Integer.class),
        @ColumnResult(name = "year", type = Integer.class)

}))

/* ===== Set mapping: ListReport3DTO ===== */
@SqlResultSetMapping(name = "Mapping.ListReport3DTO", classes = @ConstructorResult(targetClass = ListReport3DTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "picName", type = String.class),
        @ColumnResult(name = "statusDisplay", type = String.class),
        @ColumnResult(name = "statusColor", type = String.class),
        @ColumnResult(name = "tinhTrangDuAn", type = String.class),
        @ColumnResult(name = "tongGiaTriThucTe", type = String.class),
        @ColumnResult(name = "week", type = Integer.class),
        @ColumnResult(name = "year", type = Integer.class)

}))

/* ===== Set mapping: ProjectDashboardDTO ===== */
@SqlResultSetMapping(name = "Mapping.ProjectDashboardDTO", classes = @ConstructorResult(targetClass = ProjectDashboardDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "picName", type = String.class),
        @ColumnResult(name = "statusDisplay", type = String.class),
        @ColumnResult(name = "statusColor", type = String.class),
        @ColumnResult(name = "tinhTrangDuAn", type = String.class) }))

/* ===== Set mapping: ReportDetailTabPhanLoaiDTO ===== */
@SqlResultSetMapping(name = "Mapping.ReportDetailTabPhanLoaiDTO", classes = @ConstructorResult(targetClass = ReportDetailTabPhanLoaiDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "projectId", type = Long.class),
        @ColumnResult(name = "projectName", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "typeId", type = Long.class),
        @ColumnResult(name = "typeDisplay", type = String.class),
        @ColumnResult(name = "priorityId", type = Long.class),
        @ColumnResult(name = "priorityDisplay", type = String.class),
        @ColumnResult(name = "statusId", type = Long.class),
        @ColumnResult(name = "statusColor", type = String.class),
        @ColumnResult(name = "statusDisplay", type = String.class),
        @ColumnResult(name = "week", type = Integer.class),
        @ColumnResult(name = "year", type = Integer.class),
        @ColumnResult(name = "maHopDong", type = String.class),
        @ColumnResult(name = "maKeToan", type = String.class),
        @ColumnResult(name = "currencyUnitId", type = Long.class),
        @ColumnResult(name = "currencyUnitDisplay", type = String.class) }))

/* ===== Set mapping: ReportDetailTabDuThauDTO ===== */
@SqlResultSetMapping(name = "Mapping.ReportDetailTabDuThauDTO", classes = @ConstructorResult(targetClass = ReportDetailTabDuThauDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "jobName", type = String.class),
        @ColumnResult(name = "description", type = String.class),
        @ColumnResult(name = "phanTichSwoot", type = String.class),
        @ColumnResult(name = "customerId", type = Long.class),
        @ColumnResult(name = "customerAvatar", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "hinhThucDauTu", type = String.class),
        @ColumnResult(name = "phamViCungCap", type = String.class),
        @ColumnResult(name = "tongMucDauTuDuKien", type = String.class),
        @ColumnResult(name = "mucDoKhaThi", type = Integer.class) }))

/* ===== Set mapping: ReportDetailTabCptgDTO ===== */
@SqlResultSetMapping(name = "Mapping.ReportDetailTabCptgDTO", classes = @ConstructorResult(targetClass = ReportDetailTabCptgDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "soTienGiaoHang", type = String.class),
        @ColumnResult(name = "hopDongGiaoHang", type = String.class),
        @ColumnResult(name = "mucTieuGiaoHang", type = String.class),
        @ColumnResult(name = "thucTeGiaoHang", type = String.class),
        @ColumnResult(name = "noteGiaoHang", type = String.class),

        @ColumnResult(name = "soTienDac", type = String.class),
        @ColumnResult(name = "hopDongDac", type = String.class),
        @ColumnResult(name = "mucTieuDac", type = String.class),
        @ColumnResult(name = "thucTeDac", type = String.class),
        @ColumnResult(name = "noteDac", type = String.class),

        @ColumnResult(name = "soTienPac", type = String.class),
        @ColumnResult(name = "hopDongPac", type = String.class),
        @ColumnResult(name = "mucTieuPac", type = String.class),
        @ColumnResult(name = "thucTePac", type = String.class),
        @ColumnResult(name = "notePac", type = String.class),

        @ColumnResult(name = "soTienFac", type = String.class),
        @ColumnResult(name = "hopDongFac", type = String.class),
        @ColumnResult(name = "mucTieuFac", type = String.class),
        @ColumnResult(name = "thucTeFac", type = String.class),
        @ColumnResult(name = "noteFac", type = String.class),

        @ColumnResult(name = "tongGiaTriThucTe", type = String.class),
        @ColumnResult(name = "noteTongGiaTri", type = String.class),
        @ColumnResult(name = "soTienTamUng", type = String.class),
        @ColumnResult(name = "keHoachTamUng", type = String.class),
        @ColumnResult(name = "noteTamUng", type = String.class), }))

/* ===== Set mapping: SupportCptgDTO ===== */
@SqlResultSetMapping(name = "Mapping.SupportCptgDTO", classes = @ConstructorResult(targetClass = SupportCptgDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),

        @ColumnResult(name = "soTien", type = String.class),
        @ColumnResult(name = "hopDong", type = String.class),
        @ColumnResult(name = "mucTieu", type = String.class),
        @ColumnResult(name = "thucTe", type = String.class),
        @ColumnResult(name = "note", type = String.class),

        @ColumnResult(name = "soTien2", type = String.class),
        @ColumnResult(name = "hopDong2", type = String.class),
        @ColumnResult(name = "mucTieu2", type = String.class),
        @ColumnResult(name = "thucTe2", type = String.class),
        @ColumnResult(name = "note2", type = String.class),

        @ColumnResult(name = "soTien3", type = String.class),
        @ColumnResult(name = "hopDong3", type = String.class),
        @ColumnResult(name = "mucTieu3", type = String.class),
        @ColumnResult(name = "thucTe3", type = String.class),
        @ColumnResult(name = "note3", type = String.class),

        @ColumnResult(name = "soTien4", type = String.class),
        @ColumnResult(name = "hopDong4", type = String.class),
        @ColumnResult(name = "mucTieu4", type = String.class),
        @ColumnResult(name = "thucTe4", type = String.class),
        @ColumnResult(name = "note4", type = String.class),

        @ColumnResult(name = "soTien5", type = String.class),
        @ColumnResult(name = "hopDong5", type = String.class),
        @ColumnResult(name = "mucTieu5", type = String.class),
        @ColumnResult(name = "thucTe5", type = String.class),
        @ColumnResult(name = "note5", type = String.class) }))

/* ===== Set mapping: ReportDetailTabQuaTrinhDTO ===== */
@SqlResultSetMapping(name = "Mapping.ReportDetailTabQuaTrinhDTO", classes = @ConstructorResult(targetClass = ReportDetailTabQuaTrinhDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "generalIssue", type = String.class),
        @ColumnResult(name = "solution", type = String.class),
        @ColumnResult(name = "keHoachTuanNay", type = String.class),
        @ColumnResult(name = "keHoachTuanSau", type = String.class),
        @ColumnResult(name = "ketQuaTuanTruoc", type = String.class),
        @ColumnResult(name = "ketQuaTuanNay", type = String.class) }))

@Entity
@Table(name = "project_report")
public class ProjectReportEntity extends BaseEntity {
    // @Column(name = "id") // 1
    // private int id;
    // @Column(name = "uid") // 2
    // private String uid;
    // @Column(name = "project_id", nullable = false) // 3 - Foreign Key
    // private Long projectId;
    // @Column(name = "project_type_id", nullable = false) // 4 - Foreign Key
    // private Long projectTypeId;
    // @Column(name = "project_priority_id", nullable = false) // 5 - Foreign Key
    // private Long projectPriorityId;
    // @Column(name = "project_status_id", nullable = false) // 6 - Foreign Key
    // private Long projectStatusId;
    // @Column(name = "currency_unit_id", nullable = false) // 7
    // private Long currencyUnitId;

    @Column(name = "am_manager_id") // 8
    private Integer amManagerId;

    @Column(name = "am_id", nullable = false) // 9
    private Integer amId;

    @Column(name = "pm_manager_id") // 10
    private Integer pmManagerId;

    @Column(name = "pm_id") // 11
    private Integer pmId;

    @Column(name = "week", nullable = false) // 12
    private Integer week;

    @Column(name = "year", nullable = false) // 13
    private Integer year;

    @Column(name = "ma_hop_dong") // 14
    private String maHopDong;

    @Column(name = "ma_ke_toan") // 15
    private String maKeToan;

    @Column(name = "job_name", nullable = false, columnDefinition = "TEXT") // 16
    private String jobName;

    @Column(name = "description", columnDefinition = "TEXT") // 17
    private String description;

    @Column(name = "tong_muc_dau_tu_du_kien") // 18
    private String tongMucDauTuDuKien;

    @Column(name = "hinh_thuc_dau_tu") // 19
    private String hinhThucDauTu;

    @Column(name = "muc_do_kha_thi") // 20
    private Integer mucDoKhaThi;

    @Column(name = "phan_tich_SWOOT", columnDefinition = "TEXT") // 21
    private String phanTichSwoot;

    @Column(name = "pham_vi_cung_cap") // 22
    private String phamViCungCap;

    @Column(name = "tong_gia_tri_thuc_te") // 23
    private String tongGiaTriThucTe;
    @Column(name = "note_tong_gia_tri") // 24
    private String noteTongGiaTri;

    @Column(name = "so_tien_tam_ung") // 25
    private String soTienTamUng;
    @Column(name = "ke_hoach_tam_ung") // 26
    private String keHoachTamUng;
    @Column(name = "note_tam_ung") // 27
    private String noteTamUng;

    @Column(name = "so_tien_giao_hang") // 28
    private String soTienGiaoHang;
    @Column(name = "hop_dong_giao_hang") // 29
    private String hopDongGiaoHang;
    @Column(name = "muc_tieu_giao_hang") // 30
    private String mucTieuGiaoHang;
    @Column(name = "thuc_te_giao_hang") // 31
    private String thucTeGiaoHang;
    @Column(name = "note_giao_hang") // 32
    private String noteGiaoHang;

    @Column(name = "so_tien_DAC") // 33
    private String soTienDac;
    @Column(name = "hop_dong_DAC") // 34
    private String hopDongDac;
    @Column(name = "muc_tieu_DAC") // 35
    private String mucTieuDac;
    @Column(name = "thuc_te_DAC") // 36
    private String thucTeDac;
    @Column(name = "note_DAC") // 37
    private String noteDac;

    @Column(name = "so_tien_DAC_them") // 38
    private String soTienDacThem;
    @Column(name = "hop_dong_DAC_them") // 39
    private String hopDongDacThem;
    @Column(name = "muc_tieu_DAC_them") // 40
    private String mucTieuDacThem;
    @Column(name = "thuc_te_DAC_them") // 41
    private String thucTeDacThem;
    @Column(name = "note_DAC_them") // 42
    private String noteDacThem;

    @Column(name = "so_tien_PAC") // 43
    private String soTienPac;
    @Column(name = "hop_dong_PAC") // 44
    private String hopDongPac;
    @Column(name = "muc_tieu_PAC") // 45
    private String mucTieuPac;
    @Column(name = "thuc_te_PAC") // 46
    private String thucTePac;
    @Column(name = "note_PAC") // 47
    private String notePac;

    @Column(name = "so_tien_PAC_them") // 48
    private String soTienPacThem;
    @Column(name = "hop_dong_PAC_them") // 49
    private String hopDongPacThem;
    @Column(name = "muc_tieu_PAC_them") // 50
    private String mucTieuPacThem;
    @Column(name = "thuc_te_PAC_them") // 51
    private String thucTePacThem;
    @Column(name = "note_PAC_them") // 52
    private String notePacThem;

    @Column(name = "so_tien_FAC") // 53
    private String soTienFac;
    @Column(name = "hop_dong_FAC") // 54
    private String hopDongFac;
    @Column(name = "muc_tieu_FAC") // 55
    private String mucTieuFac;
    @Column(name = "thuc_te_FAC") // 56
    private String thucTeFac;
    @Column(name = "note_FAC") // 57
    private String noteFac;

    @Column(name = "so_tien_FAC_them") // 58
    private String soTienFacThem;
    @Column(name = "hop_dong_FAC_them") // 59
    private String hopDongFacThem;
    @Column(name = "muc_tieu_FAC_them") // 60
    private String mucTieuFacThem;
    @Column(name = "thuc_te_FAC_them") // 61
    private String thucTeFacThem;
    @Column(name = "note_FAC_them") // 62
    private String noteFacThem;

    @Column(name = "general_issue", columnDefinition = "TEXT") // 63
    private String generalIssue;
    @Column(name = "solution", columnDefinition = "TEXT") // 64
    private String solution;

    @Column(name = "ke_hoach_tuan_nay", columnDefinition = "TEXT") // 65
    private String keHoachTuanNay;
    @Column(name = "ke_hoach_tuan_sau", columnDefinition = "TEXT") // 66
    private String keHoachTuanSau;
    @Column(name = "ket_qua_tuan_truoc", columnDefinition = "TEXT") // 67
    private String ketQuaTuanTruoc;
    @Column(name = "ket_qua_tuan_nay", columnDefinition = "TEXT") // 68
    private String ketQuaTuanNay;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT(4)") // 69
    private int enabled;
    @Column(name = "tien_do_chung", columnDefinition = "TEXT") // 70
    private String tienDoChung;

    // @Column(name = "created_by") // 71
    // private String created_by;
    // @Column(name = "created_at") // 72
    // private Date created_at;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'currency_unit' (currency_unit_id)"
     * )
     */
    @JoinColumn(name = "currency_unit_id")
    private CurrencyUnitEntity currencyUnit; // 1 'project_report' sử dụng 1 'currency_unit' => hứng 1 bản ghi

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ ProjectReportEntity trong FileUploadEntity (projectReport)"
     * )
     */
    @OneToMany(mappedBy = "projectReport")
    /*
     * 1 'project_report' nằm trong nhiều 'file_upload'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<FileUploadEntity> fileUploads = new ArrayList<>();

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'project' (project_id)"
     * )
     */
    @JoinColumn(name = "project_id")
    private ProjectEntity project; // 1 'project_report' sử dụng 1 'project' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'project_type' (project_type_id)"
     * )
     */
    @JoinColumn(name = "project_type_id")
    private ProjectTypeEntity projectType; // 1 'project_report' sử dụng 1 'project_type' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'project_priority' (project_priority_id)"
     * )
     */
    @JoinColumn(name = "project_priority_id")
    private ProjectPriorityEntity projectPriority; // 1 'project_report' sử dụng 1 'project_priority' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'project_status' (project_status_id)"
     * )
     */
    @JoinColumn(name = "project_status_id")
    private ProjectStatusEntity projectStatus; // 1 'project_report' sử dụng 1 'project_status' => hứng 1 bản ghi

    public Integer getAmManagerId() {
        return this.amManagerId;
    }

    public void setAmManagerId(Integer amManagerId) {
        this.amManagerId = amManagerId;
    }

    public Integer getAmId() {
        return this.amId;
    }

    public void setAmId(Integer amId) {
        this.amId = amId;
    }

    public Integer getPmManagerId() {
        return this.pmManagerId;
    }

    public void setPmManagerId(Integer pmManagerId) {
        this.pmManagerId = pmManagerId;
    }

    public Integer getPmId() {
        return this.pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public Integer getWeek() {
        return this.week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMaHopDong() {
        return this.maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getMaKeToan() {
        return this.maKeToan;
    }

    public void setMaKeToan(String maKeToan) {
        this.maKeToan = maKeToan;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTongMucDauTuDuKien() {
        return this.tongMucDauTuDuKien;
    }

    public void setTongMucDauTuDuKien(String tongMucDauTuDuKien) {
        this.tongMucDauTuDuKien = tongMucDauTuDuKien;
    }

    public String getHinhThucDauTu() {
        return this.hinhThucDauTu;
    }

    public void setHinhThucDauTu(String hinhThucDauTu) {
        this.hinhThucDauTu = hinhThucDauTu;
    }

    public Integer getMucDoKhaThi() {
        return this.mucDoKhaThi;
    }

    public void setMucDoKhaThi(Integer mucDoKhaThi) {
        this.mucDoKhaThi = mucDoKhaThi;
    }

    public String getPhanTichSwoot() {
        return this.phanTichSwoot;
    }

    public void setPhanTichSwoot(String phanTichSwoot) {
        this.phanTichSwoot = phanTichSwoot;
    }

    public String getPhamViCungCap() {
        return this.phamViCungCap;
    }

    public void setPhamViCungCap(String phamViCungCap) {
        this.phamViCungCap = phamViCungCap;
    }

    public String getTongGiaTriThucTe() {
        return this.tongGiaTriThucTe;
    }

    public void setTongGiaTriThucTe(String tongGiaTriThucTe) {
        this.tongGiaTriThucTe = tongGiaTriThucTe;
    }

    public String getNoteTongGiaTri() {
        return this.noteTongGiaTri;
    }

    public void setNoteTongGiaTri(String noteTongGiaTri) {
        this.noteTongGiaTri = noteTongGiaTri;
    }

    public String getSoTienTamUng() {
        return this.soTienTamUng;
    }

    public void setSoTienTamUng(String soTienTamUng) {
        this.soTienTamUng = soTienTamUng;
    }

    public String getKeHoachTamUng() {
        return this.keHoachTamUng;
    }

    public void setKeHoachTamUng(String keHoachTamUng) {
        this.keHoachTamUng = keHoachTamUng;
    }

    public String getNoteTamUng() {
        return this.noteTamUng;
    }

    public void setNoteTamUng(String noteTamUng) {
        this.noteTamUng = noteTamUng;
    }

    public String getSoTienGiaoHang() {
        return this.soTienGiaoHang;
    }

    public void setSoTienGiaoHang(String soTienGiaoHang) {
        this.soTienGiaoHang = soTienGiaoHang;
    }

    public String getHopDongGiaoHang() {
        return this.hopDongGiaoHang;
    }

    public void setHopDongGiaoHang(String hopDongGiaoHang) {
        this.hopDongGiaoHang = hopDongGiaoHang;
    }

    public String getMucTieuGiaoHang() {
        return this.mucTieuGiaoHang;
    }

    public void setMucTieuGiaoHang(String mucTieuGiaoHang) {
        this.mucTieuGiaoHang = mucTieuGiaoHang;
    }

    public String getThucTeGiaoHang() {
        return this.thucTeGiaoHang;
    }

    public void setThucTeGiaoHang(String thucTeGiaoHang) {
        this.thucTeGiaoHang = thucTeGiaoHang;
    }

    public String getNoteGiaoHang() {
        return this.noteGiaoHang;
    }

    public void setNoteGiaoHang(String noteGiaoHang) {
        this.noteGiaoHang = noteGiaoHang;
    }

    public String getSoTienDac() {
        return this.soTienDac;
    }

    public void setSoTienDac(String soTienDac) {
        this.soTienDac = soTienDac;
    }

    public String getHopDongDac() {
        return this.hopDongDac;
    }

    public void setHopDongDac(String hopDongDac) {
        this.hopDongDac = hopDongDac;
    }

    public String getMucTieuDac() {
        return this.mucTieuDac;
    }

    public void setMucTieuDac(String mucTieuDac) {
        this.mucTieuDac = mucTieuDac;
    }

    public String getThucTeDac() {
        return this.thucTeDac;
    }

    public void setThucTeDac(String thucTeDac) {
        this.thucTeDac = thucTeDac;
    }

    public String getNoteDac() {
        return this.noteDac;
    }

    public void setNoteDac(String noteDac) {
        this.noteDac = noteDac;
    }

    public String getSoTienPac() {
        return this.soTienPac;
    }

    public void setSoTienPac(String soTienPac) {
        this.soTienPac = soTienPac;
    }

    public String getHopDongPac() {
        return this.hopDongPac;
    }

    public void setHopDongPac(String hopDongPac) {
        this.hopDongPac = hopDongPac;
    }

    public String getMucTieuPac() {
        return this.mucTieuPac;
    }

    public void setMucTieuPac(String mucTieuPac) {
        this.mucTieuPac = mucTieuPac;
    }

    public String getThucTePac() {
        return this.thucTePac;
    }

    public void setThucTePac(String thucTePac) {
        this.thucTePac = thucTePac;
    }

    public String getNotePac() {
        return this.notePac;
    }

    public void setNotePac(String notePac) {
        this.notePac = notePac;
    }

    public String getSoTienFac() {
        return this.soTienFac;
    }

    public void setSoTienFac(String soTienFac) {
        this.soTienFac = soTienFac;
    }

    public String getHopDongFac() {
        return this.hopDongFac;
    }

    public void setHopDongFac(String hopDongFac) {
        this.hopDongFac = hopDongFac;
    }

    public String getMucTieuFac() {
        return this.mucTieuFac;
    }

    public void setMucTieuFac(String mucTieuFac) {
        this.mucTieuFac = mucTieuFac;
    }

    public String getThucTeFac() {
        return this.thucTeFac;
    }

    public void setThucTeFac(String thucTeFac) {
        this.thucTeFac = thucTeFac;
    }

    public String getNoteFac() {
        return this.noteFac;
    }

    public void setNoteFac(String noteFac) {
        this.noteFac = noteFac;
    }

    public String getGeneralIssue() {
        return this.generalIssue;
    }

    public void setGeneralIssue(String generalIssue) {
        this.generalIssue = generalIssue;
    }

    public String getSolution() {
        return this.solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getKeHoachTuanNay() {
        return this.keHoachTuanNay;
    }

    public void setKeHoachTuanNay(String keHoachTuanNay) {
        this.keHoachTuanNay = keHoachTuanNay;
    }

    public String getKeHoachTuanSau() {
        return this.keHoachTuanSau;
    }

    public void setKeHoachTuanSau(String keHoachTuanSau) {
        this.keHoachTuanSau = keHoachTuanSau;
    }

    public String getKetQuaTuanTruoc() {
        return this.ketQuaTuanTruoc;
    }

    public void setKetQuaTuanTruoc(String ketQuaTuanTruoc) {
        this.ketQuaTuanTruoc = ketQuaTuanTruoc;
    }

    public String getKetQuaTuanNay() {
        return this.ketQuaTuanNay;
    }

    public void setKetQuaTuanNay(String ketQuaTuanNay) {
        this.ketQuaTuanNay = ketQuaTuanNay;
    }

    public int getEnabled() {
        return this.enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getTienDoChung() {
        return this.tienDoChung;
    }

    public void setTienDoChung(String tienDoChung) {
        this.tienDoChung = tienDoChung;
    }

    public CurrencyUnitEntity getCurrencyUnit() {
        return this.currencyUnit;
    }

    public void setCurrencyUnit(CurrencyUnitEntity currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public List<FileUploadEntity> getFileUploads() {
        return this.fileUploads;
    }

    public void setFileUploads(List<FileUploadEntity> fileUploads) {
        this.fileUploads = fileUploads;
    }

    public ProjectEntity getProject() {
        return this.project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public ProjectTypeEntity getProjectType() {
        return this.projectType;
    }

    public void setProjectType(ProjectTypeEntity projectType) {
        this.projectType = projectType;
    }

    public ProjectPriorityEntity getProjectPriority() {
        return this.projectPriority;
    }

    public void setProjectPriority(ProjectPriorityEntity projectPriority) {
        this.projectPriority = projectPriority;
    }

    public ProjectStatusEntity getProjectStatus() {
        return this.projectStatus;
    }

    public void setProjectStatus(ProjectStatusEntity projectStatus) {
        this.projectStatus = projectStatus;
    }

}
