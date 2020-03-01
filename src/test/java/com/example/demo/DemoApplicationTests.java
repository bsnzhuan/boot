package com.example.demo;

import com.example.demo.redis.ValueRedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	private ValueRedisDao valueRedisDao;

	@Test
	public void test() throws Exception{
		this.valueRedisDao.save("115");
		System.out.println(this.valueRedisDao.getParam());
	}

}
