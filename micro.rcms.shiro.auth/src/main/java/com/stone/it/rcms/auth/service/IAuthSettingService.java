package com.stone.it.rcms.auth.service;

import com.stone.it.rcms.auth.vo.AuthAccountVO;
import com.stone.it.rcms.auth.vo.SystemApiVO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
public interface IAuthSettingService {

    /**
     * 查询已存在的服务信息
     *
     * @param apiPaths
     * @return
     */
    List<SystemApiVO> findApiPathsByPaths(Set<String> apiPaths);

    /**
     * 创建新增的权限路径
     *
     * @param permissionList
     */
    void createApiPaths(List<SystemApiVO> permissionList);

    /**
     * 删除不存在的权限信息
     *
     * @param permissionPathSet
     */
    void deleteApiPathsNotInList(Set<String> permissionPathSet);

    /**
     * 清理不存在授权关系
     *
     */
    void deleteApisRelationAuth();

    AuthAccountVO getUserInfoByUserId(String accountCode);

    List<SystemApiVO> getApiPathByRoleCodes(Set<String> roleSets);

    void createSuperAdminAuth(Set<String> authCodeSet, String roleCode);
}
