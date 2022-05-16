package com.develop.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.develop.user.entity.ResponseVo;
import com.develop.user.entity.UserEntity;
import com.develop.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping(value = "/save")
	public UserEntity saveUser(@RequestBody UserEntity request) {
		return service.save(request);
	}

	@GetMapping(value = "/{id}")
	public ResponseVo findUser(@PathVariable(name = "id") Long id) {
		return service.findUser(id);
	}

}
