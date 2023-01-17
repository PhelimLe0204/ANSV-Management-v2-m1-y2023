package vn.ansv.management.dto.selectOption;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionProjectDTO {
    private Long id;
    private String customerName;
    private String customerAvatar;
    private String projectName;
    private String currentAm;
    private String currentPm;

    // calculated field = transient, not exist in MySql
    @Transient
    private int onProgress;

    public int getOnProgress() {
        return getCurrentAm() == null ? 0 : 1;
    }

    public OptionProjectDTO(Long id, String customerName, String customerAvatar, String projectName,
            String currentAm, String currentPm) {
        this.id = id;
        this.customerName = customerName;
        this.customerAvatar = customerAvatar;
        this.projectName = projectName;
        this.currentAm = currentAm;
        this.currentPm = currentPm;
    }

}
