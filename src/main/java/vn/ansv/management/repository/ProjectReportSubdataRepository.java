package vn.ansv.management.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.ansv.management.entity.ProjectReportSubdataEntity;

@Repository
public interface ProjectReportSubdataRepository extends JpaRepository<ProjectReportSubdataEntity, Long> {
    // Find project_report_subdata by id
    @Query(value = "SELECT COUNT(prs.id) FROM project_report_subdata AS prs "
            + "WHERE prs.project_report_id = :projectReportId", nativeQuery = true)
    Integer countReportSubdataByReportId(@Param("projectReportId") Long projectReportId);

    // project_report_subdata: add new
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO project_report_subdata (project_report_id, "
            + "so_tien_dac_2, hop_dong_dac_2, muc_tieu_dac_2, thuc_te_dac_2, note_dac_2, "
            + "so_tien_dac_3, hop_dong_dac_3, muc_tieu_dac_3, thuc_te_dac_3, note_dac_3, "
            + "so_tien_dac_4, hop_dong_dac_4, muc_tieu_dac_4, thuc_te_dac_4, note_dac_4, "
            + "so_tien_dac_5, hop_dong_dac_5, muc_tieu_dac_5, thuc_te_dac_5, note_dac_5, "
            + "so_tien_pac_2, hop_dong_pac_2, muc_tieu_pac_2, thuc_te_pac_2, note_pac_2, "
            + "so_tien_pac_3, hop_dong_pac_3, muc_tieu_pac_3, thuc_te_pac_3, note_pac_3, "
            + "so_tien_pac_4, hop_dong_pac_4, muc_tieu_pac_4, thuc_te_pac_4, note_pac_4, "
            + "so_tien_pac_5, hop_dong_pac_5, muc_tieu_pac_5, thuc_te_pac_5, note_pac_5, "
            + "so_tien_fac_2, hop_dong_fac_2, muc_tieu_fac_2, thuc_te_fac_2, note_fac_2, "
            + "so_tien_fac_3, hop_dong_fac_3, muc_tieu_fac_3, thuc_te_fac_3, note_fac_3, "
            + "so_tien_fac_4, hop_dong_fac_4, muc_tieu_fac_4, thuc_te_fac_4, note_fac_4, "
            + "so_tien_fac_5, hop_dong_fac_5, muc_tieu_fac_5, thuc_te_fac_5, note_fac_5 "
            + ") VALUES (:projectReportId, "
            + ":soTienDac2, :hopDongDac2, :mucTieuDac2, :thucTeDac2, :noteDac2, "
            + ":soTienDac3, :hopDongDac3, :mucTieuDac3, :thucTeDac3, :noteDac3, "
            + ":soTienDac4, :hopDongDac4, :mucTieuDac4, :thucTeDac4, :noteDac4, "
            + ":soTienDac5, :hopDongDac5, :mucTieuDac5, :thucTeDac5, :noteDac5, "
            + ":soTienPac2, :hopDongPac2, :mucTieuPac2, :thucTePac2, :notePac2, "
            + ":soTienPac3, :hopDongPac3, :mucTieuPac3, :thucTePac3, :notePac3, "
            + ":soTienPac4, :hopDongPac4, :mucTieuPac4, :thucTePac4, :notePac4, "
            + ":soTienPac5, :hopDongPac5, :mucTieuPac5, :thucTePac5, :notePac5, "
            + ":soTienFac2, :hopDongFac2, :mucTieuFac2, :thucTeFac2, :noteFac2, "
            + ":soTienFac3, :hopDongFac3, :mucTieuFac3, :thucTeFac3, :noteFac3, "
            + ":soTienFac4, :hopDongFac4, :mucTieuFac4, :thucTeFac4, :noteFac4, "
            + ":soTienFac5, :hopDongFac5, :mucTieuFac5, :thucTeFac5, :noteFac5)", nativeQuery = true)
    void addNewReportSubdata(@Param("projectReportId") Long projectReportId,
            @Param("soTienDac2") String soTienDac2, @Param("hopDongDac2") String hopDongDac2,
            @Param("mucTieuDac2") String mucTieuDac2, @Param("thucTeDac2") String thucTeDac2,
            @Param("noteDac2") String noteDac2,
            @Param("soTienDac3") String soTienDac3, @Param("hopDongDac3") String hopDongDac3,
            @Param("mucTieuDac3") String mucTieuDac3, @Param("thucTeDac3") String thucTeDac3,
            @Param("noteDac3") String noteDac3,
            @Param("soTienDac4") String soTienDac4, @Param("hopDongDac4") String hopDongDac4,
            @Param("mucTieuDac4") String mucTieuDac4, @Param("thucTeDac4") String thucTeDac4,
            @Param("noteDac4") String noteDac4,
            @Param("soTienDac5") String soTienDac5, @Param("hopDongDac5") String hopDongDac5,
            @Param("mucTieuDac5") String mucTieuDac5, @Param("thucTeDac5") String thucTeDac5,
            @Param("noteDac5") String noteDac5,

            @Param("soTienPac2") String soTienPac2, @Param("hopDongPac2") String hopDongPac2,
            @Param("mucTieuPac2") String mucTieuPac2, @Param("thucTePac2") String thucTePac2,
            @Param("notePac2") String notePac2,
            @Param("soTienPac3") String soTienPac3, @Param("hopDongPac3") String hopDongPac3,
            @Param("mucTieuPac3") String mucTieuPac3, @Param("thucTePac3") String thucTePac3,
            @Param("notePac3") String notePac3,
            @Param("soTienPac4") String soTienPac4, @Param("hopDongPac4") String hopDongPac4,
            @Param("mucTieuPac4") String mucTieuPac4, @Param("thucTePac4") String thucTePac4,
            @Param("notePac4") String notePac4,
            @Param("soTienPac5") String soTienPac5, @Param("hopDongPac5") String hopDongPac5,
            @Param("mucTieuPac5") String mucTieuPac5, @Param("thucTePac5") String thucTePac5,
            @Param("notePac5") String notePac5,

            @Param("soTienFac2") String soTienFac2, @Param("hopDongFac2") String hopDongFac2,
            @Param("mucTieuFac2") String mucTieuFac2, @Param("thucTeFac2") String thucTeFac2,
            @Param("noteFac2") String noteFac2,
            @Param("soTienFac3") String soTienFac3, @Param("hopDongFac3") String hopDongFac3,
            @Param("mucTieuFac3") String mucTieuFac3, @Param("thucTeFac3") String thucTeFac3,
            @Param("noteFac3") String noteFac3,
            @Param("soTienFac4") String soTienFac4, @Param("hopDongFac4") String hopDongFac4,
            @Param("mucTieuFac4") String mucTieuFac4, @Param("thucTeFac4") String thucTeFac4,
            @Param("noteFac4") String noteFac4,
            @Param("soTienFac5") String soTienFac5, @Param("hopDongFac5") String hopDongFac5,
            @Param("mucTieuFac5") String mucTieuFac5, @Param("thucTeFac5") String thucTeFac5,
            @Param("noteFac5") String noteFac5);

