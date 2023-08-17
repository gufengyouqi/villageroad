package com.villageroad.storage.db.shijia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
