package com.biswo.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RefreshScope
public class MyClientController {

	 @Value("${test.greeting}")
	  private String msg1;

	  @Value("${test.msg}")
	  private String msg2;

	
	 
     @GetMapping("/sayValue")
     public String sayValue() {
    	 String value=msg1.concat("---").concat(msg2);
    	 System.out.println("Value:"+value);
         return value;
     }
}