    /*
     * -----------------------------------------------------------
     * Cập nhật chi tiết báo cáo dự án (Subdata - Thông tin phụ)
     * View: Detail (tab Chi phí & thời gian)
     * -----------------------------------------------------------
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE project_report_subdata AS prs SET "
            + "prs.so_tien_dac_2 = :soTienDac2, prs.hop_dong_dac_2 = :hopDongDac2, prs.muc_tieu_dac_2 = :mucTieuDac2, "
            + "prs.thuc_te_dac_2 = :thucTeDac2, prs.note_dac_2 = :noteDac2, "
            + "prs.so_tien_dac_3 = :soTienDac3, prs.hop_dong_dac_3 = :hopDongDac3, prs.muc_tieu_dac_3 = :mucTieuDac3, "
            + "prs.thuc_te_dac_3 = :thucTeDac3, prs.note_dac_3 = :noteDac3, "
            + "prs.so_tien_dac_4 = :soTienDac4, prs.hop_dong_dac_4 = :hopDongDac4, prs.muc_tieu_dac_4 = :mucTieuDac4, "
            + "prs.thuc_te_dac_4 = :thucTeDac4, prs.note_dac_4 = :noteDac4, "
            + "prs.so_tien_dac_5 = :soTienDac5, prs.hop_dong_dac_5 = :hopDongDac5, prs.muc_tieu_dac_5 = :mucTieuDac5, "
            + "prs.thuc_te_dac_5 = :thucTeDac5, prs.note_dac_5 = :noteDac5, "

            + "prs.so_tien_pac_2 = :soTienPac2, prs.hop_dong_pac_2 = :hopDongPac2, prs.muc_tieu_pac_2 = :mucTieuPac2, "
            + "prs.thuc_te_pac_2 = :thucTePac2, prs.note_pac_2 = :notePac2, "
            + "prs.so_tien_pac_3 = :soTienPac3, prs.hop_dong_pac_3 = :hopDongPac3, prs.muc_tieu_pac_3 = :mucTieuPac3, "
            + "prs.thuc_te_pac_3 = :thucTePac3, prs.note_pac_3 = :notePac3, "
            + "prs.so_tien_pac_4 = :soTienPac4, prs.hop_dong_pac_4 = :hopDongPac4, prs.muc_tieu_pac_4 = :mucTieuPac4, "
            + "prs.thuc_te_pac_4 = :thucTePac4, prs.note_pac_4 = :notePac4, "
            + "prs.so_tien_pac_5 = :soTienPac5, prs.hop_dong_pac_5 = :hopDongPac5, prs.muc_tieu_pac_5 = :mucTieuPac5, "
            + "prs.thuc_te_pac_5 = :thucTePac5, prs.note_pac_5 = :notePac5, "

            + "prs.so_tien_fac_2 = :soTienFac2, prs.hop_dong_fac_2 = :hopDongFac2, prs.muc_tieu_fac_2 = :mucTieuFac2, "
            + "prs.thuc_te_fac_2 = :thucTeFac2, prs.note_fac_2 = :noteFac2, "
            + "prs.so_tien_fac_3 = :soTienFac3, prs.hop_dong_fac_3 = :hopDongFac3, prs.muc_tieu_fac_3 = :mucTieuFac3, "
            + "prs.thuc_te_fac_3 = :thucTeFac3, prs.note_fac_3 = :noteFac3, "
            + "prs.so_tien_fac_4 = :soTienFac4, prs.hop_dong_fac_4 = :hopDongFac4, prs.muc_tieu_fac_4 = :mucTieuFac4, "
            + "prs.thuc_te_fac_4 = :thucTeFac4, prs.note_fac_4 = :noteFac4, "
            + "prs.so_tien_fac_5 = :soTienFac5, prs.hop_dong_fac_5 = :hopDongFac5, prs.muc_tieu_fac_5 = :mucTieuFac5, "
            + "prs.thuc_te_fac_5 = :thucTeFac5, prs.note_fac_5 = :noteFac5 "
            + "WHERE prs.project_report_id = :projectReportId", nativeQuery = true)
    void updateDetailTabCptgSubdata(
            @Param("projectReportId") Long projectReportId,
            @Param("soTienDac2") String soTienDac2, @Param("hopDongDac2") String hopDongDac2,
            @Param("mucTieuDac2") String mucTieuDac2, @Param("thucTeDac2") String thucTeDac2,
            @Param("noteDac2") String noteDac2,
            @Param("soTienDac3") String soTienDac3, @Param("hopDongDac3") String hopDongDac3,
            @Param("mucTieuDac3") String mucTieuDac3, @Param("thucTeDac3") String thucTeDac3,
            @Param("noteDac3") String noteDac3,
            @Param("soTienDac4") String soTienDac4, @Param("hopDongDac4") String hopDongDac4,
            @Param("mucTieuDac4") String mucTieuDac4, @Param("thucTeDac4") String thucTeDac4,
            @Param("noteDac4") String noteDac4,
            @Param("soTienDac5") String soTienDac5, @Param("hopDongDac5") String hopDongDac5,
            @Param("mucTieuDac5") String mucTieuDac5, @Param("thucTeDac5") String thucTeDac5,
            @Param("noteDac5") String noteDac5,

            @Param("soTienPac2") String soTienPac2, @Param("hopDongPac2") String hopDongPac2,
            @Param("mucTieuPac2") String mucTieuPac2, @Param("thucTePac2") String thucTePac2,
            @Param("notePac2") String notePac2,
            @Param("soTienPac3") String soTienPac3, @Param("hopDongPac3") String hopDongPac3,
            @Param("mucTieuPac3") String mucTieuPac3, @Param("thucTePac3") String thucTePac3,
            @Param("notePac3") String notePac3,
            @Param("soTienPac4") String soTienPac4, @Param("hopDongPac4") String hopDongPac4,
            @Param("mucTieuPac4") String mucTieuPac4, @Param("thucTePac4") String thucTePac4,
            @Param("notePac4") String notePac4,
            @Param("soTienPac5") String soTienPac5, @Param("hopDongPac5") String hopDongPac5,
            @Param("mucTieuPac5") String mucTieuPac5, @Param("thucTePac5") String thucTePac5,
            @Param("notePac5") String notePac5,

            @Param("soTienFac2") String soTienFac2, @Param("hopDongFac2") String hopDongFac2,
            @Param("mucTieuFac2") String mucTieuFac2, @Param("thucTeFac2") String thucTeFac2,
            @Param("noteFac2") String noteFac2,
            @Param("soTienFac3") String soTienFac3, @Param("hopDongFac3") String hopDongFac3,
            @Param("mucTieuFac3") String mucTieuFac3, @Param("thucTeFac3") String thucTeFac3,
            @Param("noteFac3") String noteFac3,
            @Param("soTienFac4") String soTienFac4, @Param("hopDongFac4") String hopDongFac4,
            @Param("mucTieuFac4") String mucTieuFac4, @Param("thucTeFac4") String thucTeFac4,
            @Param("noteFac4") String noteFac4,
            @Param("soTienFac5") String soTienFac5, @Param("hopDongFac5") String hopDongFac5,
            @Param("mucTieuFac5") String mucTieuFac5, @Param("thucTeFac5") String thucTeFac5,
            @Param("noteFac5") String noteFac5);
}
