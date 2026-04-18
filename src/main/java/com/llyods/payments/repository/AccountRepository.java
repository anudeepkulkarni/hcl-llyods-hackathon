package com.llyods.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.llyods.payments.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
	// No extra methods needed for hackathon
}