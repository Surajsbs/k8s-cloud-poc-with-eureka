package com.develop.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.develop.user.entity.Department;
import com.develop.user.entity.ResponseVo;
import com.develop.user.entity.UserEntity;
import com.develop.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	private RestTemplate template;

	@Autowired
	public UserService(RestTemplate template) {
		this.template = template;
	}

	public UserEntity save(UserEntity request) {
		return repo.save(request);
	}

	public ResponseVo findUser(Long id) {
		ResponseVo response = new ResponseVo();
		UserEntity user = repo.findByUserId(id);
		String url = String.format("http://dept-service:9001/api/dept/%s", user.getDeptId());
		Department dept = template.getForObject(url, Department.class);
		response.setDept(dept);
		response.setUser(user);
		return response;
	}

}
