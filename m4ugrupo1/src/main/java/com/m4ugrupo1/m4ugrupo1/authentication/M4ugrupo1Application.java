package com.m4ugrupo1.m4ugrupo1.authentication;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.m4ugrupo1.m4ugrupo1.model.User;


//the username identifying the user whose data is required.
public class M4ugrupo1Application implements UserDetailsService {

	private final UserRepository userRepository;
	private final AuthGroupRepository authGroupRepository;
	
	
	public M4ugrupo1Application(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
		super();
		this.userRepository = userRepository;
		this.authGroupRepository = authGroupRepository;
	}

	
     @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User doesn't exist: " + username);
		}
		List<AuthGroup> authGroups = this.authGroupRepository.finByUsername(username);
		return new UserPrincipal(user, authGroups);
}
}