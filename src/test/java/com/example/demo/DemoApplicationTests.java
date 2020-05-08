package com.example.demo;

import cn.hutool.core.lang.Console;
import com.example.demo.entity.Student;
import com.example.demo.rest.UserController;
import com.example.demo.service.SeckillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	SeckillService seckillService;

	@Test
	void contextLoads()  {
		Console.log(seckillService.exposeSeckillUrl(1234567));
	}

}
