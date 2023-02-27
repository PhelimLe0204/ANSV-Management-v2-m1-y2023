package vn.ansv.management.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListAllMemberDTO {
    private Long id; // User's id
    private Long uid;
    private String avatar;
    private String employeeCode;
    private String fullname;
    private String username;
    private Integer enabled;

    private Long positionId;
    private String positionName;
    private String positionExplain;
    private String positionDisplay; // position's note

    private Long workCenterId;
    private String centerName;
    private String centerDisplay;
    private String centerDescription;

    public ListAllMemberDTO(Long id, Long uid, String avatar, String employeeCode, String fullname, String username,
            Integer enabled, Long positionId, String positionName,
            String positionExplain, String positionDisplay, Long workCenterId, String centerName, String centerDisplay,
            String centerDescription) {
        this.id = id;
        this.uid = uid;
        this.avatar = avatar;
        this.employeeCode = employeeCode;
        this.fullname = fullname;
        this.username = username;
        this.enabled = enabled;
        this.positionId = positionId;
        this.positionName = positionName;
        this.positionExplain = positionExplain;
        this.positionDisplay = positionDisplay;
        this.workCenterId = workCenterId;
        this.centerName = centerName;
        this.centerDisplay = centerDisplay;
        this.centerDescription = centerDescription;
    }

}
