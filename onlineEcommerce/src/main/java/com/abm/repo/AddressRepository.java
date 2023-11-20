package com.abm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abm.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
