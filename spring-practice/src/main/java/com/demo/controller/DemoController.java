package com.demo.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.DemoService;

@RestController
public class DemoController {

	@Autowired
	DemoService demoService;

	public DemoController(DemoService demoService) {
		System.out.println("DemoController");

	}

	@GetMapping(path = "greet")
	CompletableFuture<ResponseEntity<String>> greet() {
		System.out.println(Thread.currentThread().getName());
		CompletableFuture<String> greetings = demoService.greetings();
		return greetings.thenApply((result) -> {
			System.out.println("Controller Thread " + Thread.currentThread().getName());
			return ResponseEntity.ok(result);
		});
	}
}
