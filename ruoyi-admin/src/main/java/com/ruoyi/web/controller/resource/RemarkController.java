package com.ruoyi.web.controller.resource;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.resource.domain.Remark;
import com.ruoyi.resource.service.RemarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:30
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/lerp/remark")
public class RemarkController extends BaseController {

    private final RemarkService remarkService;

    /**
     * 获取所有评论列表
     *
     * @return 评论列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllRemarks() {
        startPage();
        List<Remark> remarks = remarkService.getAllRemarks();
        return getDataTable(remarks);
    }

    /**
     * 添加评论
     *
     * @param remark 评论对象
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult addRemark(@RequestBody Remark remark) {
        return toAjax(remarkService.addRemark(remark));
    }

    /**
     * 删除评论
     *
     * @param remarkId 评论ID
     * @return 操作结果
     */
    @GetMapping("/delete")
    public AjaxResult deleteRemark(@RequestParam Long remarkId) {
        return toAjax(remarkService.deleteRemark(remarkId));
    }
}
