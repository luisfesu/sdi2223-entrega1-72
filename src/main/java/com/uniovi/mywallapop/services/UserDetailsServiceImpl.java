package com.uniovi.mywallapop.services;

import org.springframework.stereotype.Service;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import  java.util.*;

@Service("userDetailsService")
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired RolesService rolesService;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        User user = usersRepository.findByDni(dni);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(rolesService.getRoles()[0])); // Usuario normal

        if (user == null) {
            throw new UsernameNotFoundException(dni);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getDni(), user.getPassword(), grantedAuthorities);
    }
}
