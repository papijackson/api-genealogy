package com.papijackson.api_genealogy.repository;

import com.papijackson.api_genealogy.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository JPA pour l'entité Customer
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Cherche un customer par email
     * @param email l'email du customer
     * @return Optional contenant le customer si trouvé
     */
    Optional<Customer> findByEmail(String email);

}

