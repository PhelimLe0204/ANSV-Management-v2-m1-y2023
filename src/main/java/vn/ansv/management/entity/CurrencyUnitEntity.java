package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

// import java.util.ArrayList;
// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "currency_unit")
public class CurrencyUnitEntity extends BaseEntity {
    @Column(name = "currency_unit", unique = true)
    private String currencyUnit;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ CurrencyUnitEntity trong ProjectReportEntity (currencyUnit)"
     * )
     */
    @OneToMany(mappedBy = "currencyUnit")
    /*
     * 1 'currency_unit' nằm trong nhiều 'project_report'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<ProjectReportEntity> projectReports = new ArrayList<>();

    public String getCurrencyUnit() {
        return this.currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectReportEntity> getProjectReports() {
        return this.projectReports;
    }

    public void setProjectReports(List<ProjectReportEntity> projectReports) {
        this.projectReports = projectReports;
    }

}
