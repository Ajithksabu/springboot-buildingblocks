package com.stacksimplify.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
//Simple method
//	@RequestMapping(method = RequestMethod.GET,path="/helloworld");
	@GetMapping("/helloworld1")
	public String helloworld() {
		return "Hello world";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloworldbean() {
		return new UserDetails("Ajith","K Sabu","Cherthala");
	}
	
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name="Accept-Language",required=false) String locale) {
		return messageSource.getMessage("label.hello", null,new Locale(locale));
	}
	
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat() {
		return messageSource.getMessage("label.hello", null,LocaleContextHolder.getLocale());
	}
}
