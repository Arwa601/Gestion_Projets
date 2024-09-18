package com.Cp.Stage.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Cp.Stage.Models.Role;
import com.Cp.Stage.Models.User;
import com.Cp.Stage.Repositories.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUserName(userName)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    if (!user.getIsAccountActivated()) {
        throw new UsernameNotFoundException("Account is not activated");
}
        try{
            System.out.println(user.getId());
        
        } catch (Exception e) {
        e.printStackTrace();
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), 
                user.getPassword(), 
                authorities);
    }

    public User activateUser(String userName) {
        User user = userRepository.findFirstByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setIsAccountActivated(true);
        return userRepository.save(user);
    }

    public User deactivateUser(String userName) {
        User user = userRepository.findFirstByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setIsAccountActivated(false);
        return userRepository.save(user);
    }
}

