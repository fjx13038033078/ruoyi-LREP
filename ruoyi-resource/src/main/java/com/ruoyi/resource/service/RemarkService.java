package com.ruoyi.resource.service;

import com.ruoyi.resource.domain.Remark;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 8:59
 */
public interface RemarkService {

    /**
     * 获取所有评论
     *
     * @return 所有评论列表
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
     * 添加评论
     *
     * @param remark 待添加的评论信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addRemark(Remark remark);

    /**
     * 删除评论
     *
     * @param remarkId 待删除的评论ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteRemark(Long remarkId);
}
