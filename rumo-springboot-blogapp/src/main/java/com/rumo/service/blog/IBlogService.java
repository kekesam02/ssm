package com.rumo.service.blog;



import com.rumo.vo.ServerResponse2;

public interface IBlogService {
	
	public ServerResponse2 findBlogs(String keyword,String sortDesc,int pageNo,int pageSize);
	
}
