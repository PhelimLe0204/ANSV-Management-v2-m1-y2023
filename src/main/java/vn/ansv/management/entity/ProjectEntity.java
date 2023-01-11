package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
