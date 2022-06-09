package com.pichincha.cell.template.controller;

import com.pichincha.cell.template.domain.dto.CustomerDto;
import com.pichincha.cell.template.helper.CustomerModelAssembler;
import com.pichincha.cell.template.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/** Endpoints that manage the customers */
@RestController
@Slf4j
@Validated
@AllArgsConstructor
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

  private final CustomerService customerService;
  private final CustomerModelAssembler customerModelAssembler;

  /**
   * Find a customer by ID
   *
   * @param id ID of the customer to be searched
   * @return Customer found
   */
  @GetMapping("/{id}")
  public EntityModel<CustomerDto> getById(@PathVariable("id") Long id) {
    log.info("Endpoint to get a customer by ID: id=" + id);
    return customerModelAssembler.toModel(customerService.getCustomerById(id));
  }

  /**
   * Find a customer by DNI
   *
   * @param dni DNI of the customer to be searched
   * @return Customer found
   */
  @GetMapping("/search")
  public EntityModel<CustomerDto> getByDni(@RequestParam("dni") String dni) {
    log.info("Endpoint to get a customer by DNI: dni=" + dni);
    return customerModelAssembler.toModel(customerService.getCustomerByDni(dni));
  }

  /**
   * Find all customers
   *
   * @return All customers found
   */
  @GetMapping
  public CollectionModel<EntityModel<CustomerDto>> getAll() {
    log.info("Endpoint to get all customers");
    List<EntityModel<CustomerDto>> customers = new ArrayList<>();
    for (CustomerDto customerDto : customerService.getAllCustomers()) {
      EntityModel<CustomerDto> customerDtoEntityModel = customerModelAssembler.toModel(customerDto);
      customers.add(customerDtoEntityModel);
    }

    return CollectionModel.of(
        customers, linkTo(methodOn(CustomerController.class).getAll()).withSelfRel());
  }

  /**
   * Create a customer
   *
   * @param dto Object containing new customer information
   * @return New customer created
   */
  @PostMapping(headers = "Accept=application/json;charset=UTF-8")
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerDto create(@RequestBody @Valid CustomerDto dto) {
    log.info("Endpoint to create a customer: data=" + dto);
    return customerService.createCustomer(dto);
  }

  /**
   * Update a customer
   *
   * @param id ID of the customer to be upgraded
   * @param dto Object containing customer information to be updated
   * @return Customer updated
   */
  @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
  public ResponseEntity<CustomerDto> update(
      @PathVariable Long id, @RequestBody @Valid CustomerDto dto) {
    log.info("Endpoint to update a customer: id=" + id + ", data=" + dto);
    return ResponseEntity.ok(customerService.updateCustomer(id, dto));
  }

  /**
   * Delete a customer
   *
   * @param id ID of the client to be deleted
   * @return ID of the deleted client
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
    log.info("Endpoint to delete a customer: id=" + id);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    return new ResponseEntity<>(customerService.deleteCustomerById(id), headers, HttpStatus.OK);
  }
}
