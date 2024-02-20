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

import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.dto.User.UserProfileDTO;
import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.member.ListReportLessByUserDTO;
import vn.ansv.management.dto.member.TotalReportByUserDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;

/* ===== UserRepository.findUserProfileById() ===== */
@NamedNativeQuery(name = "UserEntity.findUserProfileById", query = "SELECT "
        + "u.id, u.avatar, u.fullname, u.username, u.employee_code AS employeeCode, "
        + "r.role_name AS userRole, wc.description AS workCenter "
        + "FROM user AS u "
        + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
        + "INNER JOIN role AS r ON ur.role_id = r.id "
        + "INNER JOIN work_center AS wc ON u.work_center_id = wc.id "
        + "WHERE u.id = :userId", resultSetMapping = "Mapping.UserProfileDTO")

/* ===== Set mapping: UserProfileDTO ===== */
@SqlResultSetMapping(name = "Mapping.UserProfileDTO", classes = @ConstructorResult(targetClass = UserProfileDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "avatar", type = String.class),
        @ColumnResult(name = "fullname", type = String.class),
        @ColumnResult(name = "username", type = String.class),
        @ColumnResult(name = "employeeCode", type = String.class),
        @ColumnResult(name = "userRole", type = String.class),
        @ColumnResult(name = "workCenter", type = String.class) }))

/* ===== UserRepository.reportLessByManagerAM() ===== */
@NamedNativeQuery(name = "UserEntity.reportLessByManagerAM", query = "SELECT "
        + "pr.id, c.avatar AS customerAvatar, pr.job_name AS reportName, pt.type_name AS typeName, "
        + "pp.priority_name AS priorityName, ps.status_name AS statusName "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_type AS pt ON pr.project_type_id = pt.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN user AS u ON pr.am_manager_id = u.id "
        + "WHERE pr.week = :week AND pr.year = :year AND u.id = :userId "
        + "ORDER BY pr.job_name", resultSetMapping = "Mapping.ListReportLessByUserDTO")

/* ===== UserRepository.reportLessByManagerPM() ===== */
@NamedNativeQuery(name = "UserEntity.reportLessByManagerPM", query = "SELECT "
        + "pr.id, c.avatar AS customerAvatar, pr.job_name AS reportName, pt.type_name AS typeName, "
        + "pp.priority_name AS priorityName, ps.status_name AS statusName "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_type AS pt ON pr.project_type_id = pt.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN user AS u ON pr.pm_manager_id = u.id "
        + "WHERE pr.week = :week AND pr.year = :year AND u.id = :userId "
        + "ORDER BY u.fullname", resultSetMapping = "Mapping.ListReportLessByUserDTO")

/* ===== UserRepository.reportLessByAM() ===== */
@NamedNativeQuery(name = "UserEntity.reportLessByAM", query = "SELECT "
        + "pr.id, c.avatar AS customerAvatar, pr.job_name AS reportName, pt.type_name AS typeName, "
        + "pp.priority_name AS priorityName, ps.status_name AS statusName "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_type AS pt ON pr.project_type_id = pt.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN user AS u ON pr.am_id = u.id "
        + "WHERE pr.week = :week AND pr.year = :year AND u.id = :userId "
        + "ORDER BY u.fullname", resultSetMapping = "Mapping.ListReportLessByUserDTO")

/* ===== UserRepository.reportLessByPM() ===== */
@NamedNativeQuery(name = "UserEntity.reportLessByPM", query = "SELECT "
        + "pr.id, c.avatar AS customerAvatar, pr.job_name AS reportName, pt.type_name AS typeName, "
        + "pp.priority_name AS priorityName, ps.status_name AS statusName "
        + "FROM project_report AS pr "
        + "INNER JOIN project AS p ON pr.project_id = p.id "
        + "INNER JOIN customer AS c ON p.customer_id = c.id "
        + "INNER JOIN project_type AS pt ON pr.project_type_id = pt.id "
        + "INNER JOIN project_priority AS pp ON pr.project_priority_id = pp.id "
        + "INNER JOIN project_status AS ps ON pr.project_status_id = ps.id "
        + "INNER JOIN user AS u ON pr.pm_id = u.id "
        + "WHERE pr.week = :week AND pr.year = :year AND u.id = :userId "
        + "ORDER BY u.fullname", resultSetMapping = "Mapping.ListReportLessByUserDTO")

