package com.lirain.scada.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lirain.scada.pojo.Userinfo;
import com.lirain.scada.service.UserinfoService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/spring-all.xml"})

public class TestMyBatis2 {
	private static Logger logger = Logger.getLogger(TestMyBatis2.class);
//	private ApplicationContext ac = null;
	@Resource
	//private IUserService userService = null;
	private  UserinfoService userInfo = null;

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}

	@Test
	public void test1() {
		//User user = userService.getUserById(1);
		new ClassPathXmlApplicationContext(new String[] { "spring/spring-all.xml" });
		Userinfo info =userInfo.getUserById(1);
		
		logger.info(JSON.toJSONString(info));
		
		// System.out.println("ok");
		// logger.info("值："+user.getUserName());
		//logger.info(JSON.toJSONString(user));
	}
}
