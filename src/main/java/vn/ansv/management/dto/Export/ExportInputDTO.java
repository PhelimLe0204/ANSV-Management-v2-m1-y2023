package vn.ansv.management.dto.Export;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportInputDTO {
    private Integer weekExport;
    private Integer yearExport;

    public ExportInputDTO(Integer weekExport, Integer yearExport) {
        this.weekExport = weekExport;
        this.yearExport = yearExport;
    }

}
