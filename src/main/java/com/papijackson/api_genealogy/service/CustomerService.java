package com.papijackson.api_genealogy.service;

import com.papijackson.api_genealogy.dto.CustomerDto;
import com.papijackson.api_genealogy.entity.Customer;
import com.papijackson.api_genealogy.mapper.CustomerMapper;
import com.papijackson.api_genealogy.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des Customers
 */
@Service
@AllArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * Récupère tous les customers
     * @return la liste de tous les customers
     */
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toDtoList(customers);
    }

    /**
     * Récupère un customer par son ID
     * @param id l'ID du customer
     * @return Optional contenant le customer DTO si trouvé
     */
    @Transactional(readOnly = true)
    public Optional<CustomerDto> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto);
    }

    /**
     * Récupère un customer par son email
     * @param email l'email du customer
     * @return Optional contenant le customer DTO si trouvé
     */
    @Transactional(readOnly = true)
    public Optional<CustomerDto> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customerMapper::toDto);
    }

    /**
     * Crée un nouveau customer
     * @param customerDto le DTO du customer à créer
     * @return le customer créé
     */
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    /**
     * Met à jour un customer existant
     * @param id l'ID du customer à mettre à jour
     * @param customerDto le DTO avec les nouvelles données
     * @return Optional contenant le customer mis à jour si trouvé
     */
    public Optional<CustomerDto> updateCustomer(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFirstName(customerDto.getFirstName());
                    existingCustomer.setLastName(customerDto.getLastName());
                    existingCustomer.setEmail(customerDto.getEmail());
                    existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());
                    existingCustomer.setAddress(customerDto.getAddress());
                    existingCustomer.setCity(customerDto.getCity());
                    existingCustomer.setPostalCode(customerDto.getPostalCode());
                    existingCustomer.setCountry(customerDto.getCountry());
                    Customer updatedCustomer = customerRepository.save(existingCustomer);
                    return customerMapper.toDto(updatedCustomer);
                });
    }

    /**
     * Supprime un customer
     * @param id l'ID du customer à supprimer
     * @return true si suppression réussie, false sinon
     */
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

