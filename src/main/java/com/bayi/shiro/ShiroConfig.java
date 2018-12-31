package com.bayi.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {




    /**
     *创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器

        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *     常用的过滤器
         *         anon：无需认证（登录）可以访问
         *         authc：必须认证才可以访问
         *         user：如果使用rememberMe的功能可以直接访问
         *         perms:该资源必须得到资源权限才可以访问
         *         role：该资源必须得到角色权限才可以访问
         */

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

//        filterChainDefinitionMap.put("/add", "authc");
//        filterChainDefinitionMap.put("/update", "authc");

        //放行的anon必须要放在过滤authc之前，否则无效，被过滤认证
        filterChainDefinitionMap.put("/testThymeleaf", "anon");

        //放行login.html页面
        filterChainDefinitionMap.put("/login", "anon");

        //授权过滤器
        //注意：当授权拦截后，shiro会自动跳转到未授权页面
        filterChainDefinitionMap.put("/add", "perms[user:add]");
        filterChainDefinitionMap.put("/update", "perms[user:update]");

        //设置认证
        filterChainDefinitionMap.put("/*", "authc");


        //设置登录验证未成功后自动跳转到页面）
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");





        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);





        return shiroFilterFactoryBean;
    }


    /**
     *创建DefaultWebSecurityManager
     */

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroDbRealm")ShiroDbRealm shiroDbRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(shiroDbRealm);
        return securityManager;
    }


    /**
     *创建Realm
     */
    @Bean(name = "shiroDbRealm")
    public ShiroDbRealm getRealm(){
        return new ShiroDbRealm();
    }

    /**
     * 配置ShiroDialect,用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
