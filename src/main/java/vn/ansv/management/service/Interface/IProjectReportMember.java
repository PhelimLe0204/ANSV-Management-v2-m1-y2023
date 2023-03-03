package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.Detail.ReportDetailTabThanhVienDTO;
import vn.ansv.management.dto.member.AddMemberDTO;

public interface IProjectReportMember {
    List<ReportDetailTabThanhVienDTO> findAllMemberByReport(Long id);

    Integer addMember(AddMemberDTO dataInsert);

    Integer deleteMember(Long memberId);
}
