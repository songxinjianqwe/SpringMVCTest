package me.newsong.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String showAll(Map<String, Object> map) {
		map.put("emps", employeeService.findAll());
		return "list";
	}

	// 显示添加员工页面
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String showAddForm(Map<String, Object> map) {
		// 显示所有员工
		map.put("depts", departmentService.findAll());
		// request域中必须有一个POJO对象，实现表单值的回显，这里是新增，因此放入一个空对象即可
		map.put("employee", new Employee());
		// 为性别的单选按钮标签准备数据，默认 显示到页面是的value，实际的填充值是key
		Map<String, String> genders = new HashMap<>();
		genders.put("M", "Male");
		genders.put("F", "Female");
		// 放到request域中
		map.put("genders", genders);
		return "input";
	}

	//添加员工，重定向至显示所有员工信息界面
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String add(@Valid Employee employee,BindingResult result,Map<String,Object> map) {
		if(result.hasErrors()){
			System.out.println("Error!");
			for(FieldError error: result.getFieldErrors()){
				System.out.println(error.getField()+" :" + error.getDefaultMessage());
			}
			//放入必要的选项
			map.put("depts", departmentService.findAll());
			Map<String, String> genders = new HashMap<>();
			genders.put("M", "Male");
			genders.put("F", "Female");
			// 放到request域中
			map.put("genders", genders);
			return "input";
		}else{
			employeeService.save(employee);
		}
		// 重定向至list.jsp（重新查询）
		return "redirect:/emps";// 重定向到controller的处理方法list，重新获取list，然后显示
	}
	
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		employeeService.delete(id);
		return "redirect:/emps";// 重定向到controller
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Integer id, Map<String, Object> map) {
		// 显示所有员工
		map.put("employee", employeeService.findByID(id));
		// request域中必须有一个POJO对象，实现表单值的回显，这里是新增，因此放入一个空对象即可
		map.put("depts", departmentService.findAll());
		// 为性别的单选按钮标签准备数据，默认 显示到页面是的value，实际的填充值是key
		Map<String, String> genders = new HashMap<>();
		genders.put("M", "Male");
		genders.put("F", "Female");
		// 放到request域中
		map.put("genders", genders);
		return "edit";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String update(Employee employee, Map<String, Object> map) {
		employeeService.update(employee);
		return "redirect:/emps";
	}
	
	//因为在所有controller方法被调用之前被调用，所以请求参数必须含有id（从Employee 的id得来），且当id非空时，是一个修改操作
	//如果是添加，那么id为空
	//如果是按id获取，那么是RESTful风格的URL，id放在路径中，而非请求参数
	//所以只要对id非空进行判断，就服务于更新操作。
	//id参数是来自于表单数据的id属性
	@ModelAttribute
	public void loadForUpdate(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		if(id != null){
			map.put("employee",employeeService.findByID(id));
		}
	}
	
	/*@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields("lastName");
	}*/
	
	
}
