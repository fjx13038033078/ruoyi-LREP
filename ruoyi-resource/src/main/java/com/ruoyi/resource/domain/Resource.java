package com.ruoyi.resource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author 范佳兴
 * @date 2025/2/24 16:48
 */
@TableName("lerp_resource")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Resource implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long resourceId;

    private String resourceName;

    private String description;

    private Long userId;

    @TableField(exist = false)
    private String userName;

    private String fileName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
