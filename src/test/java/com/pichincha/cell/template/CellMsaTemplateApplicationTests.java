package com.pichincha.cell.template;

import com.google.gson.Gson;
import com.pichincha.cell.template.controller.CustomerController;
import com.pichincha.cell.template.domain.Customer;
import com.pichincha.cell.template.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CellMsaTemplateApplicationTests {
  @Autowired private MockMvc mockMvc;
  @Autowired private CustomerController customerController;
  @MockBean private CustomerRepository customerRepositoryMock;

  @Test
  void getCustomer() {
    Long id = 1L;

    Customer customer = new Customer();
    customer.setId(id);
    customer.setDni("1721678843");
    customer.setName("Santiago");
    customer.setLastname("Gómez");

    when(customerRepositoryMock.findById(id)).thenReturn(Optional.of(customer));

    /*ResponseEntity<CustomerDto> result = customerController.getCustomer(id);
    CustomerDto customerDto = result.getBody();

    assertNotNull(customerDto);
    assertEquals(id, customerDto.getId());*/
  }

  @Test
  public void getCustomer2() throws Exception {
    final Gson gson = new Gson();
    Long id = 1L;

    Customer customer = new Customer();
    customer.setId(id);
    customer.setDni("1721678843");
    customer.setName("Santiago");
    customer.setLastname("Gómez");

    when(customerRepositoryMock.findById(id)).thenReturn(Optional.of(customer));

    var findById =
        mockMvc
            .perform(get("/customers/1").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andReturn();

    Customer customer1 = gson.fromJson(findById.getResponse().getContentAsString(), Customer.class);
    assertNotNull(customer1);
    assertEquals(id, customer1.getId());
  }
}
