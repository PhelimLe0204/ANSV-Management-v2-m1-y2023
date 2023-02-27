package vn.ansv.management.dto.selectOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionUserDTO {
    private Long id;
    private String employeeCode;
    private String fullname;
    private String avatar;
    private String position;
    private String workCenter;

    public OptionUserDTO(Long id, String employeeCode, String fullname, String avatar, String position,
            String workCenter) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.fullname = fullname;
        this.avatar = avatar;
        this.position = position;
        this.workCenter = workCenter;
    }

}
