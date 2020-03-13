package com.villageroad.web.controller.shijia;

import com.villageroad.config.Constant;
import com.villageroad.service.SellerInfoService;
import com.villageroad.storage.db.shijia.entity.SellerInfo;
import com.villageroad.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.villageroad.config.Constant.SELLER_SESSION_KEY;

/**
 * Created by flybird on 2018/4/25.
 */
@RestController
public class SellerInfoController extends BaseController {
    @Autowired
    private SellerInfoService sell;

    @PostMapping(value = "/buyer/login")
    public ResponseEntity login(String username, String password) {
        String result = "";
        SellerInfo loginInfo = sell.login(username, password);
        if (null == loginInfo) {
            result = "账号名密错误";
            createResponseEntity(result, "fail");
        }
        request.getSession().setAttribute(SELLER_SESSION_KEY, loginInfo);

        return createResponseEntity("success");
    }

    @PostMapping(value = "/seller/logout")
    public ResponseEntity logout() {
        Object attribute = request.getSession().getAttribute(Constant.SELLER_SESSION_KEY);
        if (null == attribute) {
            return createResponseEntity("need to login", "fail");
        }

        request.getSession().removeAttribute(SELLER_SESSION_KEY);
        return createResponseEntity("success");
    }
}

