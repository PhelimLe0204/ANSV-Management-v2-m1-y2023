package vn.ansv.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
