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
import java.math.BigDecimal;

/**
 * @Author 范佳兴
 * @date 2025/2/24 16:43
 */
@TableName("lerp_course")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Course implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long courseId;

    private String courseName;

    private String description;

    private BigDecimal price;

    private String instructor;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
