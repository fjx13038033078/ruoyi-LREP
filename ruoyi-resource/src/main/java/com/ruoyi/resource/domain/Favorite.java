package com.ruoyi.resource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author 范佳兴
 * @date 2025/2/24 16:45
 */
@TableName("lerp_favorite")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Favorite implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long favoriteId;

    private Long resourceId;

    @TableField(exist = false)
    private String resourceName;

    private Long userId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime favoriteTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
