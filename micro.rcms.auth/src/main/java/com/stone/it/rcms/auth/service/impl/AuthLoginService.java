package com.stone.it.rcms.auth.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.auth.dao.IUserAuthDao;
import com.stone.it.rcms.auth.service.IAuthLoginService;
import com.stone.it.rcms.auth.vo.AccountVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.JwtUtils;
import com.stone.it.rcms.core.util.ResponseUtil;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.http.HttpStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/10/14
 * @Desc
 */
@Named
public class AuthLoginService implements IAuthLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthLoginService.class);

    @Inject
    private IUserAuthDao userAuthDao;

    @Override
    public JSONObject userLogin(AuthUserVO userVO) {
        boolean login = subjectLogin(userVO);
        return ResponseUtil.responseBuild(login ? HttpStatus.SC_OK : HttpStatus.SC_INTERNAL_SERVER_ERROR,
            login ? "登录成功！" : "登录失败！");
    }

    @Override
    public JSONObject userToken(AccountVO accountVO) {
        Map<String, String> map = new java.util.HashMap<>();
        map.put("appId", accountVO.getAppId());
        map.put("secret", accountVO.getSecret());
        String token = JwtUtils.generateToken(map);
        return ResponseUtil.responseBuild(new JSONObject().fluentPut("Authorization", token));
    }

    @Override
    public JSONObject userLogout() {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            PrincipalCollection principals = currentUser.getPrincipals();
            if (principals == null) {
                return ResponseUtil.responseBuild(HttpStatus.SC_INTERNAL_SERVER_ERROR, "您当前未登录！");
            }
            AuthUserVO user = (AuthUserVO)principals.getPrimaryPrincipal();
            // 数据库记录日志，执行退出
            currentUser.logout();
            return ResponseUtil.responseBuild(HttpStatus.SC_OK, "退出成功！");
        } catch (Exception e) {
            return ResponseUtil.responseBuild(HttpStatus.SC_INTERNAL_SERVER_ERROR, "退出失败！", e.getMessage());
        }
    }

    @Override
    public JSONObject userRegister(AuthUserVO userVO) {
        return null;
    }

    @Override
    public JSONObject userLogOff() {
        return null;
    }

    private boolean subjectLogin(AuthUserVO userVO) {
        // 查询数据库用户信息
        AuthUserVO dbUser = userAuthDao.getUserInfoByUserId(userVO.getUserId());
        if (dbUser == null) {
            throw new RcmsApplicationException(500, "用户账号/密码错误！");
        }
        if (!dbUser.getPassword().equals(userVO.getPassword())) {
            throw new RcmsApplicationException(500, "用户账号/密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        Map<String, String> user = new HashMap<>();
        user.put("userId", dbUser.getUserId());
        user.put("password", dbUser.getPassword());
        user.put("type", "user");
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getUserId(), JwtUtils.generateToken(user, null));
        subject.login(token);
        return true;
    }
}
