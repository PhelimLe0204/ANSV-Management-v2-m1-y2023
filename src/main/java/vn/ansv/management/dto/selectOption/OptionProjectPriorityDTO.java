package vn.ansv.management.dto.selectOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionProjectPriorityDTO {
    private Long id;
    private String priorityDisplay;

    public OptionProjectPriorityDTO(Long id, String priorityDisplay) {
        this.id = id;
        this.priorityDisplay = priorityDisplay;
    }
}
