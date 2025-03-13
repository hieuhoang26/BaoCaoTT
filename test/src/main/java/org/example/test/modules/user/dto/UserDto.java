package org.example.test.modules.user.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
    Integer id;
    String username;
//    String password;
    String phoneNumber;
    String email;
    Date dateOfBirth;
    String avatar;
}
