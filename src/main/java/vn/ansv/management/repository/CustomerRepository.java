package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.selectOption.OptionCustomerDTO;
import vn.ansv.management.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionCustomerDTO> findAllSelectOption();
}
