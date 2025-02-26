package com.ruoyi.resource.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.resource.domain.Download;
import com.ruoyi.resource.domain.Favorite;
import com.ruoyi.resource.mapper.DownloadMapper;
import com.ruoyi.resource.service.DownloadService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Author 范佳兴
 * @date 2025/2/26 14:47
 */
@Service
@RequiredArgsConstructor
public class DownloadServiceImpl implements DownloadService {

    private final DownloadMapper downloadMapper;

    private final ResourceServiceImpl resourceService;

    private final ISysUserService iSysUserService;

    private final ISysRoleService iSysRoleService;

    /**
     * 获取所有下载记录
     *
     * @return 下载记录列表
     */
    @Override
    public List<Download> getAllDownloads() {
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equals("admin")) {
            startPage();
            List<Download> allDownloads = downloadMapper.getAllDownloads();
            fillDownloadInfo(allDownloads);
            return allDownloads;
        } else {
            startPage();
            List<Download> downloadsByUserId = downloadMapper.getDownloadsByUserId(userId);
            fillDownloadInfo(downloadsByUserId);
            return downloadsByUserId;
        }
    }

    /**
     * 添加下载记录
     *
     * @param download 下载记录对象
     * @return 操作结果
     */
    @Override
    public boolean addDownload(Download download) {
        Long userId = SecurityUtils.getUserId();
        download.setUserId(userId);
        download.setDownloadTime(LocalDateTime.now());
        int rows = downloadMapper.addDownload(download);
        return rows > 0;
    }

    /**
     * 删除下载记录
     *
     * @param downloadId 下载记录ID
     * @return 操作结果
     */
    @Override
    public boolean deleteDownload(Long downloadId) {
        int rows = downloadMapper.deleteDownload(downloadId);
        return rows > 0;
    }

    private void fillDownloadInfo(List<Download> downloads) {
        for (Download download : downloads) {
            Long resourceId = download.getResourceId();
            String resourceName = resourceService.getResourceById(resourceId).getResourceName();
            download.setResourceName(resourceName);
            Long userId = download.getUserId();
            String username = iSysUserService.selectUserById(userId).getNickName();
            download.setUsername(username);
        }
    }
}
