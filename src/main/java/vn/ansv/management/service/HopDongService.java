package vn.ansv.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Detail.UpdateDetailTabHopDong1_DTO;
import vn.ansv.management.entity.ResponseObject;
import vn.ansv.management.repository.HopDongRepository;
import vn.ansv.management.service.Interface.IHopDong;

@Service
public class HopDongService implements IHopDong {

    @Autowired
    private HopDongRepository hopDongRepository;

    @Override
    public ResponseObject updateDataHopDong(Long reportId, Long hopDongId, UpdateDetailTabHopDong1_DTO dataUpdate) {
        ResponseObject result = new ResponseObject();
        try {
            if (hopDongRepository.countByIdAndReport(hopDongId, reportId) == 0) {
                result.setMessage("NOT FOUND");
                return result;
            }
            hopDongRepository.updateDataHopDongById(hopDongId, dataUpdate.getCreatedBy(), dataUpdate.getModifiedBy(),
                    dataUpdate.getNgayKy(), dataUpdate.getNgayHieuLuc(), dataUpdate.getNgayKetThuc(),
                    dataUpdate.getNote());

            result.setMessage("SUCCESS");
            result.setData(hopDongRepository.findDetailTabHopDongById(hopDongId));
            return result;
        } catch (Exception e) {
            System.out.println("--- HopDongService Line 24: " + e.getMessage());
            result.setMessage("EXCEPTION");
            return result;
        }
    }

}
