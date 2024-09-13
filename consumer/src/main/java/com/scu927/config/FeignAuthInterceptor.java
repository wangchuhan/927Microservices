package com.scu927.config;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Chuhan
 * @date 2024/9/13
 */




@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // get currently context
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null) {
                // 将 Authorization header 添加到 Feign 请求中
                requestTemplate.header("Authorization", authorizationHeader);
            }
        }
    }
}