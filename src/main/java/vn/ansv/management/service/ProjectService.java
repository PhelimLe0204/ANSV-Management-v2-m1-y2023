package vn.ansv.management.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.AddNewProjectDTO;
import vn.ansv.management.dto.selectOption.OptionProjectDTO;
import vn.ansv.management.repository.BaoLanhBhRepository;
import vn.ansv.management.repository.BaoLanhThhdRepository;
import vn.ansv.management.repository.BaoLanhTuRepository;
import vn.ansv.management.repository.ProjectRepository;
import vn.ansv.management.service.Interface.IProject;

@Service
public class ProjectService implements IProject {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BaoLanhThhdRepository baoLanhThhdRepository;

    @Autowired
    private BaoLanhTuRepository baoLanhTuRepository;

    @Autowired
    private BaoLanhBhRepository baoLanhBhRepository;

    @Override
    public List<OptionProjectDTO> findAllSelectOption() {
        return projectRepository.findAllSelectOption();
    }

    @Override
    public Long getCustomerIdById(Long id) {
        return projectRepository.findCustomerIdById(id);
    }

    @Override
    public String addNewProject(AddNewProjectDTO dataInsert) {
        try {
            int count = projectRepository.checkIssetByProjectName(dataInsert.getProjectName());
            if (count > 0) {
                return "duplicate";
            }
            String uid = RandomStringUtils.randomAlphanumeric(20);
            projectRepository.addNewProject(dataInsert.getCreatedBy(), uid, dataInsert.getProjectDescription(), 1,
                    dataInsert.getProjectName(), dataInsert.getProjectCustomer());
            Long idProject = projectRepository.findIdByProjectName(dataInsert.getProjectName());
            baoLanhThhdRepository.addNewBLTHHD(idProject); // Thêm mới bảo lãnh thực hiện hợp đồng
            baoLanhTuRepository.addNewBLTU(idProject); // Thêm mới bảo lãnh tạm ứng
            baoLanhBhRepository.addNewBLBH(idProject); // Thêm mới bảo lãnh bảo hành
            return "true";
        } catch (Exception e) {
            System.out.println("--- ProjectService - line 33: " + e.getMessage());
            return "false";
        }
    }
}
