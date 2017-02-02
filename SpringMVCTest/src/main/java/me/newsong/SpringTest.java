package me.newsong;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.newsong.dao.UserRepository;

public class SpringTest {
	private ApplicationContext ctx;
	private UserRepository dao;
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = ctx.getBean(UserRepository.class);
	}
	
	@Test
	public void test() {
		dao.findByUsername("admin");
	}
	
}
