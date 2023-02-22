package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Column(name = "position_id", nullable = false)
    private Integer positionId;

    @Column(name = "position_assigned_by", nullable = false)
    private String positionAssignedBy;

    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT(4)")
    private Integer enabled;

    @ManyToMany
    /*
     * @JoinTable(name = "tên bảng trung gian", joinColumns = @JoinColumn(name =
     * "khóa ngoại của bảng trung gian kết nối đến bảng hiện tại (user)"),
     * inverseJoinColumns = @JoinColumn(name =
     * "khóa ngoại của bảng trung gian kết nối đến bảng còn lại (role)"))
     */
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>(); // 1 'user' có nhiều 'role' => dùng List để hứng mảng dữ liệu

    public Integer getPositionId() {
        return this.positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionAssignedBy() {
        return this.positionAssignedBy;
    }

    public void setPositionAssignedBy(String positionAssignedBy) {
        this.positionAssignedBy = positionAssignedBy;
    }

    public String getEmployeeCode() {
        return this.employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'user' kết nối với bảng 'work_center' (work_center_id)"
     * )
     */
    @JoinColumn(name = "work_center_id")
    private WorkCenterEntity workCenter; // 1 'user' sử dụng 1 'work_center' => hứng 1 bản ghi

}
