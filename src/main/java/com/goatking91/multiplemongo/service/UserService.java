package com.goatking91.multiplemongo.service;


import com.goatking91.multiplemongo.dto.UserCreateDto;
import com.goatking91.multiplemongo.model.db1.User;
import com.goatking91.multiplemongo.repository.db1.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreateDto create(UserCreateDto userCreateDto) {
//        User user = userRepository.save(User.builder().name(userCreateDto.getName()).build());
        User user = userRepository.findByName("selim").orElseThrow();
        user.setName(userCreateDto.getName());
        userRepository.save(user);
        return UserCreateDto.builder().build();
    }
}
