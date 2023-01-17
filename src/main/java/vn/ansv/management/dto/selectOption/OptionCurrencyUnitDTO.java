package vn.ansv.management.dto.selectOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionCurrencyUnitDTO {
    private Long id;
    private String currencyUnit;
    private String description;

    public OptionCurrencyUnitDTO(Long id, String currencyUnit, String description) {
        this.id = id;
        this.currencyUnit = currencyUnit;
        this.description = description;
    }

}
