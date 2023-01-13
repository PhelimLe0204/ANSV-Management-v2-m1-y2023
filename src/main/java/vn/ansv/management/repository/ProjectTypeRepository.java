package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.selectOption.OptionProjectTypeDTO;
import vn.ansv.management.entity.ProjectTypeEntity;

public interface ProjectTypeRepository extends JpaRepository<ProjectTypeEntity, Long> {
    /*
     * -------------------------------------------
     * Chi tiết báo cáo dự án theo ID và Enabled
     * View: Detail
     * -------------------------------------------
     */
    @Query(nativeQuery = true)
    List<OptionProjectTypeDTO> findAllOption();
}
