package com.study.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //  模拟数据库返回的数据 加密后的密码 123
        String password = "$2a$10$OhuRAM2ymA9PQwMvVSp28eWtsXTz4rhU9xLKhWOzXzcq3GZjcubEy";

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        ((ArrayList<SimpleGrantedAuthority>) authorities).add(new SimpleGrantedAuthority("ROLE_USER"));

        User user = new User(name, password, authorities);

        return user;
    }
}
