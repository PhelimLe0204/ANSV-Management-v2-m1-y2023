package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.selectOption.OptionProjectPriorityDTO;
import vn.ansv.management.repository.ProjectPriorityRepository;
import vn.ansv.management.service.Interface.IProjectPriority;

@Service
public class ProjectPriorityService implements IProjectPriority {
    @Autowired
    private ProjectPriorityRepository projectPriorityRepository;

    @Override
    public List<OptionProjectPriorityDTO> findAllOption() {
        return projectPriorityRepository.findAllOption();
    }
}
