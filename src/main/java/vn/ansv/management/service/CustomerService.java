package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ansv.management.dto.Customer.AddNewCustomerDTO;
import vn.ansv.management.dto.Customer.ListCustomerDTO;
import vn.ansv.management.dto.selectOption.OptionCustomerDTO;
import vn.ansv.management.repository.CustomerRepository;
import vn.ansv.management.service.Interface.ICustomer;

@Service
public class CustomerService implements ICustomer {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<OptionCustomerDTO> findAllSelectOption() {
        return customerRepository.findAllSelectOption();
    }

    @Override
    public List<ListCustomerDTO> findAllList() {
        return customerRepository.findAllList();
    }

    public boolean updateCustomerEnabled(Long id, Integer enabled) {
        try {
            customerRepository.updateEnabled(id, enabled);
            return true;
        } catch (Exception e) {
            System.out.println("--- e ---" + e);
            return false;
        }
    }

    @Override
    public Integer addCustomer(AddNewCustomerDTO addNewCustomerDTO) {
        // 0 - Failed, 1 - Success, 2 - Duplicate customer's name duplicate
        try {
            Integer count = customerRepository.countByCustomerName(addNewCustomerDTO.getCustomerName());
            if (count > 0) {
                return 2; // Duplicate customer's name duplicate
            }
            customerRepository.addCustomer(addNewCustomerDTO.getCreatedBy(), addNewCustomerDTO.getUid(),
                    addNewCustomerDTO.getAvatarName(), addNewCustomerDTO.getCustomerName(),
                    addNewCustomerDTO.getEnabled());
            return 1; // Success
        } catch (Exception e) {
            System.out.println("--- e ---" + e);
            return 0; // Failed
        }
    }

    @Override
    public ListCustomerDTO findDetailById(Long id) {
        try {
            ListCustomerDTO data = customerRepository.findDetailById(id);
            return data;
        } catch (Exception e) {
            System.out.println("--- e ---" + e);
            return null;
        }
    }

}
