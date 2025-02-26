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
 * @date 2025/2/26 14:45
 */
@TableName("lerp_download")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Download implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long downloadId;

    private Long resourceId;

    @TableField(exist = false)
    private String resourceName;

    private Long userId;

    @TableField(exist = false)
    private String username;

    private String fileName;

    private String type;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime downloadTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
