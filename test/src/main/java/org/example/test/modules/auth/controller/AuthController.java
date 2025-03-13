package org.example.test.modules.auth.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.dto.response.ResponseData;
import org.example.test.modules.auth.dto.LogInRequest;
import org.example.test.modules.auth.dto.SignUpRequest;
import org.example.test.modules.auth.dto.TokenRefreshResponse;
import org.example.test.modules.auth.dto.TokenResponse;
import org.example.test.service.Imp.AuthServiceImp;
import org.example.test.util.Uri;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    final AuthServiceImp authService;

    @PostMapping(value = Uri.LOGIN)
    public ResponseEntity<TokenResponse> Login(@Valid @RequestBody LogInRequest login) {
        return new ResponseEntity<>(authService.login(login), HttpStatus.OK);
    }

    @PostMapping(value = {Uri.SIGNUP})
    public ResponseEntity<ResponseData> SignUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return new ResponseEntity<>(authService.signUp(signUpRequest), HttpStatus.OK);
    }
    @PostMapping(value = {Uri.LOGOUT})
    public ResponseEntity<ResponseData> Logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = {Uri.REFRESH})
    public ResponseEntity<?> Refresh(HttpServletRequest  refreshToken) {
        TokenRefreshResponse data = authService.refresh(refreshToken);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or expired refresh token", HttpStatus.FORBIDDEN);
        }
    }

}
