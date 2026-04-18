package com.hcl.hackaton.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hackaton.service.impl.KafkaProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
public class CustomerController {

	private final KafkaProducerService producer;

	@GetMapping("/send")
	public String send(@RequestParam String message) {
		producer.sendMessage(message);
		return "Message sent to Kafka";
	}
}
