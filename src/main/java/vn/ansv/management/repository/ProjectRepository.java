package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.selectOption.OptionProjectDTO;
import vn.ansv.management.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionProjectDTO> findAllSelectOption();
}
