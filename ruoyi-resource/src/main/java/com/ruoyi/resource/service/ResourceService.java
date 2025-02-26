package com.ruoyi.resource.service;

import com.ruoyi.resource.domain.Resource;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:01
 */
public interface ResourceService {
    /**
     * 获取所有资源
     *
     * @return 资源列表
     */
    List<Resource> getAllResources(String keyword);

    /**
     * 根据资源ID获取资源详情
     *
     * @param resourceId 资源ID
     * @return 资源详情
     */
    Resource getResourceById(Long resourceId);

    /**
     * 添加资源
     *
     * @param resource 待添加的资源信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addResource(Resource resource);

    /**
     * 更新资源信息
     *
     * @param resource 待更新的资源信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateResource(Resource resource);

    /**
     * 删除资源
     *
     * @param resourceId 待删除的资源ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteResource(Long resourceId);
}
