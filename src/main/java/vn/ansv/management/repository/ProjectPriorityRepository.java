package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.selectOption.OptionProjectPriorityDTO;
import vn.ansv.management.entity.ProjectPriorityEntity;

public interface ProjectPriorityRepository extends JpaRepository<ProjectPriorityEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionProjectPriorityDTO> findAllOption();
}
