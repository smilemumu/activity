package com.example.activityConfig.service;

import com.example.activityConfig.common.BusinessException;
import com.example.activityConfig.dto.LoginRequest;
import com.example.activityConfig.dto.LoginResponse;
import com.example.activityConfig.entity.SysUser;
import com.example.activityConfig.mapper.UserMapper;
import com.example.activityConfig.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponse login(LoginRequest request) {
        SysUser user = userMapper.findByUsername(request.getUsername());
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        if (!"Y".equals(user.getStatus())) {
            throw new BusinessException("账号已被禁用");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getId());
        return new LoginResponse(token, user.getUsername(), user.getRealName());
    }

    public SysUser getCurrentUser(String username) {
        return userMapper.findByUsername(username);
    }
}
