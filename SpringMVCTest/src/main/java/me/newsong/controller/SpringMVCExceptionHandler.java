package me.newsong.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SpringMVCExceptionHandler {
	@ExceptionHandler({ ArithmeticException.class })
	public ModelAndView handleArithmeticException(Exception e) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", e);
		return modelAndView;
	}
}
