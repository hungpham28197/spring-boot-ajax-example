package com.hungpham.request;

import javax.validation.constraints.NotBlank;

public class SearchCritercia {

	@NotBlank(message = "username not emtry")
	private String username;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
