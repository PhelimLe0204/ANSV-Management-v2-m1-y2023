package vn.ansv.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.ResponseObject;
import vn.ansv.management.repository.BaoLanhThhdRepository;
import vn.ansv.management.service.Interface.IBaoLanhThhd;

@Service
public class BaoLanhThhdService implements IBaoLanhThhd {
    @Autowired
    private BaoLanhThhdRepository baoLanhThhdRepository;

    @Override
    public ResponseObject updateDataHopDong(Long id, SupportBaoLanhHopDongDTO dataUpdate) {
        ResponseObject result = new ResponseObject();
        try {
            if (baoLanhThhdRepository.countById(id) == 0) {
                result.setStatus("failed");
                result.setMessage("Đối tượng không xác định!");
                return result;
            }
            baoLanhThhdRepository.updateDataBaoLanhById(id, dataUpdate.getModifiedBy(),
                    dataUpdate.getNgayPhatHanh(), dataUpdate.getNgayHetHan(), dataUpdate.getNote());

            result.setStatus("success");
            result.setMessage("Cập nhật bảo lãnh thực hiện hợp đồng thành công!");
            result.setData(baoLanhThhdRepository.findDetailTabHopDongById(id));
            return result;
        } catch (Exception e) {
            System.out.println("--- BaoLanhThhdService Line 31: " + e.getMessage());
            result.setStatus("failed");
            result.setMessage("Có lỗi! Vui lòng thử lại sau.");
            return result;
        }
    }

    @Override
    public SupportBaoLanhHopDongDTO baoLanhHopDongByReport(Long reportId) {
        try {
            return baoLanhThhdRepository.findDetailTabHopDongByReport(reportId, 1);
        } catch (Exception e) {
            System.out.println("--- BaoLanhThhdService - Line 42: " + e.getMessage());
            return null;
        }
    }
}
