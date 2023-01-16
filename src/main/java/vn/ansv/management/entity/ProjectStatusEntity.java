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

import vn.ansv.management.dto.selectOption.OptionProjectStatusDTO;

/* ===== ProjectStatusRepository.findAllOption() ===== */
@NamedNativeQuery(name = "ProjectStatusEntity.findAllOption", query = "SELECT "
        + "ps.id, ps.status_name AS statusDisplay, ps.color AS statusColor FROM project_status AS ps", resultSetMapping = "Mapping.OptionProjectStatusDTO")

/* ===== Set mapping: OptionProjectStatusDTO ===== */
@SqlResultSetMapping(name = "Mapping.OptionProjectStatusDTO", classes = @ConstructorResult(targetClass = OptionProjectStatusDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "statusDisplay", type = String.class),
        @ColumnResult(name = "statusColor", type = String.class) }))

@Entity
@Table(name = "project_status")
public class ProjectStatusEntity extends BaseEntity {
    @Column(name = "status_name", nullable = false)
    private String statusName;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "display", nullable = false)
    private String display;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ bảng hiện tại (ProjectStatusEntity) trong ProjectReportEntity: projectStatus"
     * )
     */
    @OneToMany(mappedBy = "projectStatus")
    /*
     * 1 'project_status' nằm trong nhiều 'project_report'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<ProjectReportEntity> projectReports = new ArrayList<>();

    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
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
