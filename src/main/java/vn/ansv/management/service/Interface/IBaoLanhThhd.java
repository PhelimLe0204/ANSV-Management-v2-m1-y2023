package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.Detail.DanhSachGiaHanDTO;
import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.ResponseObject;

public interface IBaoLanhThhd {
    SupportBaoLanhHopDongDTO baoLanhHopDongByReport(Long reportId);

    ResponseObject updateDataHopDong(Long id, SupportBaoLanhHopDongDTO dataUpdate);

    List<DanhSachGiaHanDTO> listGiaHan(Long reportId, Integer enabled);
}
