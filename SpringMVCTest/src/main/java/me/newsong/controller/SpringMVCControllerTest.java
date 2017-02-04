package me.newsong.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import me.newsong.domain.User;
import me.newsong.service.iface.UserService;

@SessionAttributes(value={"user"},types={String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCControllerTest {
	private static final String SUCCESS = "success";
	@Autowired
	private UserService userService;

	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}

	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}

	@RequestMapping(value = "/testParamsAndHeaders", params = { "username", "age!=10" }, headers = {
			"Accept-Language=zh-CN,zh;q=0.8" })
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}

	@RequestMapping("/testPathVariable/delete/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("delete id=" + id + " user");
		return SUCCESS;
	}

	@RequestMapping(value = "/testREST/{id}", method = RequestMethod.GET)
	public String testRESTGET(@PathVariable("id") Integer id) {
		System.out.println("GET id=" + id + " user");
		return SUCCESS;
	}

	@RequestMapping(value = "/testREST", method = RequestMethod.POST)
	public String testRESTPOST() {
		System.out.println("POST a new user");
		return SUCCESS;
	}

	@RequestMapping(value = "/testREST/{id}", method = RequestMethod.PUT)
	public String testRESTPUT(@PathVariable("id") Integer id) {
		System.out.println("PUT id=" + id + " user");
		return SUCCESS;
	}

	@RequestMapping(value = "/testREST/{id}", method = RequestMethod.DELETE)
	public String testRESTDELETE(@PathVariable("id") Integer id) {
		System.out.println("DELETE id=" + id + " user");
		return SUCCESS;
	}

	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println("testRequestParam: username=" + username + " ,password=" + password);
		return SUCCESS;
	}

	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader("Accept-Language") String language) {
		System.out.println("testRequestHeader Accept-Language:" + language);
		return SUCCESS;
	}

	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String JSESSIONID) {
		System.out.println("JSESSIONID:" + JSESSIONID);
		return SUCCESS;
	}

	@RequestMapping("/testPOJO")
	public String testPOJO(User user) {
		System.out.println("testPOJO:" + user);
		return SUCCESS;
	}

	@RequestMapping("/testServletAPI")
	public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Servlet:" + request + " , " + response);
		return SUCCESS;
	}

	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		modelAndView.addObject("time", new Date());
		// 将键值对放入到viewName所对应的视图的request域中
		return modelAndView;
	}

	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		map.put("names", Arrays.asList("Tom", "Jerry"));
		// 将键值对放入到返回值所在的视图的request域中
		return SUCCESS;
	}

	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		map.put("user", new User("admin", "admin"));
		map.put("heheda", "asdasdasda");
		return SUCCESS;
	}

	@ModelAttribute
	public void getUser(Map<String, Object> map) {
		User user = userService.findByUsername("admin");
		map.put("user", user);
	}

	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(@ModelAttribute("user") User aaa) {
		userService.update(aaa);
		return SUCCESS;
	}
	
	@RequestMapping("/testHelloView")
	public String testHelloView(){
		System.out.println("testHelloView");
		return "helloView";
	}
	
	@RequestMapping("/testRedirect")
	public String testRedirect(){
		System.out.println("testRedirect");
//		return "forward:/WEB-INF/views/success.jsp";//ok
		return "redirect:/test.jsp";//ok
	}
	
	
}
