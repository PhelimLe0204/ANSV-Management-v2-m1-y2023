package vn.ansv.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/* ===== BaoLanhBhRepository.findDetailTabHopDong() ===== */
@NamedNativeQuery(name = "BaoLanhBhEntity.findDetailTabHopDongByReport", query = "SELECT "
        + "blbh.id, blbh.ngay_phat_hanh AS ngayPhatHanh, blbh.ngay_het_han AS ngayHetHan, blbh.note "
        + "FROM bao_lanh_bh AS blbh "
        + "INNER JOIN project AS p ON blbh.project_id = p.id "
        + "INNER JOIN project_report AS pr ON p.id = pr.project_id "
        + "WHERE pr.id = :projectReportId AND pr.enabled = :enabled "
        + "ORDER BY blbh.modified_at DESC LIMIT 1", resultSetMapping = "Mapping.SupportBaoLanhHopDongDTO")

/* ===== BaoLanhBhRepository.findDetailTabHopDongById() ===== */
@NamedNativeQuery(name = "BaoLanhBhEntity.findDetailTabHopDongById", query = "SELECT "
        + "blbh.id, blbh.ngay_phat_hanh AS ngayPhatHanh, blbh.ngay_het_han AS ngayHetHan, blbh.note "
        + "FROM bao_lanh_bh AS blbh "
        + "WHERE blbh.id = :id", resultSetMapping = "Mapping.SupportBaoLanhHopDongDTO")

/*
 * ===== Set mapping: SupportBaoLanhHopDongDTO =====
 * Mapping already isset in BaoLanhThhdEntity
 */

@Entity
@Table(name = "bao_lanh_bh")
public class BaoLanhBhEntity extends BaseEntity {
    @Column(name = "ngay_phat_hanh")
    private String ngayPhatHanh;

    @Column(name = "ngay_het_han")
    private String ngayHetHan;

    @Column(name = "note")
    private String note;

    @ManyToOne
    /*
     * @JoinColumn(name =
     * "khóa ngoại trong bảng 'bao_lanh_bh' kết nối với bảng 'project' (project_id)"
     * )
     */
    @JoinColumn(name = "project_id")
    private ProjectEntity project; // nhiều 'bao_lanh_bh' thuộc 1 'project' => hứng 1 bản ghi

    public String getNgayPhatHanh() {
        return this.ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public String getNgayHetHan() {
        return this.ngayHetHan;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ProjectEntity getProject() {
        return this.project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

}