package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "file_upload")
public class FileUploadEntity extends BaseEntity {
    // @Column(name = "project_report_id", nullable = false)
    // private Long projectReportId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'file_upload' kết nối với bảng 'project_report' (project_report_id)"
     * )
     */
    @JoinColumn(name = "project_report_id")
    private ProjectReportEntity projectReport; // 1 'file_upload' sử dụng 1 'project_report' => hứng 1 bản ghi

    // public Long getProjectReportId() {
    // return this.projectReportId;
    // }
    // public void setProjectReportId(Long projectReportId) {
    // this.projectReportId = projectReportId;
    // }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ProjectReportEntity getProjectReport() {
        return this.projectReport;
    }

    public void setProjectReport(ProjectReportEntity projectReport) {
        this.projectReport = projectReport;
    }

}
