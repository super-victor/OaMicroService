package com.oa.auth.filter;

import com.oa.auth.config.GlobalSecurityConfig;
import com.oa.consume.EmployeeMapper;
import com.oa.auth.util.NotLoginException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomFilterInvocationSecurityMetadataSource
 * @Description 根据requestUrl加载所需要的角色
 * @Author JohnTang
 * @LastChangeDate 2020/11/19 15:46
 * @Version v1.0
 */

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    GlobalSecurityConfig globalSecurityConfig;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException,AuthenticationException {
        // 判断系统全局url允许名单
        HttpServletRequest request = ((FilterInvocation)o).getHttpRequest();
        for (String allowUrl: globalSecurityConfig.getAllowUrlList()) {
            if (new AntPathMatcher().match(allowUrl, request.getRequestURI())) {
                return null;
            }
        }

        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            throw new NotLoginException("未登录");
        }
        String urlPatten = request.getMethod() + " " + request.getRequestURI();
        System.out.println(urlPatten);

        // 如果url不在menu表里说明任何人都可以访问
        if (employeeMapper.findMenuIsExist(urlPatten) == 0) {
            return null;
        }

        List<String> needRoleIds = employeeMapper.findRoleIdsByUrlPattern(urlPatten);
        // 如果当前Url对应的角色为空,返回空 说明没有用户拥有这个功能 直接返回权限不足
        if (needRoleIds.isEmpty()) {
            throw new AccessDeniedException("权限不足3");
        }

        String[] needRoleIdsCopy = new String[needRoleIds.size()];
        return SecurityConfig.createList(needRoleIds.toArray(needRoleIdsCopy));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
