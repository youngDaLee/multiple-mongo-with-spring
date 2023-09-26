package com.goatking91.multiplemongo.controller;


import com.goatking91.multiplemongo.dto.UserCreateDto;
import com.goatking91.multiplemongo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserCreateDto> create(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.created(URI.create("v1/api/users")).body(userService.create(userCreateDto));
    }
}
