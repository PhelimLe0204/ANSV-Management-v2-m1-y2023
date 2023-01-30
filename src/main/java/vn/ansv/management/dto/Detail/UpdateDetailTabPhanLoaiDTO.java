package vn.ansv.management.dto.Detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDetailTabPhanLoaiDTO {
    private Long id;
    private Long projectId;
    private Long projectTypeId;
    private Long projectPriorityId;
    private Long projectStatusId;
    private int week;
    private int year;
    private String maHopDong;
    private String maKeToan;
    private Long currencyUnitId;

    public UpdateDetailTabPhanLoaiDTO(Long id, Long projectId, Long projectTypeId, Long projectPriorityId,
            Long projectStatusId, int week, int year, String maHopDong, String maKeToan, Long currencyUnitId) {
        this.id = id;
        this.projectId = projectId;
        this.projectTypeId = projectTypeId;
        this.projectPriorityId = projectPriorityId;
        this.projectStatusId = projectStatusId;
        this.week = week;
        this.year = year;
        this.maHopDong = maHopDong;
        this.maKeToan = maKeToan;
        this.currencyUnitId = currencyUnitId;
    }

}
