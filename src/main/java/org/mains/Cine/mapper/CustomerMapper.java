package org.mains.Cine.mapper;


import org.mains.Cine.domain.Customer;
import org.mains.Cine.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper extends EntityMapper<CustomerDto, Customer>{

}
