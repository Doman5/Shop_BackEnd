package com.domanski.backend.homePage.controller;

import com.domanski.backend.homePage.dto.HomePageDto;
import com.domanski.backend.homePage.service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomePageController {

    private final HomePageService homePageService;

    @GetMapping("/homePage")
    public HomePageDto getHomePage() {
        return new HomePageDto(homePageService.getSaleProduct());
    }
}
