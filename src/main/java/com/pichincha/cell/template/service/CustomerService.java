package com.pichincha.cell.template.service;

import com.pichincha.cell.template.domain.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    /**
     * Create a customer
     *
     * @param data Object containing new customer information
     * @return New customer created
     */
    CustomerDto createCustomer(CustomerDto data);

    /**
     * Update a customer
     *
     * @param id   ID of the customer to be upgraded
     * @param data Object containing customer information to be updated
     * @return Customer updated
     */
    CustomerDto updateCustomer(Long id, CustomerDto data);

    /**
     * Find a customer by ID
     *
     * @param id ID of the customer to be searched
     * @return Customer found
     */
    CustomerDto getCustomerById(Long id);

    /**
     * Find a customer by DNI
     *
     * @param dni DNI of the customer to be searched
     * @return Customer found
     */
    CustomerDto getCustomerByDni(String dni);

    /**
     * Find all customers
     *
     * @return All customers found
     */
    List<CustomerDto> getAllCustomers();

    /**
     * Delete a customer
     *
     * @param id ID of the client to be deleted
     * @return ID of the deleted client
     */
    Long deleteCustomerById(Long id);
}
