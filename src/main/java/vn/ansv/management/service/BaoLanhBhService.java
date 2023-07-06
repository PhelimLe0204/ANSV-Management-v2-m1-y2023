package vn.ansv.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.ResponseObject;
import vn.ansv.management.repository.BaoLanhBhRepository;
import vn.ansv.management.service.Interface.IBaoLanhBh;

@Service
public class BaoLanhBhService implements IBaoLanhBh {

    @Autowired
    private BaoLanhBhRepository baoLanhBhRepository;

    @Override
    public SupportBaoLanhHopDongDTO baoLanhHopDongByReport(Long reportId) {
        try {
            return baoLanhBhRepository.findDetailTabHopDongByReport(reportId, 1);
        } catch (Exception e) {
            System.out.println("--- BaoLanhBhService - Line 22: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseObject updateDataHopDong(Long id, SupportBaoLanhHopDongDTO dataUpdate) {
        ResponseObject result = new ResponseObject();
        try {
            if (baoLanhBhRepository.countById(id) == 0) {
                result.setMessage("NOT FOUND");
                return result;
            }
            baoLanhBhRepository.updateDataBaoLanhById(id, dataUpdate.getModifiedBy(),
                    dataUpdate.getNgayPhatHanh(), dataUpdate.getNgayHetHan(), dataUpdate.getNote());

            result.setMessage("SUCCESS");
            result.setData(baoLanhBhRepository.findDetailTabHopDongById(id));
            return result;
        } catch (Exception e) {
            System.out.println("--- BaoLanhBhService Line 31: " + e.getMessage());
            result.setMessage("EXCEPTION");
            return result;
        }
    }

}
