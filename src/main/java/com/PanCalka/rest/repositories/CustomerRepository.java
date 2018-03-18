package com.PanCalka.rest.repositories;

import com.PanCalka.rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
