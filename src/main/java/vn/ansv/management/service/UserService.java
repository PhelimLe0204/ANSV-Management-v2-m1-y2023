package vn.ansv.management.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.selectOption.OptionUserDTO;
import vn.ansv.management.repository.RoleRepository;
import vn.ansv.management.repository.UserRepository;
import vn.ansv.management.service.Interface.IUser;

@Service
public class UserService implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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

    public boolean updateUserEnabled(Long id, Integer enabled) {
        try {
            userRepository.updateUserEnabled(id, enabled);
            return true;
        } catch (Exception e) {
            System.out.println("--- e ---" + e);
            return false;
        }
    }

    @Override
    public List<OptionUserDTO> findAllUserOption() {
        return userRepository.findAllUserOption();
    }

    @Override
    public UserDefineDTO userDefine(String username) {
        UserDefineDTO user_define = userRepository.defineByUsername(username);
        if (user_define == null) {
            String uid = RandomStringUtils.randomAlphanumeric(20);
            String fullname = username.replace("@ansv.vn", "");
            String password = "$2a$12$4CoPXoaizSDsFSDaIreSSu3MlqvY0QE8SyXrq7QUR7cRhYQEv9826";
            // Add new user
            userRepository.addUser("System", uid, "image_undefined.jpg", null,
                    1, fullname, password, username, 3L);
            // Add role for new user
            roleRepository.addUserRole(userRepository.findIdByUsername(username), 12L);

            return userRepository.defineByUsername(username);
        }
        return user_define;
    }

}
