package vn.ansv.management.service.Interface;

import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.ResponseObject;

public interface IBaoLanhTu {
    SupportBaoLanhHopDongDTO baoLanhHopDongByReport(Long reportId);

    ResponseObject updateDataHopDong(Long id, SupportBaoLanhHopDongDTO dataUpdate);
}
