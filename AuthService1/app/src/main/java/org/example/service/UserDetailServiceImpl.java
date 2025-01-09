package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.example.entities.UserInfo;
import org.example.model.UserInfoDto;
import org.example.repository.UserRepository;
import org.example.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

import static org.example.util.ValidationUtil.isEmailValid;
import static org.example.util.ValidationUtil.isPasswordValid;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new CustomUserDetail(userInfo);
    }

    public UserInfo checkIfUserExists (UserInfoDto userInfodto) {
        return userRepository.findByUsername(userInfodto.getUsername());
    }

    public Boolean signUp(UserInfoDto userInfodto) {
        if(!ValidationUtil.isPasswordValid(userInfodto.getPassword())){
            return false;
        }
        if(!ValidationUtil.isEmailValid(userInfodto.getEmail())){
            return false;
        }
        userInfodto.setPassword(passwordEncoder.encode(userInfodto.getPassword()));
        if(Objects.nonNull(checkIfUserExists(userInfodto))) {
            return false;
        }
        String userId = UUID.randomUUID().toString();
        userRepository.save(new UserInfo(userId,userInfodto.getUsername(),userInfodto.getPassword(),new HashSet<>()));
        return true;
    }
}
