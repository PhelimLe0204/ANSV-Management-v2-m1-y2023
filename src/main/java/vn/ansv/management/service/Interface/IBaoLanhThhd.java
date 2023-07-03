package vn.ansv.management.service.Interface;

import vn.ansv.management.dto.Detail.SupportBaoLanhHopDongDTO;
import vn.ansv.management.entity.ResponseObject;

public interface IBaoLanhThhd {
    ResponseObject updateDataHopDong(Long id, SupportBaoLanhHopDongDTO dataUpdate);
}
