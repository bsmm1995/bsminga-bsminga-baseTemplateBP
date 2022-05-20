package com.pichincha.cell.template.controller;

import com.pichincha.cell.template.domain.Customer;
import com.pichincha.cell.template.domain.dto.CustomerDto;
import com.pichincha.cell.template.repository.CustomerRepository;
import com.pichincha.cell.template.service.CustomerService;
import com.pichincha.cell.template.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)//nos permita mockear las otras clases como utilizar Mockito
class CustomerControllerTest {

    //solo este obj se mockeara
    private CustomerRepository customerRepositoryMock;
    private CustomerController customerController;
    private BindingResult bindingResultMock;


    @BeforeEach
    void setUp() {//este método siempre se ejecuta antes de cada test,

        //asignación de mocks manualmente
        customerRepositoryMock = Mockito.mock(CustomerRepository.class);//se mockea el obj repository
        bindingResultMock = Mockito.mock(BindingResult.class);//se mockea el obj BindingResult
        customerController = new CustomerController(new CustomerServiceImpl(customerRepositoryMock));
    }

    @Test
    void getCustomer() {
        Long id = 1L;

        Customer customer = new Customer();
        customer.setId(id);
        customer.setDni("1721678843");
        customer.setName("Santiago");
        customer.setLastname("Gómez");

        when(customerRepositoryMock.findById(id)).thenReturn(Optional.of(customer));

        ResponseEntity<CustomerDto> result = customerController.getCustomer(id);
        CustomerDto customerDto = result.getBody();

        assertNotNull(customerDto);
        assertEquals(id, customerDto.getId());

    }

    @Test
    void createCustomer() {
        Long id = 20L;

        CustomerDto customerDto = new CustomerDto();
        customerDto.setDni("1721678843");
        customerDto.setName("Santiago");
        customerDto.setLastname("Gómez");

        Customer customer = new Customer();
        customer.setId(id);
        customer.setDni("1721678843");
        customer.setName("Santiago");
        customer.setLastname("Gómez");

        when(customerRepositoryMock.save(any())).thenReturn(customer);
        when(bindingResultMock.hasErrors()).thenReturn(Boolean.FALSE);

        ResponseEntity<CustomerDto> result = customerController.createCustomer(customerDto, bindingResultMock);
        CustomerDto customerSaved = result.getBody();

        assertNotNull(customerSaved);
        assertNotNull(customerSaved.getId());



    }
}