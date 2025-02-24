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
 * @date 2025/2/24 16:43
 */
@TableName("lerp_comment")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Comment implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long commentId;

    private Long userId;

    private Long resourceId;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime commentTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
