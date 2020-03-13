package com.villageroad.service;

import com.villageroad.storage.db.shijia.dao.SellerInfoDao;
import com.villageroad.storage.db.shijia.entity.SellerInfo;
import com.villageroad.utils.MathUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class SellerInfoService {
    @Resource
    private SellerInfoDao sir;

    public SellerInfo login(String username, String password) {
        SellerInfo si = sir.findByUsername(username);
        if (si == null) {
            log.warn("can not find username:{}, password:{}", username, password);
            return null;
        }
        String md5String = MathUtils.getMD5String(password);
        if (Objects.equals(md5String, si.getPassword())){
            return si;
        }
        return null;
    }


}
