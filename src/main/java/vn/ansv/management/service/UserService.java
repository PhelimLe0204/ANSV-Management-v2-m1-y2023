package vn.ansv.management.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.User.UserDefineDTO;
import vn.ansv.management.dto.User.UserProfileDTO;
import vn.ansv.management.dto.member.ListAllMemberDTO;
import vn.ansv.management.dto.member.TotalReportByUserDTO;
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
            System.out.println("----- UserService.findAllByWorkCenter() ----- " + e);
        }
        return null;
    }

    public boolean updateUserEnabled(Long id, Integer enabled) {
        try {
            userRepository.updateUserEnabled(id, enabled);
            return true;
        } catch (Exception e) {
            System.out.println("----- UserService.updateUserEnabled() ----- " + e);
            return false;
        }
    }

    @Override
    public List<OptionUserDTO> findAllUserOption() {
        return userRepository.findAllUserOption();
    }

    @Override
    public UserDefineDTO userDefine(String username) {
        List<UserDefineDTO> user_define = userRepository.defineByUsername(username);
        if (user_define.isEmpty()) {
            String uid = RandomStringUtils.randomAlphanumeric(20);
            String fullname = username.replace("@ansv.vn", "");
            String password = "$2a$12$4CoPXoaizSDsFSDaIreSSu3MlqvY0QE8SyXrq7QUR7cRhYQEv9826";
            // Add new user
            userRepository.addUser("System", uid, "image_undefined.jpg", null,
                    1, fullname, password, username, 3L);
            // Add role for new user
            roleRepository.addUserRole(userRepository.findIdByUsername(username), 12L);
            user_define = userRepository.defineByUsername(username);
        }

        UserDefineDTO new_data = new UserDefineDTO();
        String userRole = new String();
        for (int i = 0; i < user_define.size(); i++) {
            userRole += user_define.get(i).getUserRole() + "___";
        }
        new_data.setId(user_define.get(0).getId());
        new_data.setAvatar(user_define.get(0).getAvatar());
        new_data.setUserRole(userRole);

        return new_data;

        // UserDefineDTO user_define = userRepository.defineByUsername(username);
        // if (user_define == null) {
        // String uid = RandomStringUtils.randomAlphanumeric(20);
        // String fullname = username.replace("@ansv.vn", "");
        // String password =
        // "$2a$12$4CoPXoaizSDsFSDaIreSSu3MlqvY0QE8SyXrq7QUR7cRhYQEv9826";
        // // Add new user
        // userRepository.addUser("System", uid, "image_undefined.jpg", null,
        // 1, fullname, password, username, 3L);
        // // Add role for new user
        // roleRepository.addUserRole(userRepository.findIdByUsername(username), 12L);

        // return userRepository.defineByUsername(username);
        // }
        // return user_define;
    }

    @Override
    public List<TotalReportByUserDTO> reportTotalManagerAm(Integer week, Integer year, String roleName) {
        try {
            List<TotalReportByUserDTO> data = userRepository.reportTotalManagerAm(week, year, roleName);
            data.forEach(item -> item.setListReport(
                    userRepository.reportLessByManagerAM(week, year, item.getId())));
            return data;
        } catch (Exception e) {
            System.out.println("----- UserService.reportTotalManagerAm() ----- " + e);
            return null;
        }
    }

    @Override
    public List<TotalReportByUserDTO> reportTotalManagerPm(Integer week, Integer year, String roleName) {
        try {
            List<TotalReportByUserDTO> data = userRepository.reportTotalManagerPm(week, year, roleName);
            data.forEach(item -> item.setListReport(
                    userRepository.reportLessByManagerPM(week, year, item.getId())));
            return data;
        } catch (Exception e) {
            System.out.println("----- UserService.reportTotalManagerPm() ----- " + e);
            return null;
        }
    }

    @Override
    public TotalReportByUserDTO reportTotalManagerAmOne(Integer week, Integer year, Long userId) {
        try {
            TotalReportByUserDTO data = userRepository.reportTotalManagerAmOne(week, year, userId);
            data.setListReport(userRepository.reportLessByManagerAM(week, year, data.getId()));
            return data;
        } catch (Exception e) {
            System.out.println("----- UserService.reportTotalManagerAmOne() ----- " + e);
            return null;
        }
    }

    @Override
    public TotalReportByUserDTO reportTotalManagerPmOne(Integer week, Integer year, Long userId) {
        try {
            TotalReportByUserDTO data = userRepository.reportTotalManagerPmOne(week, year, userId);
            data.setListReport(userRepository.reportLessByManagerPM(week, year, data.getId()));
            return data;
        } catch (Exception e) {
            System.out.println("----- UserService.reportTotalManagerPmOne() ----- " + e);
            return null;
        }
    }

    @Override
    public List<TotalReportByUserDTO> reportTotalAM(Integer week, Integer year, String roleName) {
        try {
            List<TotalReportByUserDTO> data = userRepository.reportTotalAM(week, year, roleName);
            data.forEach(item -> item.setListReport(
                    userRepository.reportLessByAM(week, year, item.getId())));
            return data;
        } catch (Exception e) {
            System.out.println("----- UserService.reportTotalAM() ----- " + e);
            return null;
        }
    }

    @Override
    public List<TotalReportByUserDTO> reportTotalPM(Integer week, Integer year, String roleName) {
        try {
            List<TotalReportByUserDTO> data = userRepository.reportTotalPM(week, year, roleName);
            data.forEach(item -> item.setListReport(
                    userRepository.reportLessByPM(week, year, item.getId())));
            return data;
        } catch (Exception e) {
            System.out.println("----- UserService.reportTotalPM() ----- " + e);
            return null;
        }
    }

    @Override
    public UserProfileDTO findUserProfileById(Long userId) {
        try {
            return userRepository.findUserProfileById(userId);
        } catch (Exception e) {
            System.out.println("----- UserService.findUserProfileById() ----- " + e);
            return null;
        }
    }

}
