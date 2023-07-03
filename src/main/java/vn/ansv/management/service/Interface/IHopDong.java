package vn.ansv.management.service.Interface;

import vn.ansv.management.dto.Detail.UpdateDetailTabHopDong1_DTO;
import vn.ansv.management.entity.ResponseObject;

public interface IHopDong {
    ResponseObject updateDataHopDong(Long reportId, Long hopDongId, UpdateDetailTabHopDong1_DTO dataUpdate);
}
