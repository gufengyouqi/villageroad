package com.villageroad.web.controller.back;

import com.villageroad.service.AccountService;
import com.villageroad.storage.db.shijia.dao.ProductInfoDao;
import com.villageroad.storage.db.shijia.entity.Account;
import com.villageroad.storage.db.shijia.entity.ProductInfo;
import com.villageroad.storage.nacos.SwitchConfiguration;
import com.villageroad.storage.redis.RedisCache;
import com.villageroad.web.controller.BaseController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *
 * @author flybird
 * @date 2018/4/25
 */
@Slf4j
@RestController("/back/account")
public class AccountController extends BaseController<Account> {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SwitchConfiguration switchConfiguration;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        account.setId(null);
        account.setCreateTime(null);
        account.setModifyTime(null);
        accountService.createAccount(account);
        return createResponseEntity(account);
    }

}

