package org.example.test.security.service.Imp;


import lombok.RequiredArgsConstructor;
import org.example.test.model.User;
import org.example.test.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {

    final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userService.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(userHasRole -> {
//            authorities.add(new SimpleGrantedAuthority(userHasRole.getRole().getName()));
//        });
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), authorities);
        return user;
    }
}
