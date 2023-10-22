package com.example.cinema_app.services;


import com.example.cinema_app.models.ERole;
import com.example.cinema_app.models.User;
import com.example.cinema_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.cinema_app.models.User myUser = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(),
                mapRolesToAuthorities(myUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<ERole> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }

    public void addUser(User user) throws Exception
    {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null)
        {
            throw new Exception("user exist");
        }
        user.setRoles(Collections.singleton(ERole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }
}