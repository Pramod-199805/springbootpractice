package com.demo.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.db.dto.Person;

public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Person person = new Person();
		person.setName(rs.getString("name"));
		person.setLocation(rs.getString("location"));
		person.setBirthDate(rs.getTimestamp("birth_date"));
		return person;
	}

}
