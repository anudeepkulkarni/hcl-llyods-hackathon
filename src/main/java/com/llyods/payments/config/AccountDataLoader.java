package com.llyods.payments.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llyods.payments.entity.AccountEntity;
import com.llyods.payments.repository.AccountRepository;

@Configuration
public class AccountDataLoader {

	@Bean
	CommandLineRunner loadAccounts(AccountRepository accountRepository, ObjectMapper objectMapper) {

		return args -> {

			InputStream is = getClass().getClassLoader().getResourceAsStream("accounts.json");

			if (is == null) {
				throw new RuntimeException("accounts.json not found");
			}

			List<AccountEntity> accounts = objectMapper.readValue(is, new TypeReference<>() {
			});

			accountRepository.saveAll(accounts);

			System.out.println("✅ Loaded " + accounts.size() + " accounts into H2");
		};
	}
}
