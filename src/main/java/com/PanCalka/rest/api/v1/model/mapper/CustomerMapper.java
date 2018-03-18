package com.PanCalka.rest.api.v1.model.mapper;


import com.PanCalka.rest.api.v1.model.CustomerDTO;
import com.PanCalka.rest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
