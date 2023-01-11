package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project_type")
public class ProjectTypeEntity extends BaseEntity {
    @Column(name = "step")
    private Integer step;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "display", nullable = false)
    private String display;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ bảng hiện tại (ProjectTypeEntity) trong ProjectReportEntity (projectType)"
     * )
     */
    @OneToMany(mappedBy = "projectType")
    /*
     * 1 'project_type' nằm trong nhiều 'project_report'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<ProjectReportEntity> projectReports = new ArrayList<>();

    public Integer getStep() {
        return this.step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<ProjectReportEntity> getProjectReports() {
        return this.projectReports;
    }

    public void setProjectReports(List<ProjectReportEntity> projectReports) {
        this.projectReports = projectReports;
    }

}
