package com.bagnall.urs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bagnall.urs.model.UserInfo;
import com.bagnall.urs.repository.UserInfoJpaRepository;

@Service
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoJpaRepository userInfoJpaRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userInfoJpaRepo.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with name " + username + " not found");
		}

		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(UserInfo user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities = AuthorityUtils.createAuthorityList(user.getRole());
		return authorities;
	}
}
