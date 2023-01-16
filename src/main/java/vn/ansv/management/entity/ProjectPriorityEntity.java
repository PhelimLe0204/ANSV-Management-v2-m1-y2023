package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.selectOption.OptionProjectPriorityDTO;

/* ===== ProjectPriorityRepository.findAllOption() ===== */
@NamedNativeQuery(name = "ProjectPriorityEntity.findAllOption", query = "SELECT "
        + "pp.id, pp.priority_name AS priorityDisplay FROM project_priority AS pp", resultSetMapping = "Mapping.OptionProjectPriorityDTO")

/* ===== Set mapping: OptionProjectPriorityDTO ===== */
@SqlResultSetMapping(name = "Mapping.OptionProjectPriorityDTO", classes = @ConstructorResult(targetClass = OptionProjectPriorityDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "priorityDisplay", type = String.class) }))

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
