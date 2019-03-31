package com.rumo.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rumo.mapper.BlogMapper;
import com.rumo.pojo.Blog;
import com.rumo.pojo.BlogExample;
import com.rumo.pojo.BlogExample.Criteria;
import com.rumo.util.SQLUtil;
import com.rumo.util.StringUtils;
import com.rumo.vo.ServerResponse2;

@Service
public class BlogServiceImpl implements IBlogService {
	
	@Autowired
	private BlogMapper blogMapper;

	@Override
	public ServerResponse2 findBlogs(String keyword,String sortDesc,int pageNo, int pageSize) {
		if(pageNo<=0)pageNo = 1;
		//3:设置分页
		PageHelper.startPage(pageNo, pageSize);
		//1：创建查询的参数条件类
		BlogExample blogExample = new BlogExample();
		//2:创建查询条件
		if(StringUtils.isNotEmpty(keyword)) {
			Criteria criteria =  blogExample.createCriteria();
			criteria.andNameLike(SQLUtil.like(keyword));
		}
		//4:设置排序  
		blogExample.setOrderByClause(sortDesc==null?"create_time desc":sortDesc);
		//查询所有的文章 分页的插件调用和排序，一定定义在方法的前面。否则不会生效。
		List<Blog> blogs = blogMapper.selectByExample(blogExample);
		//设置分页
		PageInfo<Blog> pageInfo = new PageInfo<>(blogs); 
		//return ServerResponse.createBySuccess(pageInfo);
		return ServerResponse2.createBySuccess("查询内容返回成功!!", pageInfo);
		
		
	}
	
	

}
