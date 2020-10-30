package com.zhangtory.base.config;

import com.zhangtory.base.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 17:13
 * @Description:
 */
@WebFilter(filterName = "ipEchoFilter", urlPatterns = {"/ip"})
@Slf4j
public class IpEchoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ipAddr = IpUtils.getIpAddr((HttpServletRequest) servletRequest);
        servletResponse.getWriter().print(ipAddr);
    }

}
