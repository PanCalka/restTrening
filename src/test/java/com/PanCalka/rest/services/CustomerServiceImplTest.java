package com.PanCalka.rest.services;

import com.PanCalka.rest.api.v1.model.CustomerDTO;
import com.PanCalka.rest.api.v1.model.mapper.CustomerMapper;
import com.PanCalka.rest.domain.Customer;
import com.PanCalka.rest.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void shouldGetAllCustomers() {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

        //then
        assertThat(allCustomers).hasSize(customers.size());
    }

    @Test
    public void shouldReturnCustomerById() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Tom");
        customer.setName("Joe");
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));

        //expected
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertThat(customerDTO.getFirstName()).isEqualTo("Tom");
    }

    @Test
    public void shouldCreateNewCustomer() throws Exception {

        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Tom");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setName(customerDTO.getName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedCustomerDto = customerService.createNewCustomer(customerDTO);

        //then
        assertThat(savedCustomerDto.getFirstName()).isEqualTo(customerDTO.getFirstName());
        assertThat("/api/v1/customer/1").isEqualTo(savedCustomerDto.getCustomerUrl());
    }
}