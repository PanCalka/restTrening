package com.PanCalka.rest.api.v1.model.mapper;

import com.PanCalka.rest.api.v1.model.CustomerDTO;
import com.PanCalka.rest.domain.Customer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void shouldMapCustomerToCustomerDTO() {
        //given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("test");

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertThat(customerDTO.getName()).isEqualTo("test");
    }
}