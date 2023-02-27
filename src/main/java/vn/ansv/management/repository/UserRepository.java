package vn.ansv.management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;
import vn.ansv.management.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true)
    List<OptionUserDTO> findAllUserOption();

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
}
