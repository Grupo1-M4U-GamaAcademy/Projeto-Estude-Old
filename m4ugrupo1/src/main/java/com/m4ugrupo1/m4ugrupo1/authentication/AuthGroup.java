package com.m4ugrupo1.m4ugrupo1.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

//Classe de autenticação de usuário, usando Lombok para deixar o visual mais limpo.

@NoArgsConstructor
@Data
@Entity
@Table(name = "authentication_user_group")
public class AuthGroup {
	
	@Id
	@Column(name = "auth_user_group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authUser_id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "auth_group")
	private String authGroup;

	public AuthGroup(String username, String authGroup) {
		super();
		this.username = username;
		this.authGroup = authGroup;
	}

	

}

	

	
	

