package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 8:52
 */
@Mapper
public interface ResourceMapper {

    /**
     * 获取所有资源
     *
     * @return 所有资源的列表
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
     * @param resource 资源对象
     * @return 影响的行数
     */
    int addResource(Resource resource);

    /**
     * 更新资源
     *
     * @param resource 资源对象
     * @return 影响的行数
     */
    int updateResource(Resource resource);

    /**
     * 删除资源
     *
     * @param resourceId 资源ID
     * @return 影响的行数
     */
    int deleteResource(Long resourceId);
}
