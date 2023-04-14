package vn.ansv.management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.member.ListReportLessByUserDTO;
import vn.ansv.management.dto.member.TotalReportByUserDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;
import vn.ansv.management.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true)
    List<OptionUserDTO> findAllUserOption();

    @Query(nativeQuery = true)
    List<TotalReportByUserDTO> reportTotalManagerAm(@Param("week") Integer week, @Param("year") Integer year,
            @Param("roleName") String roleName);

    @Query(nativeQuery = true)
    List<TotalReportByUserDTO> reportTotalManagerPm(@Param("week") Integer week, @Param("year") Integer year,
            @Param("roleName") String roleName);

    @Query(nativeQuery = true)
    List<TotalReportByUserDTO> reportTotalAM(@Param("week") Integer week, @Param("year") Integer year,
            @Param("roleName") String roleName);

    @Query(nativeQuery = true)
    List<TotalReportByUserDTO> reportTotalPM(@Param("week") Integer week, @Param("year") Integer year,
            @Param("roleName") String roleName);

    @Query(nativeQuery = true)
    List<ListReportLessByUserDTO> reportLessByManagerAM(@Param("week") Integer week, @Param("year") Integer year,
            @Param("userId") Long userId);

    @Query(nativeQuery = true)
    List<ListReportLessByUserDTO> reportLessByManagerPM(@Param("week") Integer week, @Param("year") Integer year,
            @Param("userId") Long userId);

    @Query(nativeQuery = true)
    List<ListReportLessByUserDTO> reportLessByAM(@Param("week") Integer week, @Param("year") Integer year,
            @Param("userId") Long userId);

    @Query(nativeQuery = true)
    List<ListReportLessByUserDTO> reportLessByPM(@Param("week") Integer week, @Param("year") Integer year,
            @Param("userId") Long userId);

    @Query(nativeQuery = true)
    UserDefineDTO defineByUsername(String username);

    @Query(nativeQuery = true)
    List<ListAllMemberDTO> findAllByWorkCenter(Long centerId);

    /*
     * ------------------------------
     * Cập nhật trạng thái của user
     * ------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE user AS u SET u.enabled = :enabled WHERE u.id = :id", nativeQuery = true)
    void updateUserEnabled(@Param("id") Long id, @Param("enabled") Integer enabled);

    // Tìm kiếm user's id theo username
    @Query(value = "SELECT u.id FROM user AS u WHERE u.username = :username", nativeQuery = true)
    Long findIdByUsername(@Param("username") String username);

    // Tìm kiếm user's id theo fullname và role_name
    @Query(value = "SELECT u.id FROM user AS u "
            + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
            + "INNER JOIN role AS r ON ur.role_id = r.id "
            + "WHERE u.fullname = :fullname AND r.role_name = :roleName", nativeQuery = true)
    Long findIdByFullnameWithRoleName(@Param("fullname") String fullname, @Param("roleName") String roleName);

    // Check user's isset by customer_name
    @Query(value = "SELECT COUNT(u.id) FROM user AS u WHERE u.fullname = :fullname", nativeQuery = true)
    Integer checkIssetByFullname(@Param("fullname") String fullname);

    // Check user's isset by customer_name with role_name
    @Query(value = "SELECT COUNT(u.id) FROM user AS u "
            + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
            + "INNER JOIN role AS r ON ur.role_id = r.id "
            + "WHERE u.fullname = :fullname AND r.role_name = :roleName", nativeQuery = true)
    Integer checkIssetByFullnameWithRoleName(@Param("fullname") String fullname,
            @Param("roleName") String roleName);

    // Thêm mới user
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user (created_by, uid, avatar, employee_code, enabled, fullname, "
            + "password, username, work_center_id) "
            + "VALUES (:createdBy, :uid, :avatar, :employeeCode, :enabled, :fullname, :password, :username, "
            + ":workCenterId)", nativeQuery = true)
    void addUser(@Param("createdBy") String createdBy, @Param("uid") String uid, @Param("avatar") String avatar,
            @Param("employeeCode") String employeeCode, @Param("enabled") Integer enabled,
            @Param("fullname") String fullname, @Param("password") String password,
            @Param("username") String username,
            @Param("workCenterId") Long workCenterId);
}
