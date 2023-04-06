package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.Customer.AddNewCustomerDTO;
import vn.ansv.management.dto.Customer.ListCustomerDTO;
import vn.ansv.management.dto.selectOption.OptionCustomerDTO;

public interface ICustomer {
    List<OptionCustomerDTO> findAllSelectOption();

    List<ListCustomerDTO> findAllList();

    Integer addCustomer(AddNewCustomerDTO addNewCustomerDTO);
}
