package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.selectOption.OptionProjectDTO;

/* ===== ProjectRepository.findAllSelectOption() ===== */
@NamedNativeQuery(name = "ProjectEntity.findAllSelectOption", query = "SELECT "
        + "p.id, c.customer_name AS customerName, c.avatar AS customerAvatar, p.project_name AS projectName, "
        + "(SELECT u1.fullname FROM user AS u1 WHERE u1.id = (SELECT u2.id FROM project_report AS pr INNER JOIN user AS u2 ON pr.am_id = u2.id WHERE pr.project_id = p.id ORDER BY pr.created_date DESC LIMIT 1) LIMIT 1) AS currentAm, "
        + "(SELECT u1.fullname FROM user AS u1 WHERE u1.id = (SELECT u2.id FROM project_report AS pr INNER JOIN user AS u2 ON pr.pm_id = u2.id WHERE pr.project_id = p.id ORDER BY pr.created_date DESC LIMIT 1) LIMIT 1) AS currentPm "
        + "FROM project AS p "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "WHERE p.enabled = 1", resultSetMapping = "Mapping.OptionProjectDTO")

/* ===== Set mapping: OptionProjectDTO ===== */
@SqlResultSetMapping(name = "Mapping.OptionProjectDTO", classes = @ConstructorResult(targetClass = OptionProjectDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "customerAvatar", type = String.class),
        @ColumnResult(name = "projectName", type = String.class),
        @ColumnResult(name = "currentAm", type = String.class),
        @ColumnResult(name = "currentPm", type = String.class) }))

@Entity
@Table(name = "project")
public class ProjectEntity extends BaseEntity {
    // @Column(name = "customer_id", nullable = false)
    // private Long customerId;

    @Column(name = "project_name", nullable = false, columnDefinition = "TEXT")
    private String projectName;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT(4)")
    private Integer enabled;

    @Column(name = "note")
    private String note;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project' kết nối với bảng 'customer' (customer_id)"
     * )
     */
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer; // 1 'project' sử dụng 1 'customer' => hứng 1 bản ghi

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ class hiện tại (ProjectEntity) trong ProjectReportEntity: project"
     * )
     */
    @OneToMany(mappedBy = "project")
    /*
     * 1 'project' nằm trong nhiều 'project_report'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<ProjectReportEntity> projectReports = new ArrayList<>();

    // public Long getCustomerId() {
    // return this.customerId;
    // }
    // public void setCustomerId(Long customerId) {
    // this.customerId = customerId;
    // }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CustomerEntity getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<ProjectReportEntity> getProjectReports() {
        return this.projectReports;
    }

    public void setProjectReports(List<ProjectReportEntity> projectReports) {
        this.projectReports = projectReports;
    }

}
