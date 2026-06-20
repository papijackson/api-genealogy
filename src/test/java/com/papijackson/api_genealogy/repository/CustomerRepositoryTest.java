package com.papijackson.api_genealogy.repository;

import com.papijackson.api_genealogy.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setFirstName("Jean");
        testCustomer.setLastName("Dupont");
        testCustomer.setEmail("jean.dupont@example.com");
        testCustomer.setPhoneNumber("0123456789");
        testCustomer.setAddress("123 Rue de la Paix");
        testCustomer.setCity("Paris");
        testCustomer.setPostalCode("75001");
        testCustomer.setCountry("France");
    }

    @Test
    void testSaveCustomer() {
        // Act
        Customer savedCustomer = customerRepository.save(testCustomer);

        // Assert
        assertNotNull(savedCustomer.getId());
        assertEquals("Jean", savedCustomer.getFirstName());
        assertEquals("jean.dupont@example.com", savedCustomer.getEmail());
    }

    @Test
    void testFindByEmail() {
        // Arrange
        customerRepository.save(testCustomer);

        // Act
        Optional<Customer> foundCustomer = customerRepository.findByEmail("jean.dupont@example.com");

        // Assert
        assertTrue(foundCustomer.isPresent());
        assertEquals("Jean", foundCustomer.get().getFirstName());
        assertEquals("Dupont", foundCustomer.get().getLastName());
    }

    @Test
    void testFindByEmailNotFound() {
        // Act
        Optional<Customer> foundCustomer = customerRepository.findByEmail("nonexistent@example.com");

        // Assert
        assertFalse(foundCustomer.isPresent());
    }

    @Test
    void testFindById() {
        // Arrange
        Customer savedCustomer = customerRepository.save(testCustomer);

        // Act
        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getId());

        // Assert
        assertTrue(foundCustomer.isPresent());
        assertEquals("Jean", foundCustomer.get().getFirstName());
    }

    @Test
    void testFindAll() {
        // Arrange
        Customer customer1 = testCustomer;
        Customer customer2 = new Customer();
        customer2.setFirstName("Marie");
        customer2.setLastName("Martin");
        customer2.setEmail("marie.martin@example.com");

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        // Act
        int count = (int) customerRepository.count();

        // Assert
        assertEquals(2, count);
    }

    @Test
    void testUpdateCustomer() {
        // Arrange
        Customer savedCustomer = customerRepository.save(testCustomer);

        // Act
        savedCustomer.setFirstName("Pierre");
        Customer updatedCustomer = customerRepository.save(savedCustomer);

        // Assert
        assertEquals("Pierre", updatedCustomer.getFirstName());
        assertEquals("Dupont", updatedCustomer.getLastName());
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        Customer savedCustomer = customerRepository.save(testCustomer);
        Long customerId = savedCustomer.getId();

        // Act
        customerRepository.deleteById(customerId);

        // Assert
        assertFalse(customerRepository.existsById(customerId));
    }

}

