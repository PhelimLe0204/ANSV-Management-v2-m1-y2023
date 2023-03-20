package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.selectOption.OptionProjectTypeDTO;
import vn.ansv.management.entity.ProjectTypeEntity;

public interface ProjectTypeRepository extends JpaRepository<ProjectTypeEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionProjectTypeDTO> findAllOption();

    // Find type's id by type_name
    @Query(value = "SELECT pt.id FROM project_type AS pt WHERE pt.type_name = :typeName", nativeQuery = true)
    Long findIdByTypeName(@Param("typeName") String typeName);
}
