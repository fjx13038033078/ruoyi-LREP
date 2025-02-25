package com.ruoyi.resource.service;

import com.ruoyi.resource.domain.Comment;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 8:59
 */
public interface CommentService {
    /**
     * 获取所有评论
     *
     * @return 评论列表
     */
    List<Comment> getAllComments();

    /**
     * 根据评论ID获取评论详情
     *
     * @param commentId 评论ID
     * @return 评论详情
     */
    Comment getCommentById(Long commentId);

    /**
     * 添加评论
     *
     * @param comment 待添加的评论信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addComment(Comment comment);

    /**
     * 更新评论信息
     *
     * @param comment 待更新的评论信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateComment(Comment comment);

    /**
     * 删除评论
     *
     * @param commentId 待删除的评论ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteComment(Long commentId);
}
