package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.Dashboard.ProjectDashboardDTO;
import vn.ansv.management.dto.Detail.ReportDetailTabPhanLoaiDTO;
import vn.ansv.management.entity.ProjectReportEntity;

public interface ProjectReportRepository extends JpaRepository<ProjectReportEntity, Long> {
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
     * View: Detail
     * -------------------------------------------
     */
    @Query(nativeQuery = true)
    ReportDetailTabPhanLoaiDTO findDetailTabPhanLoai(@Param("id") Long id, @Param("enabled") int enabled);
}
