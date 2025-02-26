package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Remark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 8:50
 */
@Mapper
public interface RemarkMapper {

    /**
     * 获取所有评论
     *
     * @return 评论列表
     */
    List<Remark> getAllRemarks();

    /**
     * 根据资源ID获取评论列表
     *
     * @param resourceId 资源ID
     * @return 评论列表
     */
    List<Remark> listRemarksByResourceId(Long resourceId);

    /**
     * 根据笔记ID获取评论列表
     *
     * @param noteId 笔记ID
     * @return 评论列表
     */
    List<Remark> listRemarksByNoteId(Long noteId);

    /**
     * 根据评论ID获取评论
     *
     * @param remarkId 评论ID
     * @return 评论对象
     */
    Remark getRemarkById(Long remarkId);

    /**
     * 添加评论
     *
     * @param remark 评论对象
     * @return 影响的行数
     */
    int addRemark(Remark remark);

    /**
     * 删除评论
     *
     * @param remarkId 评论ID
     * @return 影响的行数
     */
    int deleteRemark(Long remarkId);
}
