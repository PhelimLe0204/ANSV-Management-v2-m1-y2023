package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(nativeQuery = true)
    List<ListAllMemberDTO> findAllByWorkCenter(Long centerId);
}
