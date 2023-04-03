package vn.ansv.management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.Customer.ListCustomerDTO;
import vn.ansv.management.dto.selectOption.OptionCustomerDTO;
import vn.ansv.management.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionCustomerDTO> findAllSelectOption();

    @Query(nativeQuery = true)
    List<ListCustomerDTO> findAllList();

    // Check customer's isset by customer_name
    @Query(value = "SELECT c.id FROM customer AS c WHERE c.customer_name = :CustomerName", nativeQuery = true)
    Long findIdByCustomerName(@Param("CustomerName") String CustomerName);

    // Cập nhật enabled
    @Transactional
    @Modifying
    @Query(value = "UPDATE customer AS c SET c.enabled = :enabled WHERE c.id = :id", nativeQuery = true)
    void updateEnabled(@Param("id") Long id, @Param("enabled") Integer enabled);
}
