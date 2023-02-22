package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.member.ListAllMemberDTO;

public interface IUser {
    List<ListAllMemberDTO> findAllByWorkCenter(Long centerId);
}
