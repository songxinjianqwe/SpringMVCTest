package me.newsong.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.newsong.domain.Employee;
import me.newsong.service.iface.DepartmentService;
import me.newsong.service.iface.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;

	// 显示所有员工信息
	@RequestMapping(value = "/emps", method = RequestMethod.GET)
	public String list(Map<String, Object> map) {
		map.put("emps", employeeService.findAll());
		return "list";
	}

	// 显示添加员工页面
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String showAdd(Map<String,Object> map) {
		map.put("depts", departmentService.findAll());
		map.put("employee", new Employee());
		
		Map<String,String> genders = new HashMap<>();
		genders.put("M","Male");
		genders.put("F", "Female");
		map.put("genders", genders);
		return "input";
	}
	
	// 添加员工，转发至显示所有员工信息界面
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String add(Employee employee){
		employeeService.save(employee);
		return "";
	}
	
	
	
	
}
