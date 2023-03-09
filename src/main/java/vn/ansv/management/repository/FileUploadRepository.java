package vn.ansv.management.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.entity.FileUploadEntity;

public interface FileUploadRepository extends JpaRepository<FileUploadEntity, Long> {

    // Check file isset by project_report_id
    @Query(value = "SELECT COUNT(f.id) FROM file_upload AS f "
            + "WHERE f.project_report_id = :project_report_id", nativeQuery = true)
    Integer checkFileUploadByProjectReportId(@Param("project_report_id") Long project_report_id);

    /*
     * Delete file upload theo project_report id
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE file_upload AS f WHERE f.project_report_id = :project_report_id", nativeQuery = true)
    void deleteFileUploadByProjectReportId(@Param("project_report_id") Long project_report_id);
}
