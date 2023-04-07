package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.Customer.ListCustomerDTO;
import vn.ansv.management.dto.selectOption.OptionCustomerDTO;

/* ===== CustomerRepository.findAllSelectOption() ===== */
@NamedNativeQuery(name = "CustomerEntity.findAllSelectOption", query = "SELECT "
        + "c.id, c.avatar, c.customer_name AS customerName "
        + "FROM customer AS c", resultSetMapping = "Mapping.OptionCustomerDTO")

/* ===== Set mapping: OptionCustomerDTO ===== */
@SqlResultSetMapping(name = "Mapping.OptionCustomerDTO", classes = @ConstructorResult(targetClass = OptionCustomerDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "avatar", type = String.class),
        @ColumnResult(name = "customerName", type = String.class) }))

/* ===== CustomerRepository.findAllList() ===== */
@NamedNativeQuery(name = "CustomerEntity.findAllList", query = "SELECT "
        + "c.id, c.avatar, c.customer_name AS customerName, c.enabled, c.created_by AS createdBy, "
        + "c.created_date AS createdDate, c.moddified_by AS moddifiedBy, c.moddified_date AS moddifiedDate "
        + "FROM customer AS c ORDER BY c.customer_name", resultSetMapping = "Mapping.ListCustomerDTO")

/* ===== CustomerRepository.findDetailById() ===== */
@NamedNativeQuery(name = "CustomerEntity.findDetailById", query = "SELECT "
        + "c.id, c.avatar, c.customer_name AS customerName, c.enabled, c.created_by AS createdBy, "
        + "c.created_date AS createdDate, c.moddified_by AS moddifiedBy, c.moddified_date AS moddifiedDate "
        + "FROM customer AS c WHERE c.id = :id", resultSetMapping = "Mapping.ListCustomerDTO")

/* ===== Set mapping: ListCustomerDTO ===== */
@SqlResultSetMapping(name = "Mapping.ListCustomerDTO", classes = @ConstructorResult(targetClass = ListCustomerDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "avatar", type = String.class),
        @ColumnResult(name = "customerName", type = String.class),
        @ColumnResult(name = "enabled", type = Integer.class),
        @ColumnResult(name = "createdBy", type = String.class),
        @ColumnResult(name = "createdDate", type = Date.class),
        @ColumnResult(name = "moddifiedBy", type = String.class),
        @ColumnResult(name = "moddifiedDate", type = Date.class) }))

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
