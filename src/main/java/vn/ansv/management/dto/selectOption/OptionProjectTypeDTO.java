package vn.ansv.management.dto.selectOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionProjectTypeDTO {
    private Long id;
    private String typeDisplay;

    public OptionProjectTypeDTO(Long id, String typeDisplay) {
        this.id = id;
        this.typeDisplay = typeDisplay;
    }

}
