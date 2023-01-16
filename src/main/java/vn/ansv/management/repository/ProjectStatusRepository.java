package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.selectOption.OptionProjectStatusDTO;
import vn.ansv.management.entity.ProjectStatusEntity;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatusEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionProjectStatusDTO> findAllOption();
}
