package com.villageroad.storage.db.shijia.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author houshengbin
 */

public enum NotifyTypeEnum {
    // 飞书
    FEISHU("飞书");

    NotifyTypeEnum(String code) {
        this.code = code;
    }

    @EnumValue
    private final String code;

}
