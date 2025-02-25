package com.ruoyi.web.controller.resource;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.resource.domain.Comment;
import com.ruoyi.resource.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:30
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/lerp/comment")
public class CommentController extends BaseController {

    private final CommentService commentService;

    /**
     * 获取所有评论列表
     *
     * @return 评论列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllComments() {
        startPage();
        List<Comment> comments = commentService.getAllComments();
        return getDataTable(comments);
    }

    /**
     * 根据评论ID获取评论详情
     *
     * @param commentId 评论ID
     * @return 评论详情
     */
    @GetMapping("/get")
    public AjaxResult getCommentById(@RequestParam Long commentId) {
        return success(commentService.getCommentById(commentId));
    }

    /**
     * 添加评论
     *
     * @param comment 评论对象
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult addComment(@RequestBody Comment comment) {
        return toAjax(commentService.addComment(comment));
    }

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 操作结果
     */
    @GetMapping("/delete")
    public AjaxResult deleteComment(@RequestParam Long commentId) {
        return toAjax(commentService.deleteComment(commentId));
    }
}
