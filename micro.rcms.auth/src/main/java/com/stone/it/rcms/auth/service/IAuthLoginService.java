package com.stone.it.rcms.auth.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.auth.vo.AccountVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cj.stone
 * @Date 2024/10/14
 * @Desc
 */
@Path("/")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface IAuthLoginService {

    @POST
    @Path("login")
    JSONObject userLogin(AuthUserVO userVO);

    @POST
    @Path("logout")
    JSONObject userLogout();

    @POST
    @Path("register")
    JSONObject userRegister(AuthUserVO userVO);

    @POST
    @Path("logOff")
    JSONObject userLogOff();

    @POST
    @Path("token")
    JSONObject userToken(AccountVO accountVO);

}
