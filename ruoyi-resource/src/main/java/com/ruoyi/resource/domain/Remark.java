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
@TableName("lerp_remark")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Remark implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long remarkId;

    private Long userId;

    //用户名
    @TableField(exist = false)
    private String username;

    private Long resourceId;

    private Long noteId;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime remarkTime;

    //评论类型，1-资源 2-笔记
    private int type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
