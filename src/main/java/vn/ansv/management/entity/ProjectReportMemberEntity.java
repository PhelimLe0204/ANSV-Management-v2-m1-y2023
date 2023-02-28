package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
