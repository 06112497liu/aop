package com.example.aop;

import com.example.aop.service.TestService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AopApplicationTests {

	@Autowired
	TestService testService;

	@Test
	public void aop01() {
		Integer integer = this.testService.method01(69);
		System.out.println(integer);
	}

}

