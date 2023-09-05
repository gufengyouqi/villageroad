package com.villageroad.web.confs.security;

import com.villageroad.storage.db.shijia.dao.AccountDao;
import com.villageroad.storage.db.shijia.dao.RoleDao;
import com.villageroad.storage.db.shijia.entity.Account;
import com.villageroad.web.model.SecurityUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author houshengbin
 */
@Slf4j
@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountDao.getAccountByUserName(username);

            SecurityUserDetails user = new SecurityUserDetails(username, account.getNickname(),
                    account.getPassword(), new ArrayList<>(),
                    true, true, true, true);
            log.info("登录用户信息：{}", user);
            return user;
        } catch (Exception e) {
            String msg = "Username: " + username + " not found";
            log.error(msg, e);
            throw new UsernameNotFoundException(msg);
        }
    }

}
