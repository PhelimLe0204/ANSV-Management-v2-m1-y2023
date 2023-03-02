package vn.ansv.management.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMemberDTO {
    private Long createdBy;
    private Long moddifiedBy;
    private String jobAssinged;
    private Long projectId;
    private Long firstReportId;
    private Long userId;

    public AddMemberDTO(Long createdBy, Long moddifiedBy, String jobAssinged, Long projectId, Long firstReportId,
            Long userId) {
        this.createdBy = createdBy;
        this.moddifiedBy = moddifiedBy;
        this.jobAssinged = jobAssinged;
        this.projectId = projectId;
        this.firstReportId = firstReportId;
        this.userId = userId;
    }

}
