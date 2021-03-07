package com.springboot.redis.db.repo;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springboot.redis.db.entities.Employee;

@Repository
public interface Employeerepo {

	void save(final Employee employee);

	Employee findById(final String id);

	Map<String, Employee> findAll();

	void delete(String id);
}
