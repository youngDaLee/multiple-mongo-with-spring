package com.goatking91.multiplemongo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/ping")
@RequiredArgsConstructor
public class PingController {

    @GetMapping("")
    public String ping() {
        return "pong";
    }
}