/* ===== Set mapping: ListReportLessByUserDTO ===== */
@SqlResultSetMapping(name = "Mapping.ListReportLessByUserDTO", classes = @ConstructorResult(targetClass = ListReportLessByUserDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "customerAvatar", type = String.class),
        @ColumnResult(name = "reportName", type = String.class),
        @ColumnResult(name = "typeName", type = String.class),
        @ColumnResult(name = "priorityName", type = String.class),
        @ColumnResult(name = "statusName", type = String.class) }))

/* ===== UserRepository.reportTotalManagerAm() ===== */
@NamedNativeQuery(name = "UserEntity.reportTotalManagerAm", query = "SELECT "
        + "u.id, pr.week, pr.year, u.username, u.fullname, "
        + "(SELECT COUNT(pr.id) FROM project_report AS pr WHERE pr.am_manager_id = u.id "
        + "AND pr.week = :week AND pr.year = :year) AS reportTotal "
        + "FROM user AS u "
        + "INNER JOIN project_report AS pr ON u.id = pr.am_manager_id "
        + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
        + "INNER JOIN role AS r ON ur.role_id = r.id "
        + "WHERE pr.week = :week AND pr.year = :year AND r.role_name = :roleName "
        + "GROUP BY u.id ORDER BY u.fullname", resultSetMapping = "Mapping.TotalReportByUserDTO")

/* ===== UserRepository.reportTotalManagerPm() ===== */
@NamedNativeQuery(name = "UserEntity.reportTotalManagerPm", query = "SELECT "
        + "u.id, pr.week, pr.year, u.username, u.fullname, "
        + "(SELECT COUNT(pr.id) FROM project_report AS pr WHERE pr.pm_manager_id = u.id "
        + "AND pr.week = :week AND pr.year = :year) AS reportTotal "
        + "FROM user AS u "
        + "INNER JOIN project_report AS pr ON u.id = pr.pm_manager_id "
        + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
        + "INNER JOIN role AS r ON ur.role_id = r.id "
        + "WHERE pr.week = :week AND pr.year = :year AND r.role_name = :roleName "
        + "GROUP BY u.id ORDER BY u.fullname", resultSetMapping = "Mapping.TotalReportByUserDTO")

/* ===== UserRepository.reportTotalManagerAmOne() ===== */
@NamedNativeQuery(name = "UserEntity.reportTotalManagerAmOne", query = "SELECT "
        + "u.id, pr.week, pr.year, u.username, u.fullname, "
        + "(SELECT COUNT(pr.id) FROM project_report AS pr WHERE pr.am_manager_id = u.id "
        + "AND pr.week = :week AND pr.year = :year) AS reportTotal "
        + "FROM user AS u "
        + "INNER JOIN project_report AS pr ON u.id = pr.am_manager_id "
        + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
        + "INNER JOIN role AS r ON ur.role_id = r.id "
        + "WHERE pr.week = :week AND pr.year = :year AND pr.am_manager_id = :userId "
        + "GROUP BY u.id ORDER BY u.fullname", resultSetMapping = "Mapping.TotalReportByUserDTO")

/* ===== UserRepository.reportTotalManagerPmOne() ===== */
@NamedNativeQuery(name = "UserEntity.reportTotalManagerPmOne", query = "SELECT "
        + "u.id, pr.week, pr.year, u.username, u.fullname, "
        + "(SELECT COUNT(pr.id) FROM project_report AS pr WHERE pr.pm_manager_id = u.id "
        + "AND pr.week = :week AND pr.year = :year) AS reportTotal "
        + "FROM user AS u "
        + "INNER JOIN project_report AS pr ON u.id = pr.pm_manager_id "
        + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
        + "INNER JOIN role AS r ON ur.role_id = r.id "
        + "WHERE pr.week = :week AND pr.year = :year AND pr.pm_manager_id = :userId "
        + "GROUP BY u.id ORDER BY u.fullname", resultSetMapping = "Mapping.TotalReportByUserDTO")

