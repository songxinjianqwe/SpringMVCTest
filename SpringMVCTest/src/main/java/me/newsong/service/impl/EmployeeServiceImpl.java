package me.newsong.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.newsong.dao.EmployeeRepository;
import me.newsong.domain.Employee;
import me.newsong.service.iface.EmployeeService;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository dao;

	@Override
	public Employee findByID(Integer id) {
		return dao.findOne(id);
	}

	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}

	@Override
	public void update(Employee employee) {
		dao.saveAndFlush(employee);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void save(Employee employee) {
		dao.save(employee);
	}

}
