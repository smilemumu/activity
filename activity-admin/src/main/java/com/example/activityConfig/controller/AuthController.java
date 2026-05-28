package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import com.example.activityConfig.dto.LoginRequest;
import com.example.activityConfig.dto.LoginResponse;
import com.example.activityConfig.entity.SysUser;
import com.example.activityConfig.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return Result.success("登录成功", response);
    }

    @GetMapping("/info")
    public Result<SysUser> info() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = authService.getCurrentUser(auth.getName());
        user.setPassword(null);
        return Result.success(user);
    }
}
