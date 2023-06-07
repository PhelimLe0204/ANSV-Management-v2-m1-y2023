package vn.ansv.management.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    // Thêm mới role cho user (table user_role)
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_role (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void addUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    // Get allow_export
    @Query(value = "SELECT r.allow_export FROM role AS r WHERE r.role_name = :roleName", nativeQuery = true)
    String findAllowExportByRoleName(@Param("roleName") String roleName);
}
