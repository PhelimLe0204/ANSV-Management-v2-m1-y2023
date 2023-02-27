package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;
import vn.ansv.management.repository.UserRepository;
import vn.ansv.management.service.Interface.IUser;

@Service
public class UserService implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ListAllMemberDTO> findAllByWorkCenter(Long centerId) {
        try {
            List<ListAllMemberDTO> result = userRepository.findAllByWorkCenter(centerId);
            return result;
        } catch (Exception e) {
            System.out.println("--- e ---" + e);
        }
        return null;
    }

    @Override
    public List<OptionUserDTO> findAllUserOption() {
        return userRepository.findAllUserOption();
    }

}
