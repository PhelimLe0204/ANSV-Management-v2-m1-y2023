package vn.ansv.management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project_report")
public class ProjectReportEntity extends BaseEntity {
    // @Column(name = "id") // 1
    // private int id;
    // @Column(name = "uid") // 2
    // private String uid;
    // @Column(name = "project_id", nullable = false) // 3 - Foreign Key
    // private Long projectId;
    // @Column(name = "project_type_id", nullable = false) // 4 - Foreign Key
    // private Long projectTypeId;
    // @Column(name = "project_priority_id", nullable = false) // 5 - Foreign Key
    // private Long projectPriorityId;
    // @Column(name = "project_status_id", nullable = false) // 6 - Foreign Key
    // private Long projectStatusId;
    // @Column(name = "currency_unit_id", nullable = false) // 7
    // private Long currencyUnitId;

    @Column(name = "am_manager_id") // 8
    private Integer amManagerId;

    @Column(name = "am_id", nullable = false) // 9
    private Integer amId;

    @Column(name = "pm_manager_id") // 10
    private Integer pmManagerId;

    @Column(name = "pm_id") // 11
    private Integer pmId;

    @Column(name = "week", nullable = false) // 12
    private Integer week;

    @Column(name = "year", nullable = false) // 13
    private Integer year;

    @Column(name = "ma_hop_dong") // 14
    private String maHopDong;

    @Column(name = "ma_ke_toan") // 15
    private String maKeToan;

    @Column(name = "job_name", nullable = false, columnDefinition = "TEXT") // 16
    private String jobName;

    @Column(name = "description", columnDefinition = "TEXT") // 17
    private String description;

    @Column(name = "tong_muc_dau_tu_du_kien") // 18
    private String tongMucDauTuDuKien;

    @Column(name = "hinh_thuc_dau_tu") // 19
    private String hinhThucDauTu;

    @Column(name = "muc_do_kha_thi") // 20
    private Integer mucDoKhaThi;

    @Column(name = "phan_tich_SWOOT", columnDefinition = "TEXT") // 21
    private String phanTichSwoot;

    @Column(name = "pham_vi_cung_cap") // 22
    private String phamViCungCap;

    @Column(name = "tong_gia_tri_thuc_te") // 23
    private String tongGiaTriThucTe;

    @Column(name = "so_tien_tam_ung") // 24
    private String soTienTamUng;
    @Column(name = "ke_hoach_tam_ung") // 25
    private String keHoachTamUng;

    @Column(name = "so_tien_DAC") // 26
    private String soTienDac;
    @Column(name = "hop_dong_DAC") // 27
    private String hopDongDac;
    @Column(name = "muc_tieu_DAC") // 28
    private String mucTieuDac;
    @Column(name = "thuc_te_DAC") // 29
    private String thucTeDac;

    @Column(name = "so_tien_PAC") // 30
    private String soTienPac;
    @Column(name = "hop_dong_PAC") // 31
    private String hopDongPac;
    @Column(name = "muc_tieu_PAC") // 32
    private String mucTieuPac;
    @Column(name = "thuc_te_PAC") // 33
    private String thucTePac;

    @Column(name = "so_tien_FAC") // 34
    private String soTienFac;
    @Column(name = "hop_dong_FAC") // 35
    private String hopDongFac;
    @Column(name = "muc_tieu_FAC") // 36
    private String mucTieuFac;
    @Column(name = "thuc_te_FAC") // 37
    private String thucTeFac;

    @Column(name = "general_issue", columnDefinition = "TEXT") // 38
    private String generalIssue;
    @Column(name = "solution", columnDefinition = "TEXT") // 39
    private String solution;

    @Column(name = "ke_hoach_tuan_nay", columnDefinition = "TEXT") // 40
    private String keHoachTuanNay;
    @Column(name = "ke_hoach_tuan_sau", columnDefinition = "TEXT") // 41
    private String keHoachTuanSau;
    @Column(name = "ket_qua_tuan_truoc", columnDefinition = "TEXT") // 42
    private String ketQuaTuanTruoc;
    @Column(name = "ket_qua_tuan_nay", columnDefinition = "TEXT") // 43
    private String ketQuaTuanNay;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT(4)") // 44
    private int enabled;
    @Column(name = "note", columnDefinition = "TEXT") // 45
    private String note;

