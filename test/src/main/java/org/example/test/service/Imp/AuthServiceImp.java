package org.example.test.service.Imp;



import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.test.dto.response.ResponseData;
import org.example.test.exception.ResourceNotFoundExcep;
import org.example.test.model.Role;
import org.example.test.model.User;
import org.example.test.modules.auth.dto.LogInRequest;
import org.example.test.modules.auth.dto.SignUpRequest;
import org.example.test.modules.auth.dto.TokenRefreshResponse;
import org.example.test.modules.auth.dto.TokenResponse;
import org.example.test.repository.UserRepository;
import org.example.test.security.service.Imp.UserDetailServiceImp;
import org.example.test.security.service.JwtService;
import org.example.test.service.AuthService;
import org.example.test.service.RoleService;
import org.example.test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.REFERER;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    final UserRepository userRepository;
    final AuthenticationManager authenticationManager;
    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    final UserService userService;
    final RoleService roleService;
    final UserDetailServiceImp userDetailServiceImp;


    @Override
    public TokenResponse login(LogInRequest logInRequest) {
        // xac thuc user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getUsername(), logInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        List<String> roleNames = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        TokenResponse.TokenResponseBuilder responseBuilder = TokenResponse.builder()
                .id(Math.toIntExact(user.getId()))
                .username(user.getName())
                .roles(roleNames)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .message("Login success");
        return responseBuilder.build();
    }

    @Override
    public ResponseData signUp(SignUpRequest signUpRequest) {
        if (userService.isExistUserName(signUpRequest.getUsername())) {
            return new ResponseData(HttpStatus.BAD_REQUEST.value(), "UserName is already taken!");
        }
        User user = new User(signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail(), signUpRequest.getPhoneNumber());
        Role role = roleService.getByName("ROLE_User");
        user.setRole(role);
        userService.saveUser(user);
        return new ResponseData<>(HttpStatus.OK.value(), "Sign Up sucess");
    }



    @Override
    public TokenRefreshResponse refresh(HttpServletRequest request) {
        final String refreshToken = request.getHeader(REFERER);
        if (StringUtils.isBlank(refreshToken)) {
            throw new ResourceNotFoundExcep("Token must be not blank");
        }
        String username = jwtService.extractUsername(refreshToken);
        UserDetails user = userDetailServiceImp.loadUserByUsername(username);
        if (jwtService.isValid(refreshToken, user)) {
            String newToken = jwtService.generateToken(user);
            return TokenRefreshResponse.builder()
                    .tokenType("Bearer")
                    .accessToken(newToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            return null;
        }
    }

}
