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
     * "khóa ngoại trong bảng 'project_report_member' kết nối với bảng 'project_report' (project_report_id)"
     * )
     */
    @JoinColumn(name = "project_report_id")
    private ProjectReportEntity projectReport; // 1 'project_report_member' sử dụng 1 'project_report' => hứng 1 bản ghi

    // public Long getProjectReportId() {
    // return this.projectReportId;
    // }
    // public void setProjectReportId(Long projectReportId) {
    // this.projectReportId = projectReportId;
    // }

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

    public ProjectReportEntity getProjectReport() {
        return this.projectReport;
    }

    public void setProjectReport(ProjectReportEntity projectReport) {
        this.projectReport = projectReport;
    }

}
