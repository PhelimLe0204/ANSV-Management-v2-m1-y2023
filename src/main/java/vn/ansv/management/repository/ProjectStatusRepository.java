package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.selectOption.OptionProjectStatusDTO;
import vn.ansv.management.entity.ProjectStatusEntity;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatusEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionProjectStatusDTO> findAllOption();

    // Find status's id by status_name
    @Query(value = "SELECT ps.id FROM project_status AS ps WHERE ps.status_name = :statusName", nativeQuery = true)
    Long findIdByStatusName(@Param("statusName") String statusName);
}
