package com.PanCalka.rest.services;

import com.PanCalka.rest.api.v1.model.CustomerDTO;
import com.PanCalka.rest.api.v1.model.mapper.CustomerMapper;
import com.PanCalka.rest.domain.Customer;
import com.PanCalka.rest.repositories.CategoryRepository;
import com.PanCalka.rest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerMapper customerMapper;
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                        customerDTO.setCustomerUrl("api/v1/customer/" + customer.getId());
                        return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDto(customerMapper.customerDtoToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDto(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
        return returnDto;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturnDto(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if(customerDTO.getFirstName() !=null){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(customerDTO.getName() !=null){
                customer.setName(customerDTO.getName());
            }

            CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            returnDto.setCustomerUrl("/api/v1/customer/" + id);
            return returnDto;
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteCustomerById(Long id){
         customerRepository.deleteById(id);
    }


}
