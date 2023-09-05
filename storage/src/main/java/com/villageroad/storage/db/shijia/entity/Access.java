package com.villageroad.storage.db.shijia.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * @author houshengbin
 */
@Data
public class Access {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "资源地址不能为空")
    private String path;
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
}
