package vn.ansv.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.ansv.management.dto.selectOption.OptionCurrencyUnitDTO;
import vn.ansv.management.entity.CurrencyUnitEntity;

public interface CurrencyUnitRepository extends JpaRepository<CurrencyUnitEntity, Long> {
    @Query(nativeQuery = true)
    List<OptionCurrencyUnitDTO> findAllSelectOption();
}
