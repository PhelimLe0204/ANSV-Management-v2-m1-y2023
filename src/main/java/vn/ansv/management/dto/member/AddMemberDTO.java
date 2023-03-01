package vn.ansv.management.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMemberDTO {
    private String jobAssinged;
    private Long projectId;
    private Long firstReportId;
    private Long userId;

    public AddMemberDTO(Long firstReportId, String jobAssinged, Long projectId, Long userId) {
        this.jobAssinged = jobAssinged;
        this.projectId = projectId;
        this.firstReportId = firstReportId;
        this.userId = userId;
    }

}
