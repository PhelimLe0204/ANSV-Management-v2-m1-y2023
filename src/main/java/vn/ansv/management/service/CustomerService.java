package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
