package com.Cp.Stage.DTOs;


import java.util.Set;

import com.Cp.Stage.Models.ERole;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String username;
	private Set<ERole> roles;

	public JwtResponse(String accessToken, String username, Set<ERole> roles) {
		this.token = accessToken;
		this.username = username;
		this.roles = roles;
	}
}