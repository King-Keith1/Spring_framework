package tut5.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tut5.domain.Customer;
import tut5.web.model.CustomerDto;

/**
 * Created by jt on 2019-05-25.
 */
@Mapper
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Customer customerDtoToCustomer(CustomerDto dto);

    CustomerDto customerToCustomerDto(Customer customer);
}
