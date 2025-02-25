package com.ruoyi.resource.service.impl;

import com.ruoyi.resource.domain.Comment;
import com.ruoyi.resource.mapper.CommentMapper;
import com.ruoyi.resource.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    /**
     * 获取所有评论
     *
     * @return 评论列表
     */
    @Override
    public List<Comment> getAllComments() {
        return commentMapper.getAllComments();
    }

    /**
     * 根据评论ID获取评论详情
     *
     * @param commentId 评论ID
     * @return 评论详情
     */
    @Override
    public Comment getCommentById(Long commentId) {
        return commentMapper.getCommentById(commentId);
    }

    /**
     * 添加评论
     *
     * @param comment 评论信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addComment(Comment comment) {
        int rows = commentMapper.addComment(comment);
        return rows > 0;
    }

    /**
     * 更新评论信息
     *
     * @param comment 评论信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateComment(Comment comment) {
        int rows = commentMapper.updateComment(comment);
        return rows > 0;
    }

    /**
     * 删除评论
     *
     * @param commentId 待删除的评论ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteComment(Long commentId) {
        int rows = commentMapper.deleteComment(commentId);
        return rows > 0;
    }
}
