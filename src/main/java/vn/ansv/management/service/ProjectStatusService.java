package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.selectOption.OptionProjectStatusDTO;
import vn.ansv.management.repository.ProjectStatusRepository;
import vn.ansv.management.service.Interface.IProjectStatus;

@Service
public class ProjectStatusService implements IProjectStatus {
    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @Override
    public List<OptionProjectStatusDTO> findAllOption() {
        return projectStatusRepository.findAllOption();
    }
}
