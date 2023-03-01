package vn.ansv.management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.selectOption.OptionProjectDTO;
import vn.ansv.management.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
        @Query(nativeQuery = true)
        List<OptionProjectDTO> findAllSelectOption();

        /*
         * -------------------------------------------
         * Tìm kiếm theo mã báo cáo và mã khách hàng
         * -------------------------------------------
         */
        @Query(value = "SELECT COUNT(*) FROM project AS p "
                        + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
                        + "WHERE p.customer_id = :customerId AND pr.id = :id", nativeQuery = true)
        Integer findByCustomerAndReport(@Param("id") Long id, @Param("customerId") Long customerId);

        /*
         * --------------------------------------
         * Cập nhật customer_id theo mã báo cáo
         * --------------------------------------
         */
        @Transactional
        @Modifying
        @Query(value = "UPDATE project AS p "
                        + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
                        + "SET p.customer_id = :customerId "
                        + "WHERE pr.id = :id", nativeQuery = true)
        void updateCustomerByReportId(@Param("id") Long id, @Param("customerId") Long customerId);

        /* ===== Tìm kiếm mã dự án theo mã báo cáo ===== */
        @Query(value = "SELECT p.id FROM project AS p "
                        + "INNER JOIN project_report AS pr on p.id = pr.project_id "
                        + "WHERE pr.id = :reportId", nativeQuery = true)
        Long findIdByReport(Long reportId);
}
