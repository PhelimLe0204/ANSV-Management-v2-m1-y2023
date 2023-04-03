package vn.ansv.management.service.Interface;

import java.util.List;

import vn.ansv.management.dto.Customer.ListCustomerDTO;
import vn.ansv.management.dto.selectOption.OptionCustomerDTO;

public interface ICustomer {
    List<OptionCustomerDTO> findAllSelectOption();

    List<ListCustomerDTO> findAllList();
}
