package org.example.test.service.Imp;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.model.User;
import org.example.test.modules.user.dto.request.UserUpdateDto;
import org.example.test.repository.UserRepository;
import org.example.test.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findById(Long.valueOf(id)).orElse(null);
        return user;
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Boolean isExistUserName(String username) {
        return userRepository.existsUserByName(username);
    }

    @Override
    public User getUserByEmail(String username) {
        return  userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    @Override
    public void updateUser(Integer id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(Long.valueOf(id)).orElse(null);

        if (userUpdateDto.getUsername() != null) {
            user.setName(userUpdateDto.getUsername());
        }
        if (userUpdateDto.getPassword() != null) {
            user.setPassword(userUpdateDto.getPassword());
        }
        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getPhoneNumber() != null) {
            user.setMobile(userUpdateDto.getPhoneNumber());
        }

        // Handle avatar upload if not null (assuming you're handling avatar as well)
        if (userUpdateDto.getAvatar() != null && !userUpdateDto.getAvatar().isEmpty()) {
            // Handle avatar saving logic here
        }

        MultipartFile avatarFile = userUpdateDto.getAvatar();
        if (avatarFile != null && !avatarFile.isEmpty()) {
            try {
                String uploadDir = "D:/BookStore/TestImg/";
//                Path uploadPath = Paths.get(uploadDir);

//                if (!Files.exists(uploadPath)) {
//                    Files.createDirectories(uploadPath);
//                }

                String filename = id + "_" + avatarFile.getOriginalFilename();
//                Path filePath = uploadPath.resolve(filename);
                String filePath = uploadDir + filename;
                avatarFile.transferTo(new File(filePath));
                user.setAvatar(filename);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save avatar file", e);
            }
        }
        userRepository.save(user);

        log.info("User updated successfully");
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(Long.valueOf(id));
        log.info("User delete successfully");

    }


}
