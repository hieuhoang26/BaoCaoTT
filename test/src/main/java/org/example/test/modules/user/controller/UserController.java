package org.example.test.modules.user.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.dto.response.ResponseData;
import org.example.test.model.User;
import org.example.test.modules.user.dto.UserDto;
import org.example.test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.test.util.Uri;

@RestController
//@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = Uri.USER + "/{id}")
    public ResponseData UserById(@PathVariable Integer id) {
       User user =  userService.getUserById(id);
        UserDto userDto = UserDto.builder()
                .id(Math.toIntExact(user.getId()))
                .username(user.getUsername())
//                .password(user.getPassword())
                .phoneNumber(user.getMobile())
                .email(user.getEmail())
//                .dateOfBirth(user.getDateOfBirth())
                .avatar(user.getAvatar())
                .build();
        return new ResponseData(HttpStatus.OK.value(), "User Info",userDto );
    }

//    @PutMapping(value = Uri.USER + "/{id}")
//    public ResponseData<?> UpdateUser(@PathVariable Integer id, @ModelAttribute UserUpdateDto userUpdate) {
//        userService.updateUser(id, userUpdate);
//        return new ResponseData<>(HttpStatus.OK.value(), "update user success");
//    }
    @DeleteMapping(value = Uri.USER + "/{id}")
    public ResponseData DeleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return new ResponseData(HttpStatus.OK.value(), "delete user success");
    }

    /* <------------------- Uri.USERS_ORDERS ------------------> */



}
