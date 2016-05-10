/**
* Wisesoft.com Inc.
* Copyright (c) 2000-2015 All Rights Reserved.
*/
package com.lirian.scada.electron;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lirain.scada.pojo.Userinfo;
import com.lirain.scada.service.UserinfoService;

/**
 * 
 * 
 * @author lirain
 * @version Application.java, v0.1 2015年9月30日 上午11:17:48
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring/spring-all.xml"})
public class Application {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new ClassPathXmlApplicationContext(new String[] { "spring/spring-all.xml" });
	}

}

