package com.papijackson.api_genealogy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.papijackson.api_genealogy.dto.CustomerDto;
import com.papijackson.api_genealogy.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerDto testCustomerDto;

    @BeforeEach
    void setUp() {
        testCustomerDto = new CustomerDto();
        testCustomerDto.setId(1L);
        testCustomerDto.setFirstName("Jean");
        testCustomerDto.setLastName("Dupont");
        testCustomerDto.setEmail("jean.dupont@example.com");
        testCustomerDto.setPhoneNumber("0123456789");
        testCustomerDto.setAddress("123 Rue de la Paix");
        testCustomerDto.setCity("Paris");
        testCustomerDto.setPostalCode("75001");
        testCustomerDto.setCountry("France");
    }

    @Test
    void testGetAllCustomers() throws Exception {
        // Arrange
        List<CustomerDto> customers = Arrays.asList(testCustomerDto);
        when(customerService.getAllCustomers()).thenReturn(customers);

        // Act & Assert
        mockMvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value("Jean"));

        verify(customerService).getAllCustomers();
    }

    @Test
    void testGetCustomerById() throws Exception {
        // Arrange
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(testCustomerDto));

        // Act & Assert
        mockMvc.perform(get("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jean"))
                .andExpect(jsonPath("$.email").value("jean.dupont@example.com"));

        verify(customerService).getCustomerById(1L);
    }

    @Test
    void testGetCustomerByIdNotFound() throws Exception {
        // Arrange
        when(customerService.getCustomerById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/customers/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(customerService).getCustomerById(999L);
    }

    @Test
    void testGetCustomerByEmail() throws Exception {
        // Arrange
        when(customerService.getCustomerByEmail("jean.dupont@example.com"))
                .thenReturn(Optional.of(testCustomerDto));

        // Act & Assert
        mockMvc.perform(get("/api/customers/email/jean.dupont@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("jean.dupont@example.com"));

        verify(customerService).getCustomerByEmail("jean.dupont@example.com");
    }

    @Test
    void testCreateCustomer() throws Exception {
        // Arrange
        CustomerDto newCustomerDto = new CustomerDto();
        newCustomerDto.setFirstName("Pierre");
        newCustomerDto.setLastName("Martin");
        newCustomerDto.setEmail("pierre.martin@example.com");

        testCustomerDto.setId(2L);
        when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(testCustomerDto);

        // Act & Assert
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCustomerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2));

        verify(customerService).createCustomer(any(CustomerDto.class));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        // Arrange
        CustomerDto updatedDto = new CustomerDto();
        updatedDto.setFirstName("Pierre");
        updatedDto.setLastName("Martin");
        updatedDto.setEmail("pierre.martin@example.com");

        when(customerService.updateCustomer(eq(1L), any(CustomerDto.class)))
                .thenReturn(Optional.of(updatedDto));

        // Act & Assert
        mockMvc.perform(put("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Pierre"));

        verify(customerService).updateCustomer(eq(1L), any(CustomerDto.class));
    }

    @Test
    void testUpdateCustomerNotFound() throws Exception {
        // Arrange
        when(customerService.updateCustomer(eq(999L), any(CustomerDto.class)))
                .thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(put("/api/customers/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCustomerDto)))
                .andExpect(status().isNotFound());

        verify(customerService).updateCustomer(eq(999L), any(CustomerDto.class));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        // Arrange
        when(customerService.deleteCustomer(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isNoContent());

        verify(customerService).deleteCustomer(1L);
    }

    @Test
    void testDeleteCustomerNotFound() throws Exception {
        // Arrange
        when(customerService.deleteCustomer(999L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/customers/999"))
                .andExpect(status().isNotFound());

        verify(customerService).deleteCustomer(999L);
    }

}

