package com.stone.it.rcms.auth.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 给filter设置安全管理
        factoryBean.setSecurityManager(securityManager);
        // 配置系统的受限资源
        Map<String, String> map = new HashMap<>();
        // 需要请求需要认证
        map.put("/rcms/**", "authc");
        // 登录请求无需认证
        map.put("/auth/login", "anon");
        map.put("/auth/token", "anon");
        // 访问需要认证的页面如果未登录会跳转到/login
        factoryBean.setLoginUrl("/login");
        // 访问未授权页面会自动跳转到/unAuth
        factoryBean.setUnauthorizedUrl("/unAuth");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    // 自定义密码加密规则
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // hashedCredentialsMatcher.setHashIterations(2);
        // true 代表Hex编码，false代表采用base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    // 自定义认证
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCachingEnabled(false);
        return userRealm;
    }

    // 需要定义DefaultWebSecurityManager，否则会报bean冲突
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
}
