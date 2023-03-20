package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.selectOption.OptionProjectPriorityDTO;
import vn.ansv.management.entity.ProjectPriorityEntity;

public interface ProjectPriorityRepository extends JpaRepository<ProjectPriorityEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionProjectPriorityDTO> findAllOption();

    // Find priority's id by priority_name
    @Query(value = "SELECT pp.id FROM project_priority AS pp WHERE pp.priority_name = :priorityName", nativeQuery = true)
    Long findIdByPriorityName(@Param("priorityName") String priorityName);
}
