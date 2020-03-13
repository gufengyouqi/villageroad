package com.villageroad.web.controller.utils;

import com.villageroad.web.controller.BaseController;
import com.villageroad.utils.MoneyConvertUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author flybird
 * @date 2020/3/31
 */
@RestController
@RequestMapping(value = "/convert")
public class UtilsController extends BaseController {

    /**
     * 数字转大写钱 工具
     * @param nums
     * @return
     */
    @RequestMapping(value = "/num2money")
    public ResponseEntity num2money(String nums) {
        String result = MoneyConvertUtils.convertToMoney(nums);
        return createResponseEntity(result);
    }
}

