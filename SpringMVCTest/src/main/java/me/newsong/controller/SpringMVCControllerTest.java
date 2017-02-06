package me.newsong.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import me.newsong.domain.Employee;
import me.newsong.domain.User;
import me.newsong.exceptions.UsernameNotExistedException;
import me.newsong.service.iface.EmployeeService;
import me.newsong.service.iface.UserService;
import me.newsong.utils.FileUploadUitl;

@SessionAttributes(value = { "user" }, types = { String.class })
@RequestMapping("/springmvc")
@Controller
public class SpringMVCControllerTest {
	private static final String SUCCESS = "success";
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceBundleMessageSource messageSource;

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
	public String testHelloView() {
		System.out.println("testHelloView");
		return "helloView";
	}

	@RequestMapping("/testRedirect")
	public String testRedirect() {
		System.out.println("testRedirect");
		// return "forward:/WEB-INF/views/success.jsp";//ok
		return "redirect:/test.jsp";// ok
	}

	@ResponseBody
	@RequestMapping("/testJson")
	public List<Employee> testJson() {
		return employeeService.findAll();
	}

	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body) {
		System.out.println(body);
		return "OK!" + new Date();
	}

	@RequestMapping("/testDownload")
	public ResponseEntity<byte[]> testDownload(@RequestParam("fileName") String fileName, HttpSession session)
			throws IOException {
		byte[] body = null;
		ServletContext servletContext = session.getServletContext();
		FileInputStream in = new FileInputStream(servletContext.getRealPath("/WEB-INF/uploads/" + fileName));
		body = new byte[in.available()];
		in.read(body);
		HttpHeaders headers = new HttpHeaders();
		String contentType = servletContext.getMimeType(fileName);
		headers.add("Content-Type", contentType);
		String contentDisposition = "attachment;filename=" + fileName;
		headers.add("Content-Disposition", contentDisposition);
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, status);
		return entity;
	}

	@RequestMapping("/testI18n")
	public String testI18n(Locale locale) {
		String user = messageSource.getMessage("i18n.username", null, locale);
		System.out.println(user);
		return SUCCESS;
	}

	@RequestMapping("/i18n")
	public String i18n(@RequestParam("locale") Locale locale) {
		String user = messageSource.getMessage("i18n.username", null, locale);
		System.out.println(user);
		return "i18n";
	}

	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc,
			HttpSession session) throws IllegalStateException, IOException {
		FileUploadUitl.upload(file, session.getServletContext());
		return SUCCESS;
	}

	// 可以加入Exception类型的参数，该参数是发生的异常对象
	// 参数中不能有Map，但可以有ModelAndView，用于传递异常至页面
	// 标识的异常有优先级问题，先匹配较为具体的异常，再匹配较为宽泛的异常
	@ExceptionHandler({ IllegalStateException.class, IOException.class })
	public ModelAndView handleFileUploadException(Exception e) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", e);
		return modelAndView;
	}
	
	// *************************************************************************
	@RequestMapping("/testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i) {
		System.out.println(10 / i);
		return SUCCESS;
	}

	/*@ExceptionHandler({ ArithmeticException.class })
	public ModelAndView handleArithmeticException(Exception e) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", e);
		return modelAndView;
	}*/
	
	@RequestMapping("/testResponseStatus")
	public String testResponseStatus(@RequestParam("i") int i ){
		if(i == 10){
			throw new UsernameNotExistedException();
		}
		System.out.println("testResponseStatus OK!");
		return SUCCESS;
	}
	
	@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE,reason="用户名不存在")
	@RequestMapping("/testResponseStatusForMethod")
	public String testResponseStatusForMethod(@RequestParam("i") int i ){
		if(i == 10){
			throw new UsernameNotExistedException();
		}
		System.out.println("testResponseStatusForMethod OK!");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testDefaultHandlerExceptionResolver",method=RequestMethod.POST)
	public String testDefaultHandlerExceptionResolver(){
		System.out.println("testDefaultHandlerExceptionResolver");
		return SUCCESS;
	}
	@RequestMapping("/testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") int i ){
		String[] vals = new String[10];
		System.out.println(vals[i]);
		return SUCCESS;
	}
	
}
