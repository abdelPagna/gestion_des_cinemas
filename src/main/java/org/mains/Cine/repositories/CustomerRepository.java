package org.mains.Cine.repositories;

import java.util.Optional;

import org.mains.Cine.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String >{

	Optional<Customer> findByNameIgnoreCase(String name);

}
