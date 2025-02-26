package com.ruoyi.resource.service;

import com.ruoyi.resource.domain.Download;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/26 14:46
 */
public interface DownloadService {
    /**
     * 获取所有下载记录
     *
     * @return 下载记录列表
     */
    List<Download> getAllDownloads();

    /**
     * 添加下载记录
     *
     * @param download 下载记录对象
     * @return 操作结果
     */
    boolean addDownload(Download download);

    /**
     * 删除下载记录
     *
     * @param downloadId 下载记录ID
     * @return 操作结果
     */
    boolean deleteDownload(Long downloadId);
}
