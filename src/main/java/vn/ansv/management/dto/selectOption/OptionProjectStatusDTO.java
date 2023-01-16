package vn.ansv.management.dto.selectOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionProjectStatusDTO {
    private Long id;
    private String statusDisplay;
    private String statusColor;

    public OptionProjectStatusDTO(Long id, String statusDisplay, String statusColor) {
        this.id = id;
        this.statusDisplay = statusDisplay;
        this.statusColor = statusColor;
    }

}
