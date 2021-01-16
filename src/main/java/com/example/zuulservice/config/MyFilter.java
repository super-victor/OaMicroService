package com.example.zuulservice.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyFilter
 * @Description 路由过滤
 * @Author Waynejwei
 * @LastChangeDate 2021/1/15 15:30
 * @Version v1.0
 */

public class MyFilter extends ZuulFilter {

    /**
     * 决定过滤式什么时候执行的
     * @return  pre、routing、post
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 决定过滤器是否过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
//        //放行的url列表
//        List<String> urls = new ArrayList<>();
//        //根据请求路径
//        RequestContext context = RequestContext.getCurrentContext();
//        //获取请求路径
//        HttpServletRequest req = context.getRequest();
//        //如果路径在放行的路径之中
//        String url = req.getRequestURI();
//        for (String s : urls){
//            if (s.equals(url)) {
//                return false;
//            }
//        }
        return false;
    }

    /**
     * 过滤的相关操作
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest req = context.getRequest();
        /*是否带有token*/
        String token = req.getParameter("token");
        if (token == null) {
            //设置不进行路由
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(500);  //返回码
            context.setResponseBody("未登录");   //返回数据
        }
        /*token验证*/
        System.out.println("ZuulFilter执行");
        return null;
    }
}
