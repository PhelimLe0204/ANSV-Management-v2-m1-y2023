package vn.ansv.management.dto.Detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailTabQuaTrinhDTO {
    private Long id;
    private String generalIssue;
    private String solution;
    private String keHoachTuanNay;
    private String keHoachTuanSau;
    private String ketQuaTuanTruoc;
    private String ketQuaTuanNay;

    public ReportDetailTabQuaTrinhDTO(Long id, String generalIssue, String solution, String keHoachTuanNay,
            String keHoachTuanSau, String ketQuaTuanTruoc, String ketQuaTuanNay) {
        this.id = id;
        this.generalIssue = generalIssue;
        this.solution = solution;
        this.keHoachTuanNay = keHoachTuanNay;
        this.keHoachTuanSau = keHoachTuanSau;
        this.ketQuaTuanTruoc = ketQuaTuanTruoc;
        this.ketQuaTuanNay = ketQuaTuanNay;
    }

}
