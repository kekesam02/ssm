package com.rumo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rumo.mapper.BlogMapper;
import com.rumo.pojo.Blog;
import com.rumo.pojo.BlogExample;
import com.rumo.pojo.BlogExample.Criteria;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RumoSpringbootBlogappApplicationTests {


	@Autowired
	private BlogMapper blogMapper;
	
	@Test
	public void contextLoads() {
		//根据 它不是，如果不还可以自己定义
		//select count(1) from keke_blog WHERE `name` LIKE '%人%'
		//查询参数类 
		BlogExample example = new BlogExample();
		//去重
		example.setDistinct(true);
		//创建查询条件 hibernate hql qbc qbe sql
		Criteria criteria = example.createCriteria();
		criteria.andNameLike("%人%");
		Criteria criteria2 = example.createCriteria();
		criteria2.andIsDeleteEqualTo(0);
		example.or(criteria2);
		int count = blogMapper.countByExample(example);
		System.out.println(count);
	}
	
	@Test
	public void testPage() {
		//1：创建查询的参数条件类
		BlogExample blogExample = new BlogExample();
		//2:创建查询条件
		//Criteria criteria =  blogExample.createCriteria();
	
		//查询所有的文章
		List<Blog> blogs = blogMapper.selectByExample(blogExample);
		for (Blog blog : blogs) {
			System.out.println(blog.getName());
		}
		
		
	}

}
