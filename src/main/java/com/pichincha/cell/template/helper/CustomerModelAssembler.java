package com.pichincha.cell.template.helper;

import com.pichincha.cell.template.controller.CustomerController;
import com.pichincha.cell.template.domain.dto.CustomerDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler
    implements RepresentationModelAssembler<CustomerDto, EntityModel<CustomerDto>> {
  @Override
  public EntityModel<CustomerDto> toModel(CustomerDto dto) {
    return EntityModel.of(
        dto,
        linkTo(methodOn(CustomerController.class).getById(dto.getId())).withSelfRel(),
        linkTo(methodOn(CustomerController.class).getAll()).withRel("source"));
  }
}
