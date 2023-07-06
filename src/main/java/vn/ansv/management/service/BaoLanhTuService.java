package vn.ansv.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.ResponseObject;
import vn.ansv.management.repository.BaoLanhTuRepository;
import vn.ansv.management.service.Interface.IBaoLanhTu;

@Service
public class BaoLanhTuService implements IBaoLanhTu {

    @Autowired
    private BaoLanhTuRepository baoLanhTuRepository;

    @Override
    public SupportBaoLanhHopDongDTO baoLanhHopDongByReport(Long reportId) {
        try {
            return baoLanhTuRepository.findDetailTabHopDongByReport(reportId, 1);
        } catch (Exception e) {
            System.out.println("--- BaoLanhTuService - Line 22: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseObject updateDataHopDong(Long id, SupportBaoLanhHopDongDTO dataUpdate) {
        ResponseObject result = new ResponseObject();
        try {
            if (baoLanhTuRepository.countById(id) == 0) {
                result.setStatus("failed");
                result.setMessage("Đối tượng không xác định!");
                return result;
            }
            baoLanhTuRepository.updateDataBaoLanhById(id, dataUpdate.getModifiedBy(),
                    dataUpdate.getNgayPhatHanh(), dataUpdate.getNgayHetHan(), dataUpdate.getNote());

            result.setStatus("success");
            result.setMessage("Cập nhật bảo lãnh tạm ứng thành công!");
            result.setData(baoLanhTuRepository.findDetailTabHopDongById(id));
            return result;
        } catch (Exception e) {
            System.out.println("--- BaoLanhTuService Line 44: " + e.getMessage());
            result.setStatus("failed");
            result.setMessage("Có lỗi! Vui lòng thử lại sau.");
            return result;
        }
    }
}
