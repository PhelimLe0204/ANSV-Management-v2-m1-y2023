package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.ansv.management.dto.selectOption.OptionCustomerDTO;
import vn.ansv.management.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionCustomerDTO> findAllSelectOption();

    // Check customer's isset by customer_name
    @Query(value = "SELECT COUNT(c.id) FROM customer AS c WHERE c.customer_name = :CustomerName", nativeQuery = true)
    Integer checkIssetByCustomerName(@Param("CustomerName") String CustomerName);
}
