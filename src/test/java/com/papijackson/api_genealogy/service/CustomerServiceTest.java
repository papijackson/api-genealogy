package com.papijackson.api_genealogy.service;

import com.papijackson.api_genealogy.dto.CustomerDto;
import com.papijackson.api_genealogy.entity.Customer;
import com.papijackson.api_genealogy.mapper.CustomerMapper;
import com.papijackson.api_genealogy.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    private Customer testCustomer;
    private CustomerDto testCustomerDto;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setFirstName("Jean");
        testCustomer.setLastName("Dupont");
        testCustomer.setEmail("jean.dupont@example.com");
        testCustomer.setPhoneNumber("0123456789");
        testCustomer.setAddress("123 Rue de la Paix");
        testCustomer.setCity("Paris");
        testCustomer.setPostalCode("75001");
        testCustomer.setCountry("France");

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
    void testGetAllCustomers() {
        // Arrange
        List<Customer> customers = Arrays.asList(testCustomer);
        List<CustomerDto> customerDtos = Arrays.asList(testCustomerDto);
        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.toDtoList(customers)).thenReturn(customerDtos);

        // Act
        List<CustomerDto> result = customerService.getAllCustomers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Jean", result.get(0).getFirstName());
        verify(customerRepository).findAll();
    }

    @Test
    void testGetCustomerById() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(customerMapper.toDto(testCustomer)).thenReturn(testCustomerDto);

        // Act
        Optional<CustomerDto> result = customerService.getCustomerById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Jean", result.get().getFirstName());
        verify(customerRepository).findById(1L);
    }

    @Test
    void testGetCustomerByIdNotFound() {
        // Arrange
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<CustomerDto> result = customerService.getCustomerById(999L);

        // Assert
        assertFalse(result.isPresent());
        verify(customerRepository).findById(999L);
    }

    @Test
    void testGetCustomerByEmail() {
        // Arrange
        when(customerRepository.findByEmail("jean.dupont@example.com")).thenReturn(Optional.of(testCustomer));
        when(customerMapper.toDto(testCustomer)).thenReturn(testCustomerDto);

        // Act
        Optional<CustomerDto> result = customerService.getCustomerByEmail("jean.dupont@example.com");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("jean.dupont@example.com", result.get().getEmail());
        verify(customerRepository).findByEmail("jean.dupont@example.com");
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        when(customerMapper.toEntity(testCustomerDto)).thenReturn(testCustomer);
        when(customerRepository.save(testCustomer)).thenReturn(testCustomer);
        when(customerMapper.toDto(testCustomer)).thenReturn(testCustomerDto);

        // Act
        CustomerDto result = customerService.createCustomer(testCustomerDto);

        // Assert
        assertNotNull(result);
        assertEquals("Jean", result.getFirstName());
        verify(customerRepository).save(testCustomer);
    }

    @Test
    void testUpdateCustomer() {
        // Arrange
        CustomerDto updatedDto = new CustomerDto();
        updatedDto.setFirstName("Pierre");
        updatedDto.setLastName("Martin");
        updatedDto.setEmail("pierre.martin@example.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);
        when(customerMapper.toDto(any(Customer.class))).thenReturn(testCustomerDto);

        // Act
        Optional<CustomerDto> result = customerService.updateCustomer(1L, updatedDto);

        // Assert
        assertTrue(result.isPresent());
        verify(customerRepository).findById(1L);
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomerNotFound() {
        // Arrange
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<CustomerDto> result = customerService.updateCustomer(999L, testCustomerDto);

        // Assert
        assertFalse(result.isPresent());
        verify(customerRepository).findById(999L);
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        when(customerRepository.existsById(1L)).thenReturn(true);

        // Act
        boolean result = customerService.deleteCustomer(1L);

        // Assert
        assertTrue(result);
        verify(customerRepository).existsById(1L);
        verify(customerRepository).deleteById(1L);
    }

    @Test
    void testDeleteCustomerNotFound() {
        // Arrange
        when(customerRepository.existsById(999L)).thenReturn(false);

        // Act
        boolean result = customerService.deleteCustomer(999L);

        // Assert
        assertFalse(result);
        verify(customerRepository).existsById(999L);
        verify(customerRepository, never()).deleteById(any());
    }

}

