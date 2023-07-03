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
                result.setMessage("NOT FOUND");
                return result;
            }
            baoLanhThhdRepository.updateDataBaoLanhById(id, dataUpdate.getModifiedBy(),
                    dataUpdate.getNgayPhatHanh(), dataUpdate.getNgayHetHan(), dataUpdate.getNote());

            result.setMessage("SUCCESS");
            result.setData(baoLanhThhdRepository.findDetailTabHopDongById(id));
            return result;
        } catch (Exception e) {
            System.out.println("--- BaoLanhThhdService Line 31: " + e.getMessage());
            result.setMessage("EXCEPTION");
            return result;
        }
    }
}
