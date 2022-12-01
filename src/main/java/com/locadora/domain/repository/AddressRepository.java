package com.locadora.domain.repository;

import com.locadora.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByLogin(String login);
}
