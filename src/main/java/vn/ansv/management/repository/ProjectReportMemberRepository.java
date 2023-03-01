package vn.ansv.management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.Detail.ReportDetailTabThanhVienDTO;
import vn.ansv.management.entity.ProjectReportMemberEntity;

public interface ProjectReportMemberRepository extends JpaRepository<ProjectReportMemberEntity, Long> {
    /*
     * -------------------------------------------
     * Chi tiết báo cáo dự án theo ID và Enabled
     * View: Detail (tab Thành viên)
     * -------------------------------------------
     */
    @Query(nativeQuery = true)
    List<ReportDetailTabThanhVienDTO> findAllMemberByReport(@Param("projectId") Long projectId,
            @Param("id") Long id);

    /*
     * -------------------------------
     * Thêm mới thành viên vào dự án
     * -------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO project_report_member (job_assinged, project_id, first_report_id, user_id) "
            + "VALUES (:jobAssinged, :projectId, :firstReportId, :userId)", nativeQuery = true)
    void addMember(@Param("jobAssinged") String jobAssinged, @Param("projectId") Long projectId,
            @Param("firstReportId") Long firstReportId, @Param("userId") Long userId);
}
