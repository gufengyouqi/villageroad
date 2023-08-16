package com.villageroad.storage.db.shijia.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author houshengbin
 */
@Data
public class ProductInfo {
    @TableId//(type = IdType.AUTO)
    private Long id;
    /**
     * '商品名称'
     */
    private String name;
    /**
     * 价格
     */
    private Float price;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 描述
     */
    private String description;

    /**
     * '图片'
     */
    private String icon;
    /**
     * 类目编号
     */
    private Long categoryId;

    /**
     * 商品状态,0正常1下架
     */
    private Integer status;
    /**
     * 创建时间
     */
    @OrderBy(asc = false, sort = 2)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @OrderBy(asc = false, sort = 1)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
