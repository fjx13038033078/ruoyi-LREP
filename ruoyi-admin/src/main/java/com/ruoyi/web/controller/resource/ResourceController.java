package com.ruoyi.web.controller.resource;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.resource.domain.Resource;
import com.ruoyi.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:35
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/lerp/resource")
public class ResourceController extends BaseController {

    private final ResourceService resourceService;

    /**
     * 获取所有资源列表
     *
     * @return 资源列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllResources(String keyword) {
        startPage();
        List<Resource> resources = resourceService.getAllResources(keyword);
        return getDataTable(resources);
    }

    /**
     * 根据资源ID获取资源详情
     *
     * @param resourceId 资源ID
     * @return 资源详情
     */
    @GetMapping("/get")
    public AjaxResult getResourceById(@RequestParam Long resourceId) {
        return success(resourceService.getResourceById(resourceId));
    }

    /**
     * 添加资源
     *
     * @param resource 资源对象
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult addResource(@RequestBody Resource resource) {
        return toAjax(resourceService.addResource(resource));
    }

    /**
     * 更新资源信息
     *
     * @param resource 资源对象
     * @return 操作结果
     */
    @PostMapping("/update")
    public AjaxResult updateResource(@RequestBody Resource resource) {
        return toAjax(resourceService.updateResource(resource));
    }

    /**
     * 删除资源
     *
     * @param resourceId 资源ID
     * @return 操作结果
     */
    @GetMapping("/delete")
    public AjaxResult deleteResource(@RequestParam Long resourceId) {
        return toAjax(resourceService.deleteResource(resourceId));
    }
}
