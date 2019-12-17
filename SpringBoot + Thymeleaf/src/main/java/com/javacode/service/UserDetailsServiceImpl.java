package com.javacode.service;

import com.javacode.model.Role;
import com.javacode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.HashSet;
import java.util.Set;
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        String name = user.getUsername();
        if(name == null) {
            throw new UsernameNotFoundException("User not registration");
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getAuthority()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                                user.getUserpassword(), roles);
        return userDetails;
    }
}
