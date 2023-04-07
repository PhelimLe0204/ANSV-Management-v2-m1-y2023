package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.Customer.AddNewCustomerDTO;
import vn.ansv.management.dto.Customer.ListCustomerDTO;
import vn.ansv.management.dto.Customer.UpdateCustomerDTO;
import vn.ansv.management.dto.selectOption.OptionCustomerDTO;

public interface ICustomer {
    List<OptionCustomerDTO> findAllSelectOption();

    List<ListCustomerDTO> findAllList();

    ListCustomerDTO findDetailById(Long id);

    Integer addCustomer(AddNewCustomerDTO addNewCustomerDTO);

    Boolean updateCustomer(UpdateCustomerDTO updateCustomerDTO);
}
