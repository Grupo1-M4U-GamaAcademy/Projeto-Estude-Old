package com.m4ugrupo1.m4ugrupo1.authentication;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.m4ugrupo1.m4ugrupo1.model.User;

public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private User user;
	private List<AuthGroup> authGroups;
	
	public UserPrincipal(User user, List<AuthGroup> authGroups) {
		super();
		this.user = user;
		this.authGroups = authGroups;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		if (null == authGroups) {
			return Collections.emptySet();
		}
		
		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
		authGroups.forEach(group ->{
			grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
		});
		return grantedAuthorities;  //Returns an empty set (immutable). This set is serializable.Unlike
		//the like-named field, this method is parameterized. 
		}
	
	@Override
	public String getPassword() {  //Returns the password used to authenticate the user
		return this.getPassword();
	}
	
	@Override
	public String getUsername() {
		return this.getUsername();
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnable() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	}


