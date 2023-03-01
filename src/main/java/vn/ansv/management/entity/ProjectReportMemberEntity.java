package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.Detail.ReportDetailTabThanhVienDTO;

/* ===== ProjectReportMemberRepository.findAllMemberByReport() ===== */
@NamedNativeQuery(name = "ProjectReportMemberEntity.findAllMemberByReport", query = "SELECT "
        + "prm.id, prm.project_id AS projectId, prm.first_report_id AS firstReportId, "
        + "u.avatar, u.fullname, p.position_name AS position "
        + "FROM project_report_member AS prm "
        + "INNER JOIN user AS u on prm.user_id = u.id "
        + "INNER JOIN user_position AS up on u.id = up.user_id "
        + "INNER JOIN position AS p on up.position_id = p.id "
        + "WHERE prm.project_id = :projectId AND prm.first_report_id = :id", resultSetMapping = "Mapping.ReportDetailTabThanhVienDTO")

/* ===== Set mapping: ReportDetailTabThanhVienDTO ===== */
@SqlResultSetMapping(name = "Mapping.ReportDetailTabThanhVienDTO", classes = @ConstructorResult(targetClass = ReportDetailTabThanhVienDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "projectId", type = Long.class),
        @ColumnResult(name = "firstReportId", type = Long.class),
        @ColumnResult(name = "avatar", type = String.class),
        @ColumnResult(name = "fullname", type = String.class),
        @ColumnResult(name = "position", type = String.class) }))

@Entity
@Table(name = "project_report_member")
public class ProjectReportMemberEntity extends BaseEntity {
    // @Column(name = "project_report_id", nullable = false)
    // private Long projectReportId;

    @Column(name = "job_assinged", nullable = false, columnDefinition = "TEXT")
    private String jobAssinged;

    @Column(name = "job_detail", columnDefinition = "TEXT")
    private String jobDetail;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report_member' kết nối với bảng 'user' (user_id)"
     * )
     */
    @JoinColumn(name = "user_id")
    private UserEntity user; // 1 'project_report_member' sử dụng 1 'user' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report_member' kết nối với bảng 'project_report' (first_report_id)"
     * )
     */
    @JoinColumn(name = "first_report_id")
    private ProjectReportEntity projectReport; // 1 'project_report_member' sử dụng 1 'project_report' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report_member' kết nối với bảng 'project' (project_id)"
     * )
     */
    @JoinColumn(name = "project_id")
    private ProjectEntity project; // 1 'project_report_member' thuộc 1 'project' => hứng 1 bản ghi

    public String getJobAssinged() {
        return this.jobAssinged;
    }

    public void setJobAssinged(String jobAssinged) {
        this.jobAssinged = jobAssinged;
    }

    public String getJobDetail() {
        return this.jobDetail;
    }

    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProjectReportEntity getProjectReport() {
        return this.projectReport;
    }

    public void setProjectReport(ProjectReportEntity projectReport) {
        this.projectReport = projectReport;
    }

    public ProjectEntity getProject() {
        return this.project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

}
