package me.newsong.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import me.newsong.domain.Employee;
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	
}
