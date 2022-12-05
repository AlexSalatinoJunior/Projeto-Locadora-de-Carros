package com.locadora.domain.repository;

import com.locadora.domain.entity.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByLogin(String login);
}
