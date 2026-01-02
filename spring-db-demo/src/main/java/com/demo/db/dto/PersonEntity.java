package com.demo.db.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * To overcome the writing of raw queries we make use JPA by creating entity and
 * map field of entity to columns of the table and define the relationships.
 * 
 * JPA implementation takes care of writing queries.JPA is interfaces provides
 * annotations and apis which is implemented by the Hibernate ORM
 * 
 * JPA makes the object relational mapping(mapping java object to table)
 * 
 */

@Entity
@Table(name = "Person")
public class PersonEntity {

	@Id
	private int id;
	private Date birthDate;
	private String location;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
