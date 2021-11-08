package com.m4ugrupo1.m4ugrupo1.authentication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m4ugrupo1.m4ugrupo1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
	public List<User> findByEmail(String email);

}
