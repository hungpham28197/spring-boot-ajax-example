package com.hungpham.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hungpham.entity.User;
import com.hungpham.request.SearchCritercia;
import com.hungpham.response.AjaxResponseBody;
import com.hungpham.service.UserService;

@RestController
public class SearchController {

	UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("api/search")
	public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCritercia search, Errors errors) {
		AjaxResponseBody result = new AjaxResponseBody();
		
		if(errors.hasErrors()) {
			result.setMsg(errors.getAllErrors()
					.stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(",")));
			
			return ResponseEntity.badRequest().body(result);
		}
		
		List<User> users = userService.findByUsernamOrEmail(search.getUsername());
		
		if(users.isEmpty()) {
			result.setMsg("no user found");
		} else {
			result.setResult(users);
		}
		
		return ResponseEntity.ok(result);
	}
}
