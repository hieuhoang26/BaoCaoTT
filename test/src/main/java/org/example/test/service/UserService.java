package org.example.test.service;


import org.example.test.model.User;
import org.example.test.modules.user.dto.request.UserUpdateDto;

public interface UserService {
//    UserDetailsService userDeailServive();
    void saveUser(User user);
    User getUserById(Integer id);
    User getUserByUserName(String username);
    Boolean isExistUserName(String username);
    User getUserByEmail(String username);
    void updateUser(Integer id, UserUpdateDto userUpdateDto);
    void deleteUser(Integer id);



}
