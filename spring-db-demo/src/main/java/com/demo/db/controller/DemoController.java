package com.demo.db.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.db.dto.Person;
import com.demo.db.repository.PersonRepository;

@RestController
@RequestMapping("api/")
public class DemoController {

	private PersonRepository repository;

	public DemoController(PersonRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/person")
	public ResponseEntity<List<Person>> getAllPersons() {
		return new ResponseEntity<List<Person>>(repository.findAllPersons(), HttpStatus.OK);
	}

	@GetMapping(path = "/person/{name}")
	public ResponseEntity<Person> getAllPerson(@PathVariable String name) {
		return new ResponseEntity<Person>(repository.findPersonByName(name), HttpStatus.OK);
	}
	
	
	@DeleteMapping(path = "/person/{id}")
	public ResponseEntity<String> getAllPerson(@PathVariable int id) {
		return new ResponseEntity<String>(repository.deleteById(id), HttpStatus.OK);
	}
	
	@PostMapping(path = "/person")
	public ResponseEntity<String> createPerson(@RequestBody Person person) {
		return new ResponseEntity<String>(repository.insert(person), HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/person/v2")
	public ResponseEntity<List<Person>> getAllPerson() {
		return new ResponseEntity<List<Person>>(repository.getAllPersons(), HttpStatus.OK);
	}
}
