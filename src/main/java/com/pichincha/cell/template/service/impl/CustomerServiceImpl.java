package com.pichincha.cell.template.service.impl;

import com.pichincha.cell.template.domain.Customer;
import com.pichincha.cell.template.domain.dto.CustomerDto;
import com.pichincha.cell.template.repository.CustomerRepository;
import com.pichincha.cell.template.service.CustomerService;
import com.pichincha.cell.template.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto createCustomer(CustomerDto data) {
        Customer customer = Mapper.modelMapper().map(data, Customer.class);
        return Mapper.modelMapper().map(customerRepository.save(customer), CustomerDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto data) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            resourceNotFound(id);
        }
        customerOptional.get().setLastname(data.getLastname());
        customerOptional.get().setName(data.getName());
        return Mapper.modelMapper().map(customerRepository.save(customerOptional.get()), CustomerDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            resourceNotFound(id);
        }
        return Mapper.modelMapper().map(customerRepository.save(customerOptional.get()), CustomerDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto getCustomerByDni(String dni) {
        Optional<Customer> customerOptional = customerRepository.findByDni(dni);
        if (customerOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Customer with DNI %s not found", dni));
        }
        return Mapper.modelMapper().map(customerRepository.save(customerOptional.get()), CustomerDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> list = new ArrayList<>();
        for (Customer element : customerRepository.findAll()) {
            list.add(Mapper.modelMapper().map(element, CustomerDto.class));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long deleteCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            resourceNotFound(id);
        }
        customerRepository.deleteById(id);
        return id;
    }

    private void resourceNotFound(Long id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Customer with id %d not found", id));
    }
}
