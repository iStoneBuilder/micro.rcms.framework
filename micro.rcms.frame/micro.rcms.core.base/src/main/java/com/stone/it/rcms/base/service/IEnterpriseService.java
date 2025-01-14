package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.EnterpriseVO;
import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

/**
 * 企业商户管理
 * 
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
@Path("/enterprise")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IEnterpriseService {

    /**
     * 查询企业(商户)列表
     *
     * @return List<EnterpriseVO>
     */
    @GET
    @Path("/records")
    @RcmsMethod(name = "企业(商户)管理.列表查询")
    @RequiresPermissions("permission:enterprise:list:query")
    List<EnterpriseVO> findEnterpriseList(@QueryParam("") EnterpriseVO enterpriseVO);

    /**
     * 查询企业(商户)列表
     *
     * @return List<EnterpriseVO>
     */
    @GET
    @Path("/records/list")
    @RcmsMethod(name = "企业(商户)管理.下级查询")
    @RequiresPermissions("permission:enterprise:list2:query")
    List<EnterpriseVO> findEnterpriseListByPid(@QueryParam("") EnterpriseVO enterpriseVO);

    /**
     * 查询企业(商户)列表
     *
     * @return EnterpriseVO
     */
    @GET
    @Path("/records/tree")
    @RcmsMethod(name = "企业(商户)管理.Tree查询")
    @RequiresPermissions("permission:enterprise:tree:query")
    EnterpriseVO findEnterpriseTree(@QueryParam("") EnterpriseVO enterpriseVO);

    /**
     * 企业(商户)详情
     *
     * @param enterprise_id enterprise_id
     * @return EnterpriseVO
     */
    @GET
    @Path("/records/{enterprise_id}")
    @RcmsMethod(name = "企业(商户)管理.详情查询")
    @RequiresPermissions("permission:enterprise:record:query")
    EnterpriseVO findEnterpriseMerchantById(@PathParam("enterprise_id") String enterprise_id);

    /**
     * 新增企业(商户)信息
     *
     * @param enterpriseVO enterpriseVO
     * @return int
     */
    @POST
    @Path("/records")
    @RcmsMethod(name = "企业(商户)管理.新增")
    @RequiresPermissions("permission:enterprise:create")
    int createEnterpriseMerchant(EnterpriseVO enterpriseVO);

    /**
     * 修改企业(商户)信息
     *
     * @param enterpriseVO enterpriseVO
     * @return int
     */
    @PUT
    @Path("/records/{enterprise_id}")
    @RcmsMethod(name = "企业(商户)管理.修改")
    @RequiresPermissions("permission:enterprise:update")
    int updateEnterpriseMerchant(@PathParam("enterprise_id") String enterprise_id, EnterpriseVO enterpriseVO);

    /**
     * 删除企业(商户)信息
     *
     * @param enterpriseId enterprise_id
     * @return int
     */
    @DELETE
    @Path("/records/{enterprise_id}")
    @RcmsMethod(name = "企业(商户)管理.删除")
    @RequiresPermissions("permission:enterprise:delete")
    int deleteEnterpriseMerchant(@PathParam("enterprise_id") String enterpriseId) throws RcmsApplicationException;

    List<EnterpriseVO> findEnterpriseList(String enterpriseId);

}
