package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.member.TotalReportByUserDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;

public interface IUser {
    List<ListAllMemberDTO> findAllByWorkCenter(Long centerId);

    List<OptionUserDTO> findAllUserOption();

    UserDefineDTO userDefine(String username);

    List<TotalReportByUserDTO> reportTotalManagerAm(Integer week, Integer year, String roleName);

    List<TotalReportByUserDTO> reportTotalManagerPm(Integer week, Integer year, String roleName);

    TotalReportByUserDTO reportTotalManagerAmOne(Integer week, Integer year, Long userId);

    TotalReportByUserDTO reportTotalManagerPmOne(Integer week, Integer year, Long userId);

    List<TotalReportByUserDTO> reportTotalAM(Integer week, Integer year, String roleName);

    List<TotalReportByUserDTO> reportTotalPM(Integer week, Integer year, String roleName);
}
