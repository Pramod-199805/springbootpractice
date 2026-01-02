package com.demo.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.db.dto.Person;
import com.demo.db.mapper.PersonRowMapper;

@Repository
public class PersonRepository {

	/**
	 * Before Spring JDBC(JdbcTempalte) with plain JDBC we used to write boilerplate
	 * code Connection connection= datasource.getconnection(); Create Prepared
	 * statement return the ResultSet and map to object and then close the
	 * connection manually
	 * 
	 * with SpringJdbc because of Autoconfiguration spring when finds the particular
	 * database beans gets default values and creates the bean of it and injects it
	 * to the JdbcTemplate and closing the session is handled by framework
	 */
	JdbcTemplate template;

	PersonRepository(JdbcTemplate template) {
		this.template = template;
	}

	public List<Person> findAllPersons() {
		return template.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
	}

	/**
	 * "?" in sql prevents sql injection as it considers name argument passed as one
	 * single string arg
	 * 
	 * If we do concatenation then input name passed as Pieter' OR '1' = '1
	 * 
	 * Ex: "select * from person where name = " + "'" + name + "'" It creates sql
	 * statement like below SELECT * FROM PERSON where name = 'Pieter' OR '1'='1'
	 * 
	 * If name is not present '1' = '1' this condition becomes true and return all
	 * list
	 */
	public Person findPersonByName(String name) {
		return template.queryForObject("select * from person where name = ?", new BeanPropertyRowMapper<>(Person.class),
				name);
	}

	// Delete by Id
	public String deleteById(int id) {
		int num = template.update("delete from person where id = ?", id);
		return num != 0 ? "Resource deleted successfully." : "No resource found with the id " + id;
	}

	public String insert(Person person) {
		// maps the identifiers ? in sql
		Object[] args = { person.getId(), person.getName(), person.getLocation(), LocalDateTime.now() };

		int num = template.update("insert into person (id, name, location, birth_date) " + "values(?,  ?, ?, ?)", args);
		return num != 0 ? "Resource cretaed successfully." : "Something wnt wrong while createing resource";
	}

	public int update(Person person) {
		Object[] args = { person.getName(), person.getLocation(), person.getId() };

		return template.update("update person set name = ?, location = ? where id = ?", args);
	}

	/**
	 * In some cases the table column names are different and pojos will be
	 * different in such cases we need to make use RowMapper and map the required
	 * fields.
	 * 
	 * 1. create customerMapper by implementing RowMapper
	 */

	public List<Person> getAllPersons() {
		return template.query("select * from person", new PersonRowMapper());
	}
}