    // @Column(name = "created_by") // 46
    // private String created_by;
    // @Column(name = "created_at") // 47
    // private Date created_at;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'currency_unit' (currency_unit_id)"
     * )
     */
    @JoinColumn(name = "currency_unit_id")
    private CurrencyUnitEntity currencyUnit; // 1 'project_report' sử dụng 1 'currency_unit' => hứng 1 bản ghi

    /*
     * @OneToMany(mappedBy =
     * "tên biến hứng dữ liệu từ ProjectReportEntity trong FileUploadEntity (projectReport)"
     * )
     */
    @OneToMany(mappedBy = "projectReport")
    /*
     * 1 'project_report' nằm trong nhiều 'file_upload'
     * => dùng List để hứng mảng dữ liệu
     */
    private List<FileUploadEntity> fileUploads = new ArrayList<>();

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'project' (project_id)"
     * )
     */
    @JoinColumn(name = "project_id")
    private ProjectEntity project; // 1 'project_report' sử dụng 1 'project' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'project_type' (project_type_id)"
     * )
     */
    @JoinColumn(name = "project_type_id")
    private ProjectTypeEntity projectType; // 1 'project_report' sử dụng 1 'project_type' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'project_priority' (project_priority_id)"
     * )
     */
    @JoinColumn(name = "project_priority_id")
    private ProjectPriorityEntity projectPriority; // 1 'project_report' sử dụng 1 'project_priority' => hứng 1 bản ghi

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'project_report' kết nối với bảng 'project_status' (project_status_id)"
     * )
     */
    @JoinColumn(name = "project_status_id")
    private ProjectStatusEntity projectStatus; // 1 'project_report' sử dụng 1 'project_status' => hứng 1 bản ghi

    // public Long getProjectId() {
    // return this.projectId;
    // }
    // public void setProjectId(Long projectId) {
    // this.projectId = projectId;
    // }

    // public Long getProjectTypeId() {
    // return this.projectTypeId;
    // }
    // public void setProjectTypeId(Long projectTypeId) {
    // this.projectTypeId = projectTypeId;
    // }

    // public Long getProjectPriorityId() {
    // return this.projectPriorityId;
    // }
    // public void setProjectPriorityId(Long projectPriorityId) {
    // this.projectPriorityId = projectPriorityId;
    // }

    // public Long getProjectStatusId() {
    // return this.projectStatusId;
    // }

    // public void setProjectStatusId(Long projectStatusId) {
    // this.projectStatusId = projectStatusId;
    // }

    // public Long getCurrencyUnitId() {
    // return this.currencyUnitId;
    // }
    // public void setCurrencyUnitId(Long currencyUnitId) {
    // this.currencyUnitId = currencyUnitId;
    // }

    public Integer getAmManagerId() {
        return this.amManagerId;
    }

    public void setAmManagerId(Integer amManagerId) {
        this.amManagerId = amManagerId;
    }

    public Integer getAmId() {
        return this.amId;
    }

    public void setAmId(Integer amId) {
        this.amId = amId;
    }

    public Integer getPmManagerId() {
        return this.pmManagerId;
    }

    public void setPmManagerId(Integer pmManagerId) {
        this.pmManagerId = pmManagerId;
    }

    public Integer getPmId() {
        return this.pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public Integer getWeek() {
        return this.week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMaHopDong() {
        return this.maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getMaKeToan() {
        return this.maKeToan;
    }

    public void setMaKeToan(String maKeToan) {
        this.maKeToan = maKeToan;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTongMucDauTuDuKien() {
        return this.tongMucDauTuDuKien;
    }

    public void setTongMucDauTuDuKien(String tongMucDauTuDuKien) {
        this.tongMucDauTuDuKien = tongMucDauTuDuKien;
    }

    public String getHinhThucDauTu() {
        return this.hinhThucDauTu;
    }

    public void setHinhThucDauTu(String hinhThucDauTu) {
        this.hinhThucDauTu = hinhThucDauTu;
    }

    public Integer getMucDoKhaThi() {
        return this.mucDoKhaThi;
    }

    public void setMucDoKhaThi(Integer mucDoKhaThi) {
        this.mucDoKhaThi = mucDoKhaThi;
    }

    public String getPhanTichSwoot() {
        return this.phanTichSwoot;
    }

    public void setPhanTichSwoot(String phanTichSwoot) {
        this.phanTichSwoot = phanTichSwoot;
    }

    public String getPhamViCungCap() {
        return this.phamViCungCap;
    }

    public void setPhamViCungCap(String phamViCungCap) {
        this.phamViCungCap = phamViCungCap;
    }

    public String getTongGiaTriThucTe() {
        return this.tongGiaTriThucTe;
    }

    public void setTongGiaTriThucTe(String tongGiaTriThucTe) {
        this.tongGiaTriThucTe = tongGiaTriThucTe;
    }

    public String getSoTienTamUng() {
        return this.soTienTamUng;
    }

    public void setSoTienTamUng(String soTienTamUng) {
        this.soTienTamUng = soTienTamUng;
    }

    public String getKeHoachTamUng() {
        return this.keHoachTamUng;
    }

    public void setKeHoachTamUng(String keHoachTamUng) {
        this.keHoachTamUng = keHoachTamUng;
    }

    public String getSoTienDac() {
        return this.soTienDac;
    }

    public void setSoTienDac(String soTienDac) {
        this.soTienDac = soTienDac;
    }

    public String getHopDongDac() {
        return this.hopDongDac;
    }

    public void setHopDongDac(String hopDongDac) {
        this.hopDongDac = hopDongDac;
    }

    public String getMucTieuDac() {
        return this.mucTieuDac;
    }

    public void setMucTieuDac(String mucTieuDac) {
        this.mucTieuDac = mucTieuDac;
    }

    public String getThucTeDac() {
        return this.thucTeDac;
    }

    public void setThucTeDac(String thucTeDac) {
        this.thucTeDac = thucTeDac;
    }

    public String getSoTienPac() {
        return this.soTienPac;
    }

    public void setSoTienPac(String soTienPac) {
        this.soTienPac = soTienPac;
    }

    public String getHopDongPac() {
        return this.hopDongPac;
    }

    public void setHopDongPac(String hopDongPac) {
        this.hopDongPac = hopDongPac;
    }

    public String getMucTieuPac() {
        return this.mucTieuPac;
    }

    public void setMucTieuPac(String mucTieuPac) {
        this.mucTieuPac = mucTieuPac;
    }

    public String getThucTePac() {
        return this.thucTePac;
    }

    public void setThucTePac(String thucTePac) {
        this.thucTePac = thucTePac;
    }

    public String getSoTienFac() {
        return this.soTienFac;
    }

    public void setSoTienFac(String soTienFac) {
        this.soTienFac = soTienFac;
    }

    public String getHopDongFac() {
        return this.hopDongFac;
    }

    public void setHopDongFac(String hopDongFac) {
        this.hopDongFac = hopDongFac;
    }

    public String getMucTieuFac() {
        return this.mucTieuFac;
    }

    public void setMucTieuFac(String mucTieuFac) {
        this.mucTieuFac = mucTieuFac;
    }

    public String getThucTeFac() {
        return this.thucTeFac;
    }

    public void setThucTeFac(String thucTeFac) {
        this.thucTeFac = thucTeFac;
    }

    public String getGeneralIssue() {
        return this.generalIssue;
    }

    public void setGeneralIssue(String generalIssue) {
        this.generalIssue = generalIssue;
    }

    public String getSolution() {
        return this.solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getKeHoachTuanNay() {
        return this.keHoachTuanNay;
    }

    public void setKeHoachTuanNay(String keHoachTuanNay) {
        this.keHoachTuanNay = keHoachTuanNay;
    }

    public String getKeHoachTuanSau() {
        return this.keHoachTuanSau;
    }

    public void setKeHoachTuanSau(String keHoachTuanSau) {
        this.keHoachTuanSau = keHoachTuanSau;
    }

    public String getKetQuaTuanTruoc() {
        return this.ketQuaTuanTruoc;
    }

    public void setKetQuaTuanTruoc(String ketQuaTuanTruoc) {
        this.ketQuaTuanTruoc = ketQuaTuanTruoc;
    }

    public String getKetQuaTuanNay() {
        return this.ketQuaTuanNay;
    }

    public void setKetQuaTuanNay(String ketQuaTuanNay) {
        this.ketQuaTuanNay = ketQuaTuanNay;
    }

    public int getEnabled() {
        return this.enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CurrencyUnitEntity getCurrencyUnit() {
        return this.currencyUnit;
    }

    public void setCurrencyUnit(CurrencyUnitEntity currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public List<FileUploadEntity> getFileUploads() {
        return this.fileUploads;
    }

    public void setFileUploads(List<FileUploadEntity> fileUploads) {
        this.fileUploads = fileUploads;
    }

    public ProjectEntity getProject() {
        return this.project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public ProjectTypeEntity getProjectType() {
        return this.projectType;
    }

    public void setProjectType(ProjectTypeEntity projectType) {
        this.projectType = projectType;
    }

    public ProjectPriorityEntity getProjectPriority() {
        return this.projectPriority;
    }

    public void setProjectPriority(ProjectPriorityEntity projectPriority) {
        this.projectPriority = projectPriority;
    }

    public ProjectStatusEntity getProjectStatus() {
        return this.projectStatus;
    }

    public void setProjectStatus(ProjectStatusEntity projectStatus) {
        this.projectStatus = projectStatus;
    }

}
