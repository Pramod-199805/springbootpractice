package com.demo.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.db.dto.PersonEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PersonJpaImplRepository {

	/**
	 * In JPA repo to connect to data we make use Entity manager interface like
	 * JdbcTemplate we use for spring jdbc
	 * 
	 * Entitymanager manages the entity all the operations stored in persistence
	 * context
	 */
	@Autowired
	@PersistenceContext
	EntityManager entityManager;

	public PersonEntity getPersonBYId(int id) {
		return entityManager.find(PersonEntity.class, id);
	}
}
