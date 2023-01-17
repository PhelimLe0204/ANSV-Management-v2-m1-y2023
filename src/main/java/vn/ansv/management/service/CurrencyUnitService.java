package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.selectOption.OptionCurrencyUnitDTO;
import vn.ansv.management.repository.CurrencyUnitRepository;
import vn.ansv.management.service.Interface.ICurrencyUnit;

@Service
public class CurrencyUnitService implements ICurrencyUnit {
    @Autowired
    private CurrencyUnitRepository currencyUnitRepository;

    @Override
    public List<OptionCurrencyUnitDTO> findAllSelectOption() {
        return currencyUnitRepository.findAllSelectOption();
    }

}
