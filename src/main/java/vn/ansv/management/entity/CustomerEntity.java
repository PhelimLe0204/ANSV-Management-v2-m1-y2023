package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
    @Column(name = "customer_name", unique = true)
    private String customerName;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT(4)")
    private String enabled;

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ CustomerEntity trong ProjectEntity (customer)"
     * )
     */
    @OneToMany(mappedBy = "customer")
    /*
     * 1 'customer' nằm trong nhiều 'project'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<ProjectEntity> projects = new ArrayList<>();

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public List<ProjectEntity> getProjects() {
        return this.projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

}
