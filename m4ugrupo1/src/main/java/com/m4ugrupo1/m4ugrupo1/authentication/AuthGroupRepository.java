package com.m4ugrupo1.m4ugrupo1.authentication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
	public List<AuthGroup>finByUsername(String username);

}
