package me.newsong.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import me.newsong.dao.DepartmentRepository;
import me.newsong.domain.Department;
import me.newsong.service.iface.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentRepository dao;
	
	@Override
	public List<Department> findAll() {
		return dao.findAll();
	}

}
