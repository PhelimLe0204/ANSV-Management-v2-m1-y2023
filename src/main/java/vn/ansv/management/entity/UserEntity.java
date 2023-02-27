package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;

/* ===== UserRepository.findAllUserOption() ===== */
@NamedNativeQuery(name = "UserEntity.findAllUserOption", query = "SELECT "
        + "u.id, u.employee_code AS employeeCode, u.fullname, u.avatar, p.position_name AS position, "
        + "wc.name_display AS workCenter "
        + "FROM user AS u "
        + "INNER JOIN user_position AS up ON u.id = up.user_id "
        + "INNER JOIN position AS p ON up.position_id = p.id "
        + "INNER JOIN work_center AS wc ON u.work_center_id = wc.id "
        + "WHERE u.enabled = 1 "
        + "ORDER BY u.fullname", resultSetMapping = "Mapping.OptionUserDTO")

/* ===== UserRepository.findAllByWorkCenter() ===== */
@NamedNativeQuery(name = "UserEntity.findAllByWorkCenter", query = "SELECT "
        + "u.id, u.uid, u.avatar, u.employee_code AS employeeCode, u.fullname, "
        + "u.position_assigned_by AS positionAssignedBy, u.username, u.enabled, p.id AS positionId, "
        + "p.position_name AS positionName, p.position_explain AS positionExplain, "
        + "p.note AS positionDisplay, wc.id AS workCenterId, wc.center_name AS centerName, "
        + "wc.name_display AS centerDisplay, wc.description AS centerDescription "
        + "FROM user AS u "
        + "INNER JOIN position AS p ON u.position_id = p.id "
        + "INNER JOIN work_center AS wc ON u.work_center_id = wc.id "
        + "WHERE wc.id = :centerId "
        + "ORDER BY u.fullname", resultSetMapping = "Mapping.ListAllMemberDTO")

/* ===== Set mapping: OptionUserDTO ===== */
@SqlResultSetMapping(name = "Mapping.OptionUserDTO", classes = @ConstructorResult(targetClass = OptionUserDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "employeeCode", type = String.class),
        @ColumnResult(name = "fullname", type = String.class),
        @ColumnResult(name = "avatar", type = String.class),
        @ColumnResult(name = "position", type = String.class),
        @ColumnResult(name = "workCenter", type = String.class) }))

/* ===== Set mapping: ListAllMemberDTO ===== */
@SqlResultSetMapping(name = "Mapping.ListAllMemberDTO", classes = @ConstructorResult(targetClass = ListAllMemberDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "uid", type = Long.class),
        @ColumnResult(name = "avatar", type = String.class),
        @ColumnResult(name = "employeeCode", type = String.class),
        @ColumnResult(name = "fullname", type = String.class),
        @ColumnResult(name = "positionAssignedBy", type = String.class),
        @ColumnResult(name = "username", type = String.class),
        @ColumnResult(name = "enabled", type = Integer.class),

        @ColumnResult(name = "positionId", type = Long.class),
        @ColumnResult(name = "positionName", type = String.class),
        @ColumnResult(name = "positionExplain", type = String.class),
        @ColumnResult(name = "positionDisplay", type = String.class),

        @ColumnResult(name = "workCenterId", type = Long.class),
        @ColumnResult(name = "centerName", type = String.class),
        @ColumnResult(name = "centerDisplay", type = String.class),
        @ColumnResult(name = "centerDescription", type = String.class) }))

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    // @Column(name = "position_id", nullable = false)
    // private Integer positionId;

    // @Column(name = "position_assigned_by", nullable = false)
    // private String positionAssignedBy;

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

    // public Integer getPositionId() {
    // return this.positionId;
    // }

    // public void setPositionId(Integer positionId) {
    // this.positionId = positionId;
    // }

    // public String getPositionAssignedBy() {
    // return this.positionAssignedBy;
    // }

    // public void setPositionAssignedBy(String positionAssignedBy) {
    // this.positionAssignedBy = positionAssignedBy;
    // }

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
