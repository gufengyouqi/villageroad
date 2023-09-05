package com.villageroad.web.controller;

import com.villageroad.web.model.SecurityUserDetails;
import com.villageroad.service.AccountService;
import com.villageroad.web.utils.JwtTokenProvider;
import com.villageroad.web.model.Body;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.ResponseEntity.ok;


/**
 * @author flybird
 * @date 2023/8/17
 */
@Slf4j
@Controller("/auth")
public class AuthController extends BaseController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 前后端分离登录接口 获取token
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity<?> getJwtToken(@Valid @RequestParam("username") @NotBlank(message = "用户名不能为空！") String username,
                                         @RequestParam("password") @NotBlank(message = "密码不能为空！") String password) {

        try {
            //进行用户认证
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            //认证未通过，给出提示
            if(Objects.isNull(authentication)){
                throw new RuntimeException("登陆失败！");

            }
            SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();

            String token = jwtTokenProvider.createToken(securityUserDetails);
            Map<String, Object> model = new HashMap<>();
            model.put("user_name", username);
            model.put("token", token);
            model.put("token_expiration", dateTimeFormatter.format(
                    jwtTokenProvider.getTokenExpiration(token)
                            .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
            return ok(Body.build().ok("登录成功", model));
        } catch (BadCredentialsException e) {
            return ok(Body.build().fail("账号或密码错误！"));
        }

    }

    /**
     * 前后端分离刷新token接口
     *
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/token/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) {
        String currToken = jwtTokenProvider.resolveToken(request);
        String newToken = jwtTokenProvider.refreshToken(currToken);
        Map<String, Object> model = new HashMap<>();
        model.put("username", jwtTokenProvider.getUsername(newToken));
        model.put("token", newToken);
        model.put("token_expiration", dateTimeFormatter.format(
                jwtTokenProvider.getTokenExpiration(newToken)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        return ok(Body.build().ok("刷新token成功", model));
    }

    /**
     * 传统登出接口
     *
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/logout")
    public String logout(HttpSession session) {
//        jwtTokenProvider.isExpiration(session.getAttribute("jwtToken").toString());
        session.removeAttribute("jwtToken");
        return "/login";
    }

}

