package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.selectOption.OptionCurrencyUnitDTO;

public interface ICurrencyUnit {
    List<OptionCurrencyUnitDTO> findAllSelectOption();
}
