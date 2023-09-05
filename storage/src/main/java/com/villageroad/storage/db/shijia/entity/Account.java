package com.villageroad.storage.db.shijia.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * @author houshengbin
 */
@Data
public class Account {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 姓名
     */
    private String nickname;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 20,message = "密码长度在6-20之间")
    private String password;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不合理")
    private String email;
    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码不能为空")
    @Length(min = 11,max = 11,message = "电话号码长度为11位")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String phone;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

    private List<Role> roleList;
}
