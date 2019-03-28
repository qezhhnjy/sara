package com.qezhhnjy.login.config.shiro;

import com.qezhhnjy.login.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author fuzy
 * create time 19-3-25-上午10:24
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean filter(SecurityManager securityManager) {
        log.info("shiro filter");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> map = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断，因为前端模板采用了thymeleaf，这里不能直接使用
        // ("/static/**","anon")来配置匿名访问，可以在static目录下加一级目录，然后再配置
        map.put("/doc/**", "anon");
        map.put("/index", "anon");
        map.put("/sign_up", "anon");
        // 配置退出过滤器 其中的具体的退出代码shiro已经替我们实现了
        map.put("/logout", "logout");
        map.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        factoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        factoryBean.setSuccessUrl("/index");

        //未授权界面
        factoryBean.setUnauthorizedUrl("/403");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    /**
     * 凭证匹配器
     * (由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了)
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 散列算法，这里使用MD5算法
        matcher.setHashAlgorithmName(EncryptUtil.ALGORITHM_NAME);
        // 散列的次数
        matcher.setHashIterations(EncryptUtil.HASH_ITERATIONS);
        return matcher;
    }

    @Bean
    public SecurityManager securityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     * 开启shiro aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor advisor(SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    @Bean
    public SimpleMappingExceptionResolver resolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        // 数据库异常处理
        properties.setProperty("DatabaseException", "databaseError");
        properties.setProperty("UnauthorizedException", "/user/403");
        // None by default
        resolver.setExceptionMappings(properties);
        // No default
        resolver.setDefaultErrorView("error");
        // default is "exception"
        resolver.setExceptionAttribute("exception");
        // No default
        resolver.setWarnLogCategory("example.MvcLogger");
        return resolver;
    }
}
