package org.example.test.service;


import jakarta.servlet.http.HttpServletRequest;
import org.example.test.dto.response.ResponseData;
import org.example.test.modules.auth.dto.LogInRequest;
import org.example.test.modules.auth.dto.SignUpRequest;
import org.example.test.modules.auth.dto.TokenRefreshResponse;
import org.example.test.modules.auth.dto.TokenResponse;

public interface AuthService {
    TokenResponse login(LogInRequest logInRequest);
    ResponseData signUp(SignUpRequest signUpRequest);
//    TokenRefreshResponse refresh(String refreshToken);
    TokenRefreshResponse refresh(HttpServletRequest refreshToken);

}
