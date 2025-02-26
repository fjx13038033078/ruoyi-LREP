package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Download;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/26 14:45
 */
@Mapper
public interface DownloadMapper {
    /**
     * 获取所有下载记录
     *
     * @return 所有下载记录的列表
     */
    List<Download> getAllDownloads();

    /**
     * 根据下载记录ID获取下载记录
     *
     * @param downloadId 下载记录ID
     * @return 下载记录详情
     */
    Download getDownloadById(Long downloadId);

    /**
     * 根据用户ID获取下载记录
     *
     * @param userId 用户ID
     * @return 下载记录列表
     */
    List<Download> getDownloadsByUserId(Long userId);

    /**
     * 添加下载记录
     *
     * @param download 下载记录对象
     * @return 影响的行数
     */
    int addDownload(Download download);

    /**
     * 删除下载记录
     *
     * @param downloadId 下载记录ID
     * @return 影响的行数
     */
    int deleteDownload(Long downloadId);
}
