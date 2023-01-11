package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project_priority")
public class ProjectPriorityEntity extends BaseEntity {
    @Column(name = "priority_name", nullable = false)
    private String priorityName;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "display", nullable = false)
    private String display;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ ProjectPriorityEntity trong ProjectReportEntity (projectPriority)"
     * )
     */
    @OneToMany(mappedBy = "projectPriority")
    /*
     * 1 'project_priority' nằm trong nhiều 'project_report'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<ProjectReportEntity> projectReports = new ArrayList<>();

    public String getPriorityName() {
        return this.priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectReportEntity> getProjectReports() {
        return this.projectReports;
    }

    public void setProjectReports(List<ProjectReportEntity> projectReports) {
        this.projectReports = projectReports;
    }

}
