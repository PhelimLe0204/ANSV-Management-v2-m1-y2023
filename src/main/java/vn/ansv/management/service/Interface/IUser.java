package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;

public interface IUser {
    List<ListAllMemberDTO> findAllByWorkCenter(Long centerId);

    List<OptionUserDTO> findAllUserOption();

    UserDefineDTO userDefine(String username);
}
