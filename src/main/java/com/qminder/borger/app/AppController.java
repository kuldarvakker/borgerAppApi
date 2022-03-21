package com.qminder.borger.app;

import com.qminder.borger.app.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/burgerjoints")
@RequiredArgsConstructor
public class AppController {


    private final AppService appService;

    @GetMapping("/")
    public String helloWorld() {
        return "hello";
    }
}
