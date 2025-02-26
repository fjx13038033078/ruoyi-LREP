package com.ruoyi.resource.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.resource.domain.Resource;
import com.ruoyi.resource.mapper.ResourceMapper;
import com.ruoyi.resource.service.ResourceService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceMapper resourceMapper;

    private final ISysUserService iSysUserService;

    /**
     * 获取所有资源
     *
     * @return 资源列表
     */
    @Override
    public List<Resource> getAllResources(String keyword) {
        List<Resource> allResources = resourceMapper.getAllResources(keyword);
        fillResourceInfo(allResources);
        return allResources;
    }

    /**
     * 根据资源ID获取资源详情
     *
     * @param resourceId 资源ID
     * @return 资源详情
     */
    @Override
    public Resource getResourceById(Long resourceId) {
        return resourceMapper.getResourceById(resourceId);
    }

    /**
     * 添加资源
     *
     * @param resource 资源信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addResource(Resource resource) {
        Long userId = SecurityUtils.getUserId();
        resource.setUserId(userId);
        int rows = resourceMapper.addResource(resource);
        return rows > 0;
    }

    /**
     * 更新资源信息
     *
     * @param resource 资源信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateResource(Resource resource) {
        int rows = resourceMapper.updateResource(resource);
        return rows > 0;
    }

    /**
     * 删除资源
     *
     * @param resourceId 待删除的资源ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteResource(Long resourceId) {
        int rows = resourceMapper.deleteResource(resourceId);
        return rows > 0;
    }

    /**
     * 填充资源信息，包括用户名
     *
     * @param resources 资源列表
     */
    private void fillResourceInfo(List<Resource> resources) {
        for (Resource resource : resources) {
            Long userId = resource.getUserId();
            String nickName = iSysUserService.selectUserById(userId).getNickName();
            resource.setUserName(nickName);
        }
    }
}
