package com.ruoyi.web.controller.resource;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.resource.domain.Download;
import com.ruoyi.resource.service.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/26 14:49
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/lerp/download")
public class DownloadController extends BaseController {
    private final DownloadService downloadService;

    /**
     * 获取所有下载记录列表
     *
     * @return 下载记录列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllDownloads() {
        startPage();
        List<Download> downloads = downloadService.getAllDownloads();
        return getDataTable(downloads);
    }

    /**
     * 添加下载记录
     *
     * @param download 下载记录对象
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult addDownload(@RequestBody Download download) {
        return toAjax(downloadService.addDownload(download));
    }

    /**
     * 删除下载记录
     *
     * @param downloadId 下载记录ID
     * @return 操作结果
     */
    @GetMapping("/delete")
    public AjaxResult deleteDownload(@RequestParam Long downloadId) {
        return toAjax(downloadService.deleteDownload(downloadId));
    }
}