/* ===== UserRepository.reportTotalAM() ===== */
@NamedNativeQuery(name = "UserEntity.reportTotalAM", query = "SELECT "
        + "u.id, pr.week, pr.year, u.username, u.fullname, "
        + "(SELECT COUNT(pr.id) FROM project_report AS pr WHERE pr.am_id = u.id "
        + "AND pr.week = :week AND pr.year = :year) AS reportTotal "
        + "FROM user AS u "
        + "INNER JOIN project_report AS pr ON u.id = pr.am_id "
        + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
        + "INNER JOIN role AS r ON ur.role_id = r.id "
        + "WHERE pr.week = :week AND pr.year = :year AND r.role_name = :roleName "
        + "GROUP BY u.id ORDER BY u.fullname", resultSetMapping = "Mapping.TotalReportByUserDTO")

/* ===== UserRepository.reportTotalPM() ===== */
@NamedNativeQuery(name = "UserEntity.reportTotalPM", query = "SELECT "
        + "u.id, pr.week, pr.year, u.username, u.fullname, "
        + "(SELECT COUNT(pr.id) FROM project_report AS pr WHERE pr.pm_id = u.id "
        + "AND pr.week = :week AND pr.year = :year) AS reportTotal "
        + "FROM user AS u "
        + "INNER JOIN project_report AS pr ON u.id = pr.pm_id "
        + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
        + "INNER JOIN role AS r ON ur.role_id = r.id "
        + "WHERE pr.week = :week AND pr.year = :year AND r.role_name = :roleName "
        + "GROUP BY u.id ORDER BY u.fullname", resultSetMapping = "Mapping.TotalReportByUserDTO")

/* ===== Set mapping: TotalReportByUserDTO ===== */
@SqlResultSetMapping(name = "Mapping.TotalReportByUserDTO", classes = @ConstructorResult(targetClass = TotalReportByUserDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "week", type = Integer.class),
        @ColumnResult(name = "year", type = Integer.class),
        @ColumnResult(name = "username", type = String.class),
        @ColumnResult(name = "fullname", type = String.class),
        @ColumnResult(name = "reportTotal", type = Integer.class) }))

/* ===== UserRepository.defineByUsername() ===== */
@NamedNativeQuery(name = "UserEntity.defineByUsername", query = "SELECT "
        + "u.id, r.role_name AS userRole, u.avatar FROM user AS u "
        + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
        + "INNER JOIN role AS r ON ur.role_id = r.id "
        + "WHERE u.username = :username ORDER BY r.level", resultSetMapping = "Mapping.UserDefineDTO")

/* ===== Set mapping: UserDefineDTO ===== */
@SqlResultSetMapping(name = "Mapping.UserDefineDTO", classes = @ConstructorResult(targetClass = UserDefineDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "userRole", type = String.class),
        @ColumnResult(name = "avatar", type = String.class) }))

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
        + "u.username, u.enabled, p.id AS positionId, "
        + "p.position_name AS positionName, p.position_explain AS positionExplain, "
        + "p.note AS positionDisplay, wc.id AS workCenterId, wc.center_name AS centerName, "
        + "wc.name_display AS centerDisplay, wc.description AS centerDescription "
        + "FROM user AS u "
        + "INNER JOIN user_position AS up ON u.id = up.user_id "
        + "INNER JOIN position AS p ON up.position_id = p.id "
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
        @ColumnResult(name = "uid", type = String.class),
        @ColumnResult(name = "avatar", type = String.class),
        @ColumnResult(name = "employeeCode", type = String.class),
        @ColumnResult(name = "fullname", type = String.class),
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
