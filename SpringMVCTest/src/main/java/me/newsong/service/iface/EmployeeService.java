package me.newsong.service.iface;

import java.util.List;

import me.newsong.domain.Employee;

public interface EmployeeService {
	Employee findByID(Integer id);
	List<Employee> findAll();
	void update(Employee employee);
	void delete(Integer id);
	void save(Employee employee);
}
