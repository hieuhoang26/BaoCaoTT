package org.example.test.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails user);
    String generateRefreshToken(UserDetails user);
    String extractUsername(String token);
    boolean isValid(String token, UserDetails userDetails);
}
