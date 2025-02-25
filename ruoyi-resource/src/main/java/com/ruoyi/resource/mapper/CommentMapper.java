package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 8:50
 */
@Mapper
public interface CommentMapper {
    /**
     * 获取所有评论
     *
     * @return 所有评论的列表
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
     * @param comment 评论对象
     * @return 影响的行数
     */
    int addComment(Comment comment);

    /**
     * 更新评论
     *
     * @param comment 评论对象
     * @return 影响的行数
     */
    int updateComment(Comment comment);

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 影响的行数
     */
    int deleteComment(Long commentId);
}
