package com.villageroad.web.controller.route;

import com.villageroad.service.AccountService;
import com.villageroad.web.controller.BaseController;
import com.villageroad.web.utils.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;

import static org.springframework.http.ResponseEntity.ok;


/**
 * @author flybird
 * @date 2023/8/17
 */
@Slf4j
@Controller("/")
public class LoginController extends BaseController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 跳转到登陆页
     * @return
     */
    @GetMapping(value = "/login.html")
    public String login() {
        return "login";
    }

}

