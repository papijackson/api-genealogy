package com.papijackson.api_genealogy.mapper;

import com.papijackson.api_genealogy.dto.CustomerDto;
import com.papijackson.api_genealogy.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper MapStruct pour convertir entre Customer et CustomerDto
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    /**
     * Convertit une entité Customer en DTO
     * @param customer l'entité Customer
     * @return le DTO CustomerDto
     */
    CustomerDto toDto(Customer customer);

    /**
     * Convertit un DTO CustomerDto en entité Customer
     * @param customerDto le DTO CustomerDto
     * @return l'entité Customer
     */
    Customer toEntity(CustomerDto customerDto);

    /**
     * Convertit une liste d'entités Customer en liste de DTOs
     * @param customers la liste d'entités Customer
     * @return la liste de DTOs CustomerDto
     */
    List<CustomerDto> toDtoList(List<Customer> customers);

    /**
     * Convertit une liste de DTOs en liste d'entités Customer
     * @param customerDtos la liste de DTOs CustomerDto
     * @return la liste d'entités Customer
     */
    List<Customer> toEntityList(List<CustomerDto> customerDtos);

}

