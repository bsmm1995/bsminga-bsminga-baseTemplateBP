package com.pichincha.cell.template.controller;

import com.pichincha.cell.template.domain.dto.CustomerDto;
import com.pichincha.cell.template.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * Endpoints that manage the customers
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Find a customer by ID
     *
     * @param id ID of the customer to be searched
     * @return Customer found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        log.info("Endpoint to get a customer by ID: id=" + id);
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    /**
     * Find a customer by DNI
     *
     * @param dni DNI of the customer to be searched
     * @return Customer found
     */
    @GetMapping("/search")
    public ResponseEntity<CustomerDto> getCustomerByDni(@RequestParam("dni") String dni) {
        log.info("Endpoint to get a customer by DNI: dni=" + dni);
        return new ResponseEntity<>(customerService.getCustomerByDni(dni), HttpStatus.OK);
    }

    /**
     * Find all customers
     *
     * @return All customers found
     */
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        log.info("Endpoint to get all customers");
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    /**
     * Create a customer
     *
     * @param dto Object containing new customer information
     * @return New customer created
     */
    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerDto dto, BindingResult bindingResult) {
        log.info("Endpoint to create a customer: data=" + dto);
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getAllErrors().toString());
            StringBuilder strBuilder = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                strBuilder.append(error.getDefaultMessage()).append(System.getProperty("line.separator"));
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, strBuilder.toString());
        }
        return new ResponseEntity<>(customerService.createCustomer(dto), HttpStatus.OK);
    }

    /**
     * Update a customer
     *
     * @param id  ID of the customer to be upgraded
     * @param dto Object containing customer information to be updated
     * @return Customer updated
     */
    @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto dto) {
        log.info("Endpoint to update a customer: id=" + id + ", data=" + dto);
        return new ResponseEntity<>(customerService.updateCustomer(id, dto), HttpStatus.OK);
    }

    /**
     * Delete a customer
     *
     * @param id ID of the client to be deleted
     * @return ID of the deleted client
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable("id") Long id) {
        log.info("Endpoint to delete a customer: id=" + id);
        return new ResponseEntity<>(customerService.deleteCustomerById(id), HttpStatus.OK);
    }
}
