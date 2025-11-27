package com.demo.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

		System.out.println("Controller Thread " + Thread.currentThread().getName());

		CompletableFuture<String> greetings = demoService.greetings();
		return greetings.thenApply((result) -> {

			System.out.println("Controller End: " + Thread.currentThread().getName());
			return ResponseEntity.ok(result);
		});
	}

	@GetMapping(value = "/employee")
	public CompletableFuture<ResponseEntity<String>> getEmployee() {
		System.out.println(Thread.currentThread().getName());
		CompletableFuture<String> response = demoService.getEmployee();
		System.out.println("Controller End: " + Thread.currentThread().getName());
		return response.thenApply(res -> ResponseEntity.ok().body(res))
				.exceptionally(r -> new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND));

	}
}
